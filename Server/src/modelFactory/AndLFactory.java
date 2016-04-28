package modelFactory;

import Model.AndExpression;
import Model.LogicExpression;

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
