package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 *  Action modifies the state of an item if certain rules are exceeded
 */
public class Action {
    private String identifier;
    private IExpression rule;
    private Item itemToModify;
    private ArrayList<State> statesToDelete;
    private ArrayList<State> statesToAdd;

    // Create an action which modifies a specific item
    public Action(String identifier, IExpression rule, Item item, Map<String, ArrayList<State>> state) {
        this.identifier = identifier;
        this.rule = rule;
        this.itemToModify = item;
        this.statesToDelete = state.getOrDefault("remove", new ArrayList<>());
        this.statesToAdd = state.getOrDefault("add",new ArrayList<>());
    }

    // Create a regular action
    public Action(String identifier, IExpression rule) {
        this.identifier = identifier;
        this.rule = rule;
        this.itemToModify = null;
        this.statesToDelete = new ArrayList<>();
        this.statesToAdd = new ArrayList<>();
    }

    // Create a regular action without rules (always execute)
    public Action(String identifier) {
        this.identifier = identifier;
        this.rule = null;
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

    public boolean execute() {
        if (!this.rule.interpret()) {
            return false;
        }

        if (itemToModify != null) {
            itemToModify.addStates(statesToAdd);
            itemToModify.removeStates(statesToDelete);
        }

        return true;
    }
}
