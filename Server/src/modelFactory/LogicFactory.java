package modelFactory;

import Model.IExpression;
import Model.LogicExpression;
import Model.Rule;

import java.util.Map;

/**
 * Created by francisco on 4/26/16.
 */
public abstract class LogicFactory {

    protected LogicBuilder parser;

    public LogicFactory(){
        parser = new LogicBuilder();
    }

    public IExpression build(Map<Character, Rule> rules, String logicLeft, String logicRight){
        IExpression right = parser.parse(rules, logicLeft);
        IExpression left = parser.parse(rules, logicRight);
        LogicExpression lExp = getExpression();
        lExp.setLeftExpression(left);
        lExp.setRightExpression(right);
        return lExp;
    }

    abstract LogicExpression getExpression();

}
