package logic;

public class CParenthesisLInterpreter extends LogicInterpreter {

    public CParenthesisLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        if (character == ')') {
            manager.closeParenthesis();
            return couldInterpret;
        }
        return couldNotInterpret;
    }

}
