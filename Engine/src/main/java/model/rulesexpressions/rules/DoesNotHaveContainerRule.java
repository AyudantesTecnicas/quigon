package model.rulesexpressions.rules;

public class DoesNotHaveContainerRule extends HasContainerRule {

    public DoesNotHaveContainerRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return !super.validate();
    }

}
