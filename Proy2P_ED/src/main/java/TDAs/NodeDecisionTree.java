
package TDAs;

public class NodeDecisionTree<E> {
    private E content;
    private DecisionTree<E> left;
    private DecisionTree<E> right;
    
    public NodeDecisionTree(E content) {
        this.content = content;
        this.left = null;
        this.right = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DecisionTree<E> getLeft() {
        return left;
    }

    public void setLeft(DecisionTree<E> left) {
        this.left = left;
    }

    public DecisionTree<E> getRight() {
        return right;
    }

    public void setRight(DecisionTree<E> right) {
        this.right = right;
    }
    
    
    
    
    
}
