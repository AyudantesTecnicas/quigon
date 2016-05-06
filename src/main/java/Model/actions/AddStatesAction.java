package model.actions;

import model.elements.Element;

/**
 * Created by metro on 27/04/16.
 */
public class AddStatesAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.addState(state);
    }

}
