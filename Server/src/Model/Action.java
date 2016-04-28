package Model;

import java.util.ArrayList;
import java.util.List;

/**
 *  Action modifies the state of an item if certain rules are exceeded
 */
public class Action {
    private String identifier;
    private IExpression rule;
    private List<ItemsStatesToUpdate> itemsStatesToUpdate;

    // Create an action which modifies a specific item
    public Action(String identifier, IExpression rule, List<ItemsStatesToUpdate> itemsStatesToUpdate) {
        this.identifier = identifier;
        this.rule = rule;
        this.itemsStatesToUpdate = itemsStatesToUpdate;
    }

    // Create a regular action
    public Action(String identifier, IExpression rule) {
        this.identifier = identifier;
        this.rule = rule;
        this.itemsStatesToUpdate = new ArrayList<>();
    }

    // Create a regular action without rules (always execute)
    public Action(String identifier) {
        this.identifier = identifier;
        this.rule = null;
        this.itemsStatesToUpdate = new ArrayList<>();
    }

    public void setRule(IExpression rule) {
        this.rule = rule;
    }

    public void addItemsStatesToUpdate(ItemsStatesToUpdate itemsStatesToUpdate) {
        this.itemsStatesToUpdate.add(itemsStatesToUpdate);
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
        if (this.rule != null && !this.rule.interpret()) {
            return false;
        }

        for (ItemsStatesToUpdate itemsStatesToUpdate : this.itemsStatesToUpdate) {
            itemsStatesToUpdate.update();
        }

        return true;
    }
}
