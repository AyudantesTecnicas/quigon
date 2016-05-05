package Model.ruleExpressions.rules;

public class DoesNotHaveState extends HasStateRule {

    public DoesNotHaveState() {
        super();
    }

    @Override
    public Boolean validate() {
        return !super.interpret();
    }

}
