package model.rulesexpressions.rules;

public class DoesNotHaveState extends HasStateRule {

    public DoesNotHaveState() {
        super();
    }

    @Override
    public Boolean validate() {
        return !super.validate();
    }

}
