package model.elements;

import model.rulesexpressions.expressions.IExpression;

public class Player extends ComplexElement {
    private IExpression victoryCondition;
    private IExpression gameOverCondition = null;

    public Player(String name) {
        super(name);
    }

    public boolean hasWon() {
        return victoryCondition.interpret();
    }

    public boolean hasLost() {
        if (gameOverCondition != null) {
            return gameOverCondition.interpret();
        } else {
            return false;
        }
    }

    public void setVictoryCondition(IExpression condition) {
        victoryCondition = condition;
    }

    public void setGameOverCondition(IExpression condition) {
        gameOverCondition = condition;
    }

    @Override
    public boolean equals(Object another) {
        return super.equals(another);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
