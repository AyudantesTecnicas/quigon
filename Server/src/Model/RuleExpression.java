package Model;

/**
 * Created by metro on 24/04/16.
 */
public abstract class RuleExpression implements IExpression {

    //Attributes
    protected String name;
    protected Item itemToValidate;

    //Methods
    public RuleExpression() {
        this.name = "";
        this.itemToValidate = null;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public void setItemToValidate(Item anItem) {
        this.itemToValidate = anItem;
    }


}
