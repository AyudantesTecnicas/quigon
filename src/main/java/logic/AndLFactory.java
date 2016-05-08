package logic;

import model.rules.AndExpression;
import model.rules.LogicExpression;

public class AndLFactory extends LogicFactory {

    public AndLFactory() {
        symbol = '&';
    }

    protected LogicExpression build(){
        return new AndExpression();
    }

}
