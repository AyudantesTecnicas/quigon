package Model.actions;

import Model.elements.Element;

/**
 * Created by metro on 27/04/16.
 */
public class ChangeContainerAction extends Action {

    @Override
    protected void applyChanges(Element state) {
        this.elementToUpdate.setContainerElement(state);
    }

}
