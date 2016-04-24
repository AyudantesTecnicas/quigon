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
    private ArrayList<String> statesToDelete; // This will be an array of State Class
    private ArrayList<String> statesToAdd; // This will be an array of State Class

    // Create an action which modifies a specific item
    public Action(String identifier, ArrayList<String> rules, Item item, Map<String, ArrayList<String>> state) {
        this.identifier = identifier;
        this.rules = rules;
        this.itemToModify = item;
        this.statesToDelete = state.getOrDefault("remove",new ArrayList<>());
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

    public boolean equals(Action actionToCompare) {
        return this.identifier.equals(actionToCompare.getIdentifier());
    }

    public void execute() throws Exception {
        Iterator<String> iterator = rules.iterator();
        while (iterator.hasNext()) {
            String rule = iterator.next();
            // rule.evaluate();
            // if error, raise error with message
        }

        if (itemToModify != null) {
            //update item
        }
    }
}
