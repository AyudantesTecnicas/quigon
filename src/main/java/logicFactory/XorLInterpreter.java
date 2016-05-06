package logicFactory;

/**
 * Created by francisco on 4/26/16.
 */
public class XorLInterpreter extends LogicSymbolInterpreter {

    public XorLInterpreter(final LogicParseManager manager){
        super(manager);
    }

    public boolean interpret(char c){
        if(c == '^'){
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }

    public LogicFactory getFactory(){
        return new XorLFactory();
    }

}
