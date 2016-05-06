package logic;

import model.rules.AndExpression;
import model.rules.LogicExpression;

public class AndLFactory extends LogicFactory {

    public AndLFactory() {
        super();
    }

    public LogicExpression getExpression() {
        return new AndExpression();
    }

}
