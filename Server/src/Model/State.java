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
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof State))return false;

        return this.hasTheSameName(other);
    }


    protected Boolean hasTheSameName(Object other) {
        State otherState = (State)other;
        return (this.name.equals(otherState.name));
    }

}
