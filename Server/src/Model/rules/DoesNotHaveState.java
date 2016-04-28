package Model.rules;

/**
 * Created by metro on 27/04/16.
 */
public class DoesNotHaveState extends HasStateRule {

    @Override
    public Boolean interpret() {
        return !super.interpret();
    }

}
