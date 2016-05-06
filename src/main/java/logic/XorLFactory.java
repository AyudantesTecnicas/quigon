package logic;

import model.rules.LogicExpression;
import model.rules.XorExpression;

public class XorLFactory extends LogicFactory {

    public XorLFactory() {
        super();
    }

    public LogicExpression getExpression() {
        return new XorExpression();
    }
}
