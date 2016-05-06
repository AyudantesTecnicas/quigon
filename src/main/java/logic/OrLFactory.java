package logic;

import model.rules.LogicExpression;
import model.rules.OrExpression;

public class OrLFactory extends LogicFactory {

    public OrLFactory() {
        super();
    }

    public LogicExpression getExpression() {
        return new OrExpression();
    }
}
