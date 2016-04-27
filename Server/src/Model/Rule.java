package Model;

/**
 * Created by metro on 24/04/16.
 */
public class Rule {

    //Attributes
    private String name;
    private Item itemToValidate;
    private State stateNeeded;
    private State stateNotNeeded;

    //Methods
    public Rule() {
        this.name = "";
        this.itemToValidate = null;
        this.stateNeeded = null;
        this.stateNotNeeded = null;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public void setItemToValidate(Item anItem) {
        this.itemToValidate = anItem;
    }

    public void setStateNeeded(State aState) {
        this.stateNeeded = aState;
    }

    public void setStateNotNeeded(State aState) {
        this.stateNotNeeded = aState;
    }

    public String getName() {
        return this.name;
    }

    public Item getItemToValidate() {
        return this.itemToValidate;
    }

    public State getStateNeeded() {
        return this.stateNeeded;
    }

    public State getStateNotNeeded() {
        return this.stateNotNeeded;
    }

    //It validates the rule
    public Boolean doesTheRuleMet() {
        if (this.stateNeeded != null)
            if (!this.itemToValidate.hasState(this.stateNeeded)) return false;

        if (this.itemToValidate.hasState(this.stateNotNeeded)) return false;
        return true;
    }

}
