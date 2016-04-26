package modelFactory;

/**
 * Created by francisco on 4/26/16.
 */
public class CParenthesisLInterpreter extends LogicInterpreter {

    public CParenthesisLInterpreter(final LogicParseManager manager){
        super(manager);
    }

    public boolean interpret(char c){
        if(c == ')'){
            manager.closeParenthesis();
            return couldInterpret;
        }
        return couldNotInterpret;
    }

}
