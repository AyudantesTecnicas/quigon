package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;

public class ChangeVisibleAction extends Action {

    @Override
    protected void applyChanges(Element element) {
        ComplexElement elementToChange = (ComplexElement) (element);
        this.elementToUpdate.getElement().setVisible(elementToChange.getVisible());
    }

}
