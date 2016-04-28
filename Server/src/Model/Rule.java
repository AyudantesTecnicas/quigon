package Model;

/**
 * Created by metro on 24/04/16.
 */
public class Rule extends RuleExpression {

    //Attributes
    private State stateNeeded;
    private State stateNotNeeded;

    //Methods
    public Rule() {
        super();
        this.stateNeeded = null;
        this.stateNotNeeded = null;
    }

    public void setStateNeeded(State aState) {
        this.stateNeeded = aState;
    }

    public void setStateNotNeeded(State aState) {
        this.stateNotNeeded = aState;
    }

    public State getStateNeeded() {
        return this.stateNeeded;
    }

    public State getStateNotNeeded() {
        return this.stateNotNeeded;
    }

    @Override
    public Boolean interpret() {
        if (this.stateNeeded != null)
            if (!this.itemToValidate.hasState(this.stateNeeded)) return false;

        if (this.stateNotNeeded != null)
            if (this.itemToValidate.hasState(this.stateNotNeeded)) return false;
        return true;
    }

}
