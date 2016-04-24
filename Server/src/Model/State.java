package Model;

/**
 * Created by metro on 24/04/16.
 */
public class State {

    //Attributes
    private String name;

    //Methods
    public State() {
        this.name = "";
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object stateToCompare) {
        if (!(stateToCompare instanceof State)) {
            return false;
        }
        State auxState = (State)stateToCompare;
        return (this.name.equals(auxState.getName()));
    }

}

