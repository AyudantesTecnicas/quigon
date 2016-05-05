package Model.rules;

public class DoesNotHaveContainerRule extends RuleExpression {

    @Override
    public Boolean validate() {
        return !this.elementToValidate.hasContainerElement(this.elementOfElementToValidate);
    }

}
