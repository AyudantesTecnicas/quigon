package model.ruleExpressions.rules;

import model.elements.ComplexElement;
import model.ruleExpressions.expressions.RuleExpression;

public abstract class ContainerRule extends RuleExpression {

    //Attributes
    protected ComplexElement containerToValidate;

    //Methods
    public ContainerRule() {
        super();
        this.containerToValidate = null;
    }

    public void setContainerToValidate(ComplexElement containerToValidate) {
        this.containerToValidate = containerToValidate;
    }

}
