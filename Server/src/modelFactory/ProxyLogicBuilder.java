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
            if (readingPosition == firstChar) {
                if (!interpreted) {
                    if (logic.length() == 1){
                        if (rules.containsKey(logic.charAt(readingPosition))) {
                            return builder.parse(rules, logic);
                        }
                    }
                    else {
                        throw new WrongLogicException();
                    }
                }
                else if(pManager.openedParenthesis() != 1) {
                    throw new WrongLogicException();
                }
            }
        }
        if ((pManager.getFactoryForFoundSymbol() != null && pManager.openedParenthesis() == 0)){
            return builder.parse(rules, logic);
        }
        throw new WrongLogicException();
    }
}
