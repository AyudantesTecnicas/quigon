package modelFactory;

import Model.AndExpression;
import Model.IExpression;
import Model.LogicExpression;
import Model.Rule;

import java.util.ArrayList;

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
