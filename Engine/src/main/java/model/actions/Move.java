package model.actions;

import creation.GameRandom;
import logic.Utils;

import model.elements.Element;
import model.rulesexpressions.expressions.IExpression;

import java.util.ArrayList;
import java.util.List;

public class Move extends Element implements IExecutable {

    //Attributes
    private List<Action> actions;
    private IExpression rules;
    private String resultMessage;
    private String correctMessage;
    private Boolean random;
    private GameRandom gameRandom;
    private Move chainedMove = null;

    //Methods
    public Move(String name) {
        super(name);
        init();
    }

    public void addAction(Action action) {
        Utils.addToCollection(action, actions);
    }

    public void setRules(IExpression rules) {
        this.rules = rules;
    }

    public void setResultMessage(String resultMessage) {
        this.correctMessage = resultMessage;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setGameRandom(GameRandom gameRandom) {
        this.gameRandom = gameRandom;
    }

    public void setChainedMove(Move move) {
        this.chainedMove = move;
    }

    @Override
    public void execute() {
        this.process();
    }

    protected Boolean process() {
        if (this.rules == null || this.rules.interpret()) {
            this.executeActions();
            return true;
        } else {
            if (chainedMove != null) {
                System.out.println("chained");
                chainedMove.execute();
            }
            this.resultMessage = this.rules.getFailMessage();
            return false;
        }
    }

    protected Boolean canExecute() {
        return (this.rules == null || this.rules.interpret());
    }

    protected void executeActions() {
        if (!this.random) {
            this.actions.forEach(Action::execute);
        } else {
            this.executeRandomAction();
        }

        this.resultMessage = this.correctMessage;
    }

    protected void executeRandomAction() {
        Action action = this.getRandomAction();

        if (action != null) {
            action.execute();
        }
    }

    private Action getRandomAction() {
        if (this.actions == null || this.actions.isEmpty()) {
            return null;
        }
        //System.out.println("tomando random ...");
        Integer randomNumberOfAction = gameRandom.nextInt(this.actions.size());
        //System.out.println("random = " + randomNumberOfAction);
        return this.actions.get(randomNumberOfAction);
    }

    public void setRandom(Boolean random) {
        this.random = random;
    }

    protected void init() {
        this.actions = new ArrayList<>();
        this.setRules(null);
        this.correctMessage = "Ok";
        this.random = false;
    }

    public boolean equals(Object other) {
        return super.equals(other);
    }

    public int hashCode() {
        return super.hashCode();
    }

}