package model.actions;

import model.elements.Element;

public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.setContainerElement(state);
    }

}
