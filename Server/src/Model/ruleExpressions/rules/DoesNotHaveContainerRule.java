package Model.ruleExpressions.rules;

/**
 * Created by metro on 05/05/16.
 */
public class DoesNotHaveContainerRule extends HasContainerRule {

    public DoesNotHaveContainerRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return !super.interpret();
    }

}
