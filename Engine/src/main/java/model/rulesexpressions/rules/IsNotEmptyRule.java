package model.rulesexpressions.rules;

import model.rulesexpressions.expressions.RuleExpression;

public class IsNotEmptyRule extends RuleExpression {

    public IsNotEmptyRule() {
        super();
    }

    @Override
    protected Boolean validate() {
        return this.elementToValidate.getElement().hasElements();
    }

}
