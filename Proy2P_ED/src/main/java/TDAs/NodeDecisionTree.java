
package TDAs;

public class NodeDecisionTree<E> {
    private E content;
    private DecisionTree<E> yes;
    private DecisionTree<E> no;
    
    public NodeDecisionTree(E content) {
        this.content = content;
        this.yes = null;
        this.no = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DecisionTree<E> getYes() {
        return yes;
    }

    public void setYes(DecisionTree<E> yes) {
        this.yes = yes;
    }

    public DecisionTree<E> getNo() {
        return no;
    }

    public void setNo(DecisionTree<E> no) {
        this.no = no;
    }
    
}