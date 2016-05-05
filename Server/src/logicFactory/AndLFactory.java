package logicFactory;

import Model.ruleExpressions.expressions.AndExpression;
import Model.ruleExpressions.expressions.LogicExpression;

/**
 * Created by francisco on 4/26/16.
 */
public class AndLFactory extends LogicFactory {

    public AndLFactory(){
        super();
    }

    public LogicExpression getExpression(){
        return new AndExpression();
    }

}
