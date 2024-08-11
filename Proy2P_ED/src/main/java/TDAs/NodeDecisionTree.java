
package TDAs;

public class NodeDecisionTree<E> {
    private E content;
    private DecisionTree<E> yesBranch;
    private DecisionTree<E> noBranch;
    
    public NodeDecisionTree(E content) {
        this.content = content;
        this.yesBranch = null;
        this.noBranch = null;
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public DecisionTree<E> getYesBranch() {
        return yesBranch;
    }

    public void setYesBranch(DecisionTree<E> yesBranch) {
        this.yesBranch = yesBranch;
    }

    public DecisionTree<E> getNoBranch() {
        return noBranch;
    }

    public void setNoBranch(DecisionTree<E> noBranch) {
        this.noBranch = noBranch;
    }
    
    
}