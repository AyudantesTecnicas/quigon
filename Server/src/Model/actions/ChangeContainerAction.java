package Model.actions;

import Model.elements.Element;

public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.setContainerElement(state);
    }

}
