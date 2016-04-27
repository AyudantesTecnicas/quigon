package modelFactory;

import Model.AndExpression;
import Model.LogicExpression;
import Model.OrExpression;

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
