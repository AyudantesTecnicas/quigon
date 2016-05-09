package logic;

import model.ruleExpressions.expressions.LogicExpression;
import model.ruleExpressions.expressions.OrExpression;

public class OrLFactory extends LogicFactory {

    public OrLFactory() {
        symbol = '|';
    }

    protected LogicExpression build(){
        return new OrExpression();
    }
}
