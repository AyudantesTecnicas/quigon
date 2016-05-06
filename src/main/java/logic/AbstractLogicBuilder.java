package logic;

import model.rules.IExpression;
import model.rules.RuleExpression;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by francisco on 4/28/16.
 */
public abstract class AbstractLogicBuilder {

    protected ArrayList<LogicInterpreter> parseHandlers = new ArrayList<>();
    protected LogicParseManager pManager;

    public AbstractLogicBuilder(){
        pManager = new LogicParseManager();
        parseHandlers.add(new OParenthesisLInterpreter(pManager));
        parseHandlers.add(new CParenthesisLInterpreter(pManager));
        parseHandlers.add(new AndLInterpreter(pManager));
        parseHandlers.add(new OrLInterpreter(pManager));
        parseHandlers.add(new XorLInterpreter(pManager));
    }

    abstract IExpression parse(HashMap<Character, RuleExpression> rules, String logic)
            throws  WrongLogicException;

}
