package logic;

import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.expressions.LogicExpression;

abstract class LogicFactory {

    char symbol;

    LogicFactory() {
    }

    public LogicExpression build(IExpression leftExp, IExpression rightExp, char symbol) {
        if (validExpression(symbol)) {
            LogicExpression result = buildExpression();
            result.setLeftExpression(leftExp);
            result.setRightExpression(rightExp);
            return result;
        }
        return null;
    }

    private boolean validExpression(char symbol) {
        return symbol == this.symbol;
    }

    abstract LogicExpression buildExpression();

}