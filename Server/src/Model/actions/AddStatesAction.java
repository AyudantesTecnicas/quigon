package Model.actions;

import Model.elements.Element;

public class AddStatesAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.getElement().addState(state);
    }

}
