package logic;

import model.rules.LogicExpression;
import model.rules.OrExpression;

public class OrLFactory extends LogicFactory {

    public OrLFactory() {
        symbol = '|';
    }

    protected LogicExpression build(){
        return new OrExpression();
    }
}
