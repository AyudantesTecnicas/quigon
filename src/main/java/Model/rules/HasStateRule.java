package model.rules;

public class HasStateRule extends RuleExpression {

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasState(this.elementOfElementToValidate);
    }

}
