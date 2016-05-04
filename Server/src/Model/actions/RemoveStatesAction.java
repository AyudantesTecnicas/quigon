package Model.actions;

import Model.elements.Element;

public class RemoveStatesAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.removeState(state);
    }

}
