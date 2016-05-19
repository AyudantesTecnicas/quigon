package model.rulesexpressions.expressions;

public class XorExpression extends LogicExpression {

    //Methods
    public XorExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() ^ this.rightExpression.interpret());
    }

    @Override
    protected String getFinalMessage() {
        return this.leftExpression.getFailMessage();
    }

}
