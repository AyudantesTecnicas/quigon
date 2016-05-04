package Model.rules;

import Model.elements.ComplexElement;
import Model.elements.Element;

public abstract class RuleExpression implements IExpression {

    //Attributes
    protected String failMessage;
    protected ComplexElement elementToValidate;
    protected Element elementOfElementToValidate;
    protected Boolean ruleMet;

    //Methods
    public RuleExpression() {
        this.setFailMessage("Rule violated");
        this.setElementToValidate(null);
        this.setElementOfElementToValidate(null);
        this.ruleMet = true;
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

    @Override
    public String getFailMessage() {
        if (!this.ruleMet) {
            return this.failMessage;
        }
        return "";
    }

    @Override
    public Boolean interpret() {
        this.ruleMet = this.validate();
        return this.ruleMet;
    }

    protected abstract Boolean validate();

}
