package Model.rules;

import Model.elements.ComplexElement;
import Model.elements.Element;

/**
 * Created by metro on 24/04/16.
 */
public abstract class RuleExpression implements IExpression {

    //Attributes
    protected String failMessage;
    protected ComplexElement elementToValidate;
    protected Element elementOfElementToValidate;

    //Methods
    public RuleExpression() {
        this.setFailMessage("Rule violated");
        this.setElementToValidate(null);
        this.setElementOfElementToValidate(null);
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public void setElementOfElementToValidate(Element elementOfElementToValidate) {
        this.elementOfElementToValidate = elementOfElementToValidate;
    }

    public void setElementToValidate(ComplexElement elementToValidate) {
        this.elementToValidate = elementToValidate;
    }

}
