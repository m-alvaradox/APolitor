package TDAs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class DecisionTree<E> {
    
    private NodeDecisionTree<E> root;
    private List<E> elements;
    
    public DecisionTree() {
        this.root = null;
        this.elements = null;
    }

    public DecisionTree(E e) {
        this.root = new NodeDecisionTree<>(e);
    }

    public DecisionTree(NodeDecisionTree<E> root) {
        this.root = root;
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public NodeDecisionTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeDecisionTree<E> root) {
        this.root = root;
    }
    
    public boolean isLeaf() {
        return root.getYesBranch() == null && root.getNoBranch() == null;
    }

    public List<E> getElements() {
        return elements;
    }

    public void setElements(List<E> elements) {
        this.elements = elements;
    }
    
    public void recorrerArbol() {
        if(isLeaf()) {
            System.out.println("ES UN: "+ root.getContent());
            return;
        }
        
        System.out.println(root.getContent());
        System.out.println("1: Sí\n2: No");
        System.out.println();
        int myanswer = new java.util.Scanner(System.in).nextInt();
        
        if(myanswer == 1) {
            root.getYesBranch().recorrerArbol();
        } else {
            root.getNoBranch().recorrerArbol();
        }
    } 
    
    public DecisionTree<E> findParent(NodeDecisionTree<E> nodo) {
        if (this.isEmpty() || nodo == null) {
            return null;
        }

        Stack<DecisionTree<E>> stack = new Stack<>();
        stack.push(this);

        while (!stack.isEmpty()) {
            DecisionTree<E> currentTree = stack.pop();
            NodeDecisionTree<E> currentNode = currentTree.getRoot();

            if (currentNode.getYesBranch() != null && currentNode.getYesBranch().getRoot() == nodo) {
                return currentTree;
            }
            if (currentNode.getNoBranch() != null && currentNode.getNoBranch().getRoot() == nodo) {
                return currentTree;
            }

            if (currentNode.getYesBranch() != null) {
                stack.push(currentNode.getYesBranch());
            }
            if (currentNode.getNoBranch() != null) {
                stack.push(currentNode.getNoBranch());
            }
        }

        return null; // Si no se encuentra el padre
    }
    
    public LinkedList<DecisionTree> obtenerLeaves() {
        LinkedList<DecisionTree> hojas = new LinkedList<>();

        // Si el nodo actual es una hoja (no tiene hijos)
        if (this.isLeaf()) {
            hojas.add(this);
        } else {
            // Si tiene un hijo izquierdo, busca recursivamente
            if (this.root.getYesBranch() != null) {
                hojas.addAll(this.root.getYesBranch().obtenerLeaves());
            }
            // Si tiene un hijo derecho, busca recursivamente
            if (this.root.getNoBranch() != null) {
                hojas.addAll(this.root.getNoBranch().obtenerLeaves());
            }
        }

        return hojas;
    }
}
