package Model.ruleExpressions.rules;

import Model.elements.ComplexElement;
import Model.ruleExpressions.expressions.RuleExpression;

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
