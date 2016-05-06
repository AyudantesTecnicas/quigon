package model.rules;


public class HasContainerRule extends RuleExpression {

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasContainerElement(this.elementOfElementToValidate);
    }

}
