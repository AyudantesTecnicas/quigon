package model.rulesexpressions.rules;

import model.elements.ComplexElement;
import model.rulesexpressions.expressions.RuleExpression;

public abstract class ContainerRule extends RuleExpression {

    //Attributes
    ComplexElement containerToValidate;

    //Methods
    ContainerRule() {
        super();
        this.containerToValidate = null;
    }

    public void setContainerToValidate(ComplexElement containerToValidate) {
        this.containerToValidate = containerToValidate;
    }

}
