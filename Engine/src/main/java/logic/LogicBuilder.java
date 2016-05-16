package logic;

import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.expressions.LogicExpression;

import java.util.ArrayList;

public class LogicBuilder {

    private ArrayList<LogicFactory> logicHandlers = new ArrayList<>();

    public LogicBuilder() {
        logicHandlers.add(new AndLFactory());
        logicHandlers.add(new OrLFactory());
        logicHandlers.add(new XorLFactory());
    }

    public LogicExpression build(IExpression exp1, IExpression exp2, char symbol)
            throws WrongLogicSymbolException {
        LogicExpression result;
        for (LogicFactory handler : logicHandlers) {
            result = handler.build(exp1, exp2, symbol);
            if (result != null) {
                return result;
            }
        }
        throw new WrongLogicSymbolException();
    }
}