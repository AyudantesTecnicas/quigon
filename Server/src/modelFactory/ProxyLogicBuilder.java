package modelFactory;

import Model.IExpression;
import Model.Rule;

import java.util.HashMap;

/**
 * Created by francisco on 4/27/16.
 */
public class ProxyLogicBuilder extends LogicBuilder {

    public ProxyLogicBuilder(){
        super();
    }

    @Override
    public IExpression parse(HashMap<Character, Rule> rules, String logic) throws WrongLogicException{

        LogicBuilder builder = new LogicBuilder();

        int firstChar = 0;

        boolean interpreted;
        pManager.reset();
        for (int readingPosition = 0; readingPosition < logic.length(); readingPosition++){
            interpreted = false;
            for (LogicInterpreter handler : parseHandlers){
                interpreted |= handler.interpret(logic.charAt(readingPosition));
            }
            if (readingPosition == firstChar && checkFirstChar(rules, logic, interpreted)) {
                    return builder.parse(rules, logic);
            }
        }
        if ((pManager.getFactoryForFoundSymbol() != null && pManager.openedParenthesis() == 0)){
            return builder.parse(rules, logic);
        }
        throw new WrongLogicException();
    }

    private boolean checkFirstChar(HashMap<Character, Rule> rules, String logic, boolean isSpecialChar)
            throws WrongLogicException{
        boolean isParenthesis = (pManager.openedParenthesis() == 1);
        boolean isLastChar = (logic.length() == 1);
        boolean existsRule = rules.containsKey(logic.charAt(0));

        if((isSpecialChar || !isLastChar || !existsRule) && !isParenthesis){
            throw new WrongLogicException();
        }
        else if (!isSpecialChar){
            return true;
        }
        return false;
    }
}