package model.rulesexpressions.rules;

public class HasContainerRule extends ContainerRule {

    public HasContainerRule() {
        super();
    }

    @Override
    public Boolean validate() {
        return this.elementToValidate.getElement().hasContainerElement(this.containerToValidate);
    }

}
