package model.rulesexpressions.rules;

public class HasStateRule extends StateRule {

    public HasStateRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return this.elementToValidate.getElement().hasState(this.stateToValidate);
    }

}
