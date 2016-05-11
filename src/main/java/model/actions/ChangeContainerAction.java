package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;

public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element element) {
        ComplexElement elementToChange = (ComplexElement) (element);
        this.elementToUpdate.getElement().setContainerElement(elementToChange);
    }

}
