package model.actions;

import logic.Utils;

import model.elements.Element;
import model.rulesexpressions.expressions.IExpression;

import java.util.ArrayList;
import java.util.List;

public class Move extends Element implements IExecutable {

    //Attributes
    protected List<Action> actions;
    protected IExpression rules;
    protected String resultMessage;
    protected String correctMessage;

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

    protected void executeActions() {
        this.actions.forEach(Action::execute);
    }

    @Override
    public void execute() {
        if (this.rules == null || this.rules.interpret()) {
            this.executeActions();
            this.resultMessage = this.correctMessage;

        } else {
            this.resultMessage = this.rules.getFailMessage();
        }
    }

    protected void init() {
        this.actions = new ArrayList<>();
        this.setRules(null);
        this.correctMessage = "Ok";
    }

    public boolean equals(Object other) {
        return super.equals(other);
    }

    public int hashCode() {
        return super.hashCode();
    }

}