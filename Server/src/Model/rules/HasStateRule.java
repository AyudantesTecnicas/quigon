package Model.rules;

public class HasStateRule extends RuleExpression {

    public HasStateRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasState(this.elementOfElementToValidate);
    }

}
