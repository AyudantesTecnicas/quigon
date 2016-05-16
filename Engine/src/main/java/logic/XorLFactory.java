package logic;

import model.rulesexpressions.expressions.LogicExpression;
import model.rulesexpressions.expressions.XorExpression;

class XorLFactory extends LogicFactory {

    XorLFactory() {
        symbol = '^';
    }

    protected LogicExpression buildExpression() {
        return new XorExpression();
    }
}
