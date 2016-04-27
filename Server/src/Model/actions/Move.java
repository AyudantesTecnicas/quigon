package Model.actions;

import Model.elements.Element;
import Model.rules.IExpression;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by metro on 27/04/16.
 */
public class Move extends Element implements IExecutable {

    //Attributes
    private List<Action> actions;
    private IExpression rules;

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

    @Override
    public void execute() {
        if (this.rules.interpret()) {
            Iterator<Action> iterator = this.actions.iterator();

            while (iterator.hasNext()) {
                iterator.next().execute();
            }
        }
    }

    private void initMove() {
        this.actions = new ArrayList<>();
        this.setRules(null);
    }

}
