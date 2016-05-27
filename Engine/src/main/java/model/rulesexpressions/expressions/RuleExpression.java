package model.rulesexpressions.expressions;

import model.elements.ComplexElement;
import model.elements.IndexedElement;

public abstract class RuleExpression implements IExpression {

    //Attributes
    private String failMessage;
    protected IndexedElement elementToValidate;
    private Boolean ruleMet;


    //Methods
    public RuleExpression() {
        this.setFailMessage("Rule violated");
        this.elementToValidate = null;
        this.ruleMet = true;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public void setIndexToValidate(String indexToValidate) {
        this.elementToValidate.setIndex(indexToValidate);
    }

    public void setElementToValidate(ComplexElement elementToValidate) {
        if (elementToValidate != null) {
            this.elementToValidate = new IndexedElement(elementToValidate);
        }
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
