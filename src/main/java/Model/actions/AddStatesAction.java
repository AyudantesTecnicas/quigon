package model.actions;

import model.elements.Element;

public class AddStatesAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.addState(state);
    }

}
