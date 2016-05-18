package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;

public class MakeNotVisibleAction extends Action {

    @Override
    protected void applyChanges(Element element) {

        this.elementToUpdate.getElement().setVisible(false);
    }
}
