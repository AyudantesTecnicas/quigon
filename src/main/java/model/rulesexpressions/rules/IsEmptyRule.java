package model.rulesexpressions.rules;

public class IsEmptyRule extends IsNotEmptyRule {

    public IsEmptyRule() {
        super();
    }

    @Override
    protected Boolean validate() {
        return !super.validate();
    }

}
