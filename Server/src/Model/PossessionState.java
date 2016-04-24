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

}
