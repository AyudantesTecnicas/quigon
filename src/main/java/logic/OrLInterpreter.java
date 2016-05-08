package logic;

class OrLInterpreter extends LogicSymbolInterpreter {

    OrLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        return doInterpret(character, '|');
    }

    public LogicFactory getFactory() {
        return new OrLFactory();
    }

}
