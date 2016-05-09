package model.rulesexpressions.rules;

import model.elements.Element;
import model.rulesexpressions.expressions.RuleExpression;

public abstract class StateRule extends RuleExpression {

    //Attributes
    Element stateToValidate;

    //Methods
    StateRule() {
        super();
        this.stateToValidate = null;
    }

    public void setStateToValidate(Element stateToValidate) {
        this.stateToValidate = stateToValidate;
    }

}
