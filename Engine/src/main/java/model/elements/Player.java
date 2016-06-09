package model.elements;

import model.actions.Move;
import model.rulesexpressions.expressions.IExpression;

import java.util.Iterator;

public class Player extends ComplexElement {
    private IExpression victoryCondition;
    private IExpression gameOverCondition = null;
    private Move updateLostGame;

    public Player(String name) {
        super(name);
    }

    public void resetPlayer() {
        dropElements();
        if (updateLostGame != null) {
            updateLostGame.execute();
        }
    }

    public boolean hasWon() {
        return victoryCondition.interpret();
    }

    public boolean hasLost() {
        if ((gameOverCondition != null) && gameOverCondition.interpret()) {
            resetPlayer();
            return true;
        }
        return false;
    }

    private void dropElements() {
        for (int i = 0; i < this.elements.size(); i++) {
            ComplexElement element = this.elements.get(i);
            element.setContainerElement((ComplexElement) getContainerElement());
        }
    }

    public void setVictoryCondition(IExpression condition) {
        victoryCondition = condition;
    }

    public void setGameOverCondition(IExpression condition, Move resetMove) {
        gameOverCondition = condition;
        updateLostGame = resetMove;
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
