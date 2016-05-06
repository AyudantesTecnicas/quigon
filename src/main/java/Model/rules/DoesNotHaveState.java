package model.rules;

public class DoesNotHaveState extends HasStateRule {

    @Override
    public Boolean validate() {
        return !super.interpret();
    }

}
