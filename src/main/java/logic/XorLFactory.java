package logic;

import model.rules.LogicExpression;
import model.rules.XorExpression;

public class XorLFactory extends LogicFactory {

    public XorLFactory() {
        symbol = '^';
    }

    protected LogicExpression build(){
        return new XorExpression();
    }
}
