package Model.actions;

import Model.elements.Element;
import Model.rules.IExpression;

import java.util.ArrayList;
import java.util.List;

public class Move extends Element implements IExecutable {

    //Attributes
    private List<Action> actions;
    private IExpression rules;
    private String resultMessage;

    //Methods
    public Move() {
        super();
        this.initMove();
    }

    public Move(String name) {
        super(name);
        this.initMove();
    }

    public void addAction(Action action) {
        if (action != null) {
            if (!this.actions.contains(action)) {
                this.actions.add(action);
            }
        }
    }

    public void setRules(IExpression rules) {
        this.rules = rules;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    @Override
    public void execute() {
        if (this.rules.interpret()) {
            for (Action action : this.actions) {
                action.execute();
            }
        } else {
            this.resultMessage = this.rules.getFailMessage();
        }
    }

    private void initMove() {
        this.actions = new ArrayList<>();
        this.setRules(null);
        this.resultMessage = "Ok";
    }

}
