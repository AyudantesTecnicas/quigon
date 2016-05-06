package logic;


public abstract class LogicSymbolInterpreter extends LogicInterpreter {

    public LogicSymbolInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    abstract LogicFactory getFactory();

}
