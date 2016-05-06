package logic;

import model.rules.IExpression;
import model.rules.RuleExpression;

import java.util.HashMap;


public class LogicBuilder extends AbstractLogicBuilder {

    public LogicBuilder() {
        super();
    }

    public IExpression parse(HashMap<Character, RuleExpression> rules, String logic)
            throws WrongLogicException {
        boolean interpreted;
        LogicFactory logicFactory;
        logicParseManager.reset();
        for (int readingPosition = 0; readingPosition < logic.length(); readingPosition++) {
            interpreted = false;
            for (LogicInterpreter handler : parseHandlers) {
                interpreted |= handler.interpret(logic.charAt(readingPosition));
            }
            if (readingPosition == 0 && !interpreted) {
                return rules.get(logic.charAt(readingPosition));
            }
            if ((logicFactory = logicParseManager.getFactoryForFoundSymbol()) != null) {
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
