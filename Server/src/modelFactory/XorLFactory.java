package modelFactory;

import Model.AndExpression;
import Model.LogicExpression;
import Model.XorExpression;

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
