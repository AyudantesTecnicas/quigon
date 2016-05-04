package Model.rules;

public class HasContainerRule extends RuleExpression {

    public HasContainerRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasContainerElement(this.elementOfElementToValidate);
    }

}
