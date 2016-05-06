package model.rules;

/**
 * Created by metro on 27/04/16.
 */
public class DoesNotHaveState extends HasStateRule {

    @Override
    public Boolean validate() {
        return !super.interpret();
    }

}
