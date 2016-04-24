package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by metro on 24/04/16.
 */
public class ItemsStatesToUpdate {

    //Attributes
    Item itemToUpdate;
    List<State> statesToAdd;
    List<State> statesToDelete;

    //Methods
    public ItemsStatesToUpdate() {
        this.itemToUpdate = null;
        this.statesToAdd = new ArrayList<>();
        this.statesToDelete = new ArrayList<>();
    }

    public void setItemToUpdate(Item anItem) {
        this.itemToUpdate = anItem;
    }

    public void addStateToAdd(State aState) {
        this.statesToAdd.add(aState);
    }

    public void addStateToDelete(State aState) {
        this.statesToDelete.add(aState);
    }

    public void update() {
        if (this.itemToUpdate != null) {
            this.itemToUpdate.addStates(this.statesToAdd);
            this.itemToUpdate.removeStates(this.statesToDelete);
        }
    }

}
