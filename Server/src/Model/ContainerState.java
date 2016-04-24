package Model;

/**
 * Created by metro on 24/04/16.
 */
public class ContainerState extends State {

    //Attributes
    private Item item;

    //Methods
    public ContainerState() {
        super();
    }

    public void setItem(Item anItem) {
        this.item = anItem;
    }

    public Item getItem() {
        return this.item;
    }

}
