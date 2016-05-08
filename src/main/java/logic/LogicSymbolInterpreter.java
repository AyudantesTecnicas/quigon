package logic;


abstract class LogicSymbolInterpreter extends LogicInterpreter {

    LogicSymbolInterpreter(final LogicParseManager manager) {
        super(manager);
    }


    boolean doInterpret(char character, char symbol) {
        if (character == symbol) {
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }


    abstract LogicFactory getFactory();
}
