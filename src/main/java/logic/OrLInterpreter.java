package logic;

/**
 * Created by francisco on 4/26/16.
 */
public class OrLInterpreter extends LogicSymbolInterpreter {

    public OrLInterpreter(final LogicParseManager manager){
        super(manager);
    }

    public boolean interpret(char c){
        if(c == '|'){
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }

    public LogicFactory getFactory(){
        return new OrLFactory();
    }

}
