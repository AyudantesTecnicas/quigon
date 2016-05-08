package logic;


abstract class LogicSymbolInterpreter extends LogicInterpreter {

    LogicSymbolInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    abstract LogicFactory getFactory();
}
