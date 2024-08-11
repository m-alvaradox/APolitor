
package TDAs;

import java.util.List;

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
}
