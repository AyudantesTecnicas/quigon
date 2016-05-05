package Model.ruleExpressions.rules;

import Model.elements.Element;
import Model.ruleExpressions.expressions.RuleExpression;

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
