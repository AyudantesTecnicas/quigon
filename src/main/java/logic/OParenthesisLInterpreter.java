package logic;

public class OParenthesisLInterpreter extends LogicInterpreter {

    public OParenthesisLInterpreter(final LogicParseManager manager) {
        super(manager);
    }

    public boolean interpret(char character) {
        if (character == '(') {
            manager.openParenthesis();
            return couldInterpret;
        }
        return couldNotInterpret;
    }

}
