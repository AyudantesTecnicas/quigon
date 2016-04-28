package logicFactory;

import Model.rules.AndExpression;
import Model.rules.LogicExpression;

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
