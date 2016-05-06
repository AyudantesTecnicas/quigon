package logic;

import model.rules.IExpression;
import model.rules.RuleExpression;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class AbstractLogicBuilder {

    protected ArrayList<LogicInterpreter> parseHandlers = new ArrayList<>();
    protected LogicParseManager logicParseManager;

    public AbstractLogicBuilder() {
        logicParseManager = new LogicParseManager();
        parseHandlers.add(new OParenthesisLInterpreter(logicParseManager));
        parseHandlers.add(new CParenthesisLInterpreter(logicParseManager));
        parseHandlers.add(new AndLInterpreter(logicParseManager));
        parseHandlers.add(new OrLInterpreter(logicParseManager));
        parseHandlers.add(new XorLInterpreter(logicParseManager));
    }

    abstract IExpression parse(HashMap<Character, RuleExpression> rules, String logic)
            throws  WrongLogicException;

}
