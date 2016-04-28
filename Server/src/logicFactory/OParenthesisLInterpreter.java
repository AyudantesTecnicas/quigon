package logicFactory;

/**
 * Created by francisco on 4/26/16.
 */
public class OParenthesisLInterpreter extends LogicInterpreter {

    public OParenthesisLInterpreter(final LogicParseManager manager){
        super(manager);
    }

    public boolean interpret(char c){
        if(c == '('){
            manager.openParenthesis();
            return couldInterpret;
        }
        return couldNotInterpret;
    }

}
