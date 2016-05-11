package logic;

import model.rulesexpressions.expressions.LogicExpression;
import model.rulesexpressions.expressions.OrExpression;

class OrLFactory extends LogicFactory {

    OrLFactory() {
        symbol = '|';
    }

    protected LogicExpression build() {
        return new OrExpression();
    }
}
