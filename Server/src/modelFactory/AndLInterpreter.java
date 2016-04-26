package modelFactory;

/**
 * Created by francisco on 4/26/16.
 */
public class AndLInterpreter extends LogicSymbolInterpreter {

    public AndLInterpreter(final LogicParseManager manager){
        super(manager);
    }

    public boolean interpret(char c){
        if(c == '&'){
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }

    public LogicFactory getFactory(){
        return new AndLFactory();
    }

}
