package Model.actions;

import Model.elements.ComplexElement;
import Model.elements.Element;

public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element element) {
        ComplexElement elementToChange = (ComplexElement)(element);
        this.elementToUpdate.getElement().setContainerElement(elementToChange);
    }

}
