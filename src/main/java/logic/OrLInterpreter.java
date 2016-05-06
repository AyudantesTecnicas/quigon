package logic;

public class OrLInterpreter extends LogicSymbolInterpreter {

    public OrLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        if (character == '|') {
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }

    public LogicFactory getFactory() {
        return new OrLFactory();
    }

}
