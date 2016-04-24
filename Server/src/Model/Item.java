package Model;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A basic component.
 * It can execute actions to modify state from others items
 */
public class Item {
    private String identifier;
    private ArrayList<Action> actions;
    private ArrayList<String> states; // This will be a State Class array

    public Item(String identifier, ArrayList<Action> validActions) {
        this.identifier = identifier;
        this.actions = validActions;
        this.states = new ArrayList<String>();
    }

    public Item(String identifier) {
        this.identifier = identifier;
        this.actions = new ArrayList<Action>();
        this.states = new ArrayList<String>();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ArrayList<String> getStates() {
        return this.states;
    }

    // TODO: test!
    public void addState(ArrayList<String> statesToAdd) {
        if (statesToAdd != null) {
            this.states.addAll(statesToAdd);
        }
    }

    // TODO: test!
    public void removeState(ArrayList<String> statesToRemove) {
        if (statesToRemove != null) {
            this.states.removeAll(statesToRemove);
        }
    }

    public void execute(Action action) throws Exception{
        boolean haveExecute = false;
        Iterator<Action> iterator = actions.iterator();
        while(iterator.hasNext()) {
            Action currentActon = iterator.next();
            if (currentActon.equals(action)) {
                currentActon.execute();
                haveExecute = true;
                break;
            }
        }

        if (!haveExecute){
            throw new Exception("No valid action");
        }
    }
}
