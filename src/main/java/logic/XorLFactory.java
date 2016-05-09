package logic;

import model.ruleExpressions.expressions.LogicExpression;
import model.ruleExpressions.expressions.XorExpression;

public class XorLFactory extends LogicFactory {

    public XorLFactory() {
        symbol = '^';
    }

    protected LogicExpression build(){
        return new XorExpression();
    }
}
