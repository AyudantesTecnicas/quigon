package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *  Action modifies the state of an item if certain rules are exceeded
 */
public class Action {
    private String identifier;
    private ArrayList<String> rules; // This will be an array of Rule Class
    private Item itemToModify;
    private ArrayList<State> statesToDelete; // This will be an array of State Class
    private ArrayList<State> statesToAdd; // This will be an array of State Class

    // Create an action which modifies a specific item
    public Action(String identifier, ArrayList<String> rules, Item item, Map<String, ArrayList<State>> state) {
        this.identifier = identifier;
        this.rules = rules;
        this.itemToModify = item;
        this.statesToDelete = state.getOrDefault("remove", new ArrayList<>());
        this.statesToAdd = state.getOrDefault("add",new ArrayList<>());
    }

    // Create a regular action
    public Action(String identifier, ArrayList<String> rules) {
        this.identifier = identifier;
        this.rules = rules;
        this.itemToModify = null;
        this.statesToDelete = new ArrayList<>();
        this.statesToAdd = new ArrayList<>();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public boolean equals(Object actionToCompare) {
        if (!(actionToCompare instanceof Action)) {
            return false;
        }
        Action auxAction = (Action)actionToCompare;
        return this.identifier.equals(auxAction.getIdentifier());
    }

    public void execute(Item itemExecute) throws Exception {
        Iterator<String> iterator = rules.iterator();
        while (iterator.hasNext()) {
            String rule = iterator.next();
            // rule.evaluate();
            // if error, raise error with message
        }

        if (itemToModify != null) {
            itemToModify.addState(statesToAdd);
            itemToModify.removeState(statesToDelete);
        }
    }
}
