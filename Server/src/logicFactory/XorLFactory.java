package logicFactory;

import Model.ruleExpressions.expressions.LogicExpression;
import Model.ruleExpressions.expressions.XorExpression;

/**
 * Created by francisco on 4/26/16.
 */
public class XorLFactory extends LogicFactory {

    public XorLFactory(){
        super();
    }

    public LogicExpression getExpression(){
        return new XorExpression();
    }
}
