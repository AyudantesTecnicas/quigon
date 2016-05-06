package logic;

public abstract class LogicInterpreter {

    protected LogicParseManager manager;
    protected static boolean couldInterpret = true;
    protected static boolean couldNotInterpret = false;

    public LogicInterpreter(final LogicParseManager manager) {
        this.manager = manager;
    }

    abstract boolean interpret(char character);

}
