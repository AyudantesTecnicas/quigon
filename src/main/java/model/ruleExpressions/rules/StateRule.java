package model.ruleExpressions.rules;

import model.elements.Element;
import model.ruleExpressions.expressions.RuleExpression;

public abstract class StateRule extends RuleExpression {

    //Attributes
    protected Element stateToValidate;

    //Methods
    public StateRule() {
        super();
        this.stateToValidate = null;
    }

    public void setStateToValidate(Element stateToValidate) {
        this.stateToValidate = stateToValidate;
    }

}
