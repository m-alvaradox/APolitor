
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

    
}
