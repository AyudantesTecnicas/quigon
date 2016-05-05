package logicFactory;

import Model.ruleExpressions.expressions.LogicExpression;
import Model.ruleExpressions.expressions.OrExpression;

/**
 * Created by francisco on 4/26/16.
 */
public class OrLFactory extends LogicFactory {

    public OrLFactory(){
        super();
    }

    public LogicExpression getExpression(){
        return new OrExpression();
    }
}
