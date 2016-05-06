package logic;

import model.rules.IExpression;
import model.rules.LogicExpression;
import model.rules.RuleExpression;

import java.util.HashMap;

/**
 * Created by francisco on 4/26/16.
 */
public abstract class LogicFactory {

    protected AbstractLogicBuilder parser;

    public LogicFactory(){
        parser = new ProxyLogicBuilder();
    }

    public IExpression build(HashMap<Character, RuleExpression> rules, String logicLeft,
                             String logicRight)
    throws WrongLogicException{
        IExpression right = parser.parse(rules, logicLeft);
        IExpression left = parser.parse(rules, logicRight);
        LogicExpression lExp = getExpression();
        lExp.setLeftExpression(left);
        lExp.setRightExpression(right);
        return lExp;
    }

    abstract LogicExpression getExpression();

}
