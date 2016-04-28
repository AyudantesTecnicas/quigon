package logicFactory;

/**
 * Created by francisco on 4/26/16.
 */
public abstract class LogicInterpreter {

    protected LogicParseManager manager;
    protected static boolean couldInterpret = true;
    protected static boolean couldNotInterpret = false;

    public LogicInterpreter(final LogicParseManager manager){
        this.manager = manager;
    }

    abstract boolean interpret(char c);

}
