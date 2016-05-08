package logic;

class AndLInterpreter extends LogicSymbolInterpreter {

    AndLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        return doInterpret(character, '&');
    }

    public LogicFactory getFactory() {
        return new AndLFactory();
    }

}
