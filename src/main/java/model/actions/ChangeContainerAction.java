package model.actions;

import model.elements.Element;
import model.elements.ComplexElement;

public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element element) {
        ComplexElement elementToChange = (ComplexElement)(element);
        this.elementToUpdate.getElement().setContainerElement(elementToChange);
    }

}
