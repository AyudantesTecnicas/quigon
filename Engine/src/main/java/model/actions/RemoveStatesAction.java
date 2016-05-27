package model.actions;

import model.elements.Element;

public class RemoveStatesAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.getElement().removeState(state);
    }

}
