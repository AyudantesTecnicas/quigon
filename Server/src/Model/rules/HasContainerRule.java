package Model.rules;

/**
 * Created by metro on 27/04/16.
 */
public class HasContainerRule extends RuleExpression {

    @Override
    public Boolean validate() {
        return this.elementToValidate.hasContainerElement(this.elementOfElementToValidate);
    }

}
