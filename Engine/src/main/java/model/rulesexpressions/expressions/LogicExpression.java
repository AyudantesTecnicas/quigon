package model.rulesexpressions.expressions;

public abstract class LogicExpression implements IExpression {

    //Attributes
    IExpression leftExpression;
    IExpression rightExpression;

    //Methods
    LogicExpression() {
        this.leftExpression = null;
        this.rightExpression = null;
    }

    public void setLeftExpression(IExpression anExpression) {
        this.leftExpression = anExpression;
    }

    public void setRightExpression(IExpression anExpression) {
        this.rightExpression = anExpression;
    }

}
