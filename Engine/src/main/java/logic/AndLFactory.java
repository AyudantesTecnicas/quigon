package logic;

import model.rulesexpressions.expressions.AndExpression;
import model.rulesexpressions.expressions.LogicExpression;

class AndLFactory extends LogicFactory {

    AndLFactory() {
        symbol = '&';
    }

    protected LogicExpression buildExpression() {
        return new AndExpression();
    }

}
