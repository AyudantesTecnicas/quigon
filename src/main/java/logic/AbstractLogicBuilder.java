package logic;

import model.rules.IExpression;
import model.rules.RuleExpression;

import java.util.ArrayList;
import java.util.HashMap;

abstract class AbstractLogicBuilder {

    ArrayList<LogicInterpreter> parseHandlers = new ArrayList<>();
    LogicParseManager logicParseManager;

    @SuppressWarnings("CPD-START")
    AbstractLogicBuilder() {
        logicParseManager = new LogicParseManager();
        parseHandlers.add(new OParenthesisLInterpreter(logicParseManager));
        parseHandlers.add(new CParenthesisLInterpreter(logicParseManager));
        parseHandlers.add(new AndLInterpreter(logicParseManager));
        parseHandlers.add(new OrLInterpreter(logicParseManager));
        parseHandlers.add(new XorLInterpreter(logicParseManager));
    }
    @SuppressWarnings("CPD-END")
    abstract IExpression parse(HashMap<Character, RuleExpression> rules, String logic)
            throws  WrongLogicException;

}
