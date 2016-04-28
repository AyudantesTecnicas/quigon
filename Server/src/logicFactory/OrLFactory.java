package logicFactory;

import Model.rules.LogicExpression;
import Model.rules.OrExpression;

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
