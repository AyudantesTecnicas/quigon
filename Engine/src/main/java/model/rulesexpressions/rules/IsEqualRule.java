package model.rulesexpressions.rules;

import model.elements.ComplexElement;
import model.rulesexpressions.expressions.RuleExpression;

public class IsEqualRule extends RuleExpression {
    //Attributes
    ComplexElement elementToCompare;

    public IsEqualRule() {
        super();
        elementToCompare = null;
    }

    public void setElementToCompare(ComplexElement elementToCompare) {
        this.elementToCompare = elementToCompare;
    }

    @Override
    protected Boolean validate() {
        return this.elementToValidate.getElement().equals(elementToCompare);
    }
}
