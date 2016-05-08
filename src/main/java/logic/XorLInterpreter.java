package logic;

class XorLInterpreter extends LogicSymbolInterpreter {

    XorLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        return doInterpret(character,'^');
    }

    public LogicFactory getFactory() {
        return new XorLFactory();
    }

}
