package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A basic component.
 * It can execute actions to modify state from others items
 */
public class Item {
    private String identifier;
    private ArrayList<Action> actions;
    private ArrayList<State> states;

    public Item(String identifier, ArrayList<Action> validActions) {
        this.identifier = identifier;
        this.actions = validActions;
        this.states = new ArrayList<>();
    }

    public Item(String identifier) {
        this.identifier = identifier;
        this.actions = new ArrayList<>();
        this.states = new ArrayList<>();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public ArrayList<State> getStates() {
        return this.states;
    }

    public void addState(State stateToAdd) {
        if (stateToAdd != null) {
            this.states.add(stateToAdd);
        }
    }

    public void addState(ArrayList<State> statesToAdd) {
        if (statesToAdd != null) {
            this.states.addAll(statesToAdd);
        }
    }

    public void removeState(State stateToRemove) {
        if (stateToRemove != null) {
            this.states.remove(stateToRemove);
        }
    }

    public void removeState(ArrayList<State> statesToRemove) {
        if (statesToRemove != null) {
            this.states.removeAll(statesToRemove);
        }
    }

    public void execute(Action action) throws Exception {
        boolean haveExecute = false;
        Iterator<Action> iterator = actions.iterator();
        while (iterator.hasNext()) {
            Action currentAction = iterator.next();
            if (action.equals(currentAction)) {
                currentAction.execute(this);
                haveExecute = true;
                break;
            }
        }

        if (!haveExecute) {
            throw new Exception("No valid action");
        }
    }

    public Boolean hasState(State aState) {
        return (this.states.contains(aState));
    }
}
