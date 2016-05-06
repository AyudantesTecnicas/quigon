package logic;

public class XorLInterpreter extends LogicSymbolInterpreter {

    public XorLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        if (character == '^') {
            manager.notifySymbolFound(this);
            return couldInterpret;
        }
        return couldNotInterpret;
    }

    public LogicFactory getFactory() {
        return new XorLFactory();
    }

}
