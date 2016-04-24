package Model;

/**
 * Created by metro on 24/04/16.
 */
public class PossessionState extends State {

    //Attributes
    private Item item;

    //Methods
    public PossessionState() {
        super();
    }

    public void setItem(Item anItem) {
        this.item = anItem;
    }

    public Item getItem() {
        return this.item;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof State))return false;

        if (!this.hasTheSameName(other)) return false;

        PossessionState otherState = (PossessionState)other;

        if (this.item == null && otherState.item == null) return true;
        if (this.item == null && otherState.item != null) return false;
        if (this.item != null && otherState.item == null) return false;
        return (this.item.equals(otherState.item));
    }

}
