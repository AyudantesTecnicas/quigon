package logic;

import model.rules.LogicExpression;
import model.rules.XorExpression;

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
