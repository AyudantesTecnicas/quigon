package logic;

/**
 * Created by francisco on 4/26/16.
 */
public abstract class LogicSymbolInterpreter extends LogicInterpreter {

    public LogicSymbolInterpreter(final LogicParseManager manager){
        super(manager);
    }

    abstract LogicFactory getFactory();

}
