package model.actions;

import java.util.Random;

public class RandomMove extends Move {

    public RandomMove(String name) {
        super(name);
    }

    @Override
    protected void executeActions() {
        Action action = this.getRandomAction();

        if (action != null) {
            action.execute();
        }
    }

    private Action getRandomAction() {
        if (this.actions == null || this.actions.isEmpty()) {
            return null;
        }

        Integer randomNumberOfAction = new Random().nextInt(this.actions.size());
        return this.actions.get(randomNumberOfAction);
    }

}