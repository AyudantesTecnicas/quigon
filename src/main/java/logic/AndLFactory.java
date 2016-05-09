package logic;

import model.ruleExpressions.expressions.AndExpression;
import model.ruleExpressions.expressions.LogicExpression;

public class AndLFactory extends LogicFactory {

    public AndLFactory() {
        symbol = '&';
    }

    protected LogicExpression build() {
        return new AndExpression();
    }

}
