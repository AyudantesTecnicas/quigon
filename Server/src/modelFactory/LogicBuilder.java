package modelFactory;

import Model.IExpression;
import Model.Rule;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by francisco on 4/26/16.
 */
public class LogicBuilder {

    protected ArrayList<LogicInterpreter> parseHandlers = new ArrayList<>();
    protected LogicParseManager pManager;

    public LogicBuilder(){
        pManager = new LogicParseManager(this);
        parseHandlers.add(new OParenthesisLInterpreter(pManager));
        parseHandlers.add(new CParenthesisLInterpreter(pManager));
        parseHandlers.add(new AndLInterpreter(pManager));
        parseHandlers.add(new OrLInterpreter(pManager));
        parseHandlers.add(new XorLInterpreter(pManager));
    }

    public IExpression parse(HashMap<Character, Rule> rules, String logic) throws  WrongLogicException{
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
                int beginningString1 = 1;
                int endString1 = readingPosition - 1;
                int beginningString2 = readingPosition + 2;
                int endString2 = logic.length() - 1;
                return logicFactory.build(rules, logic.substring(beginningString1, endString1),
                        logic.substring(beginningString2, endString2));
            }
        }
        throw new WrongLogicException();
    }

}
