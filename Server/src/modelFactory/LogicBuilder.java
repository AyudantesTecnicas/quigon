package modelFactory;

import Model.IExpression;
import Model.Rule;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by francisco on 4/26/16.
 */
public class LogicBuilder {

    private ArrayList<LogicInterpreter> parseHandlers = new ArrayList<>();
    private LogicParseManager pManager;
    private int takeParenthesisLeft = 1;
    private int takeParenthesisRight = 2;

    public LogicBuilder(){
        pManager = new LogicParseManager(this);
        parseHandlers.add(new OParenthesisLInterpreter(pManager));
        parseHandlers.add(new CParenthesisLInterpreter(pManager));
        parseHandlers.add(new AndLInterpreter(pManager));
        parseHandlers.add(new OrLInterpreter(pManager));
        parseHandlers.add(new XorLInterpreter(pManager));
    }

    public IExpression parse(Map<Character, Rule> rules, String logic){
        boolean interpreted;
        LogicFactory logicFactory;
        pManager.reset();
        for (int readingPosition = 0; readingPosition < logic.length(); readingPosition++){
            interpreted = false;
            for (LogicInterpreter handler : parseHandlers){
                interpreted |= handler.interpret(logic.charAt(readingPosition));
            }
            if (readingPosition == 0 && !interpreted){
                return rules.get(logic.charAt(readingPosition));
            }
            if ((logicFactory = pManager.getFactoryForFoundSymbol()) != null){
                return logicFactory.build(rules, logic.substring(takeParenthesisLeft, readingPosition -
                        takeParenthesisLeft), logic.substring(readingPosition + takeParenthesisRight));
            }
        }
        return null;
    }

}
