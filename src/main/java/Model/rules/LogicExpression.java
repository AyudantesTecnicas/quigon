package model.rules;

public abstract class LogicExpression implements IExpression {

    //Attributes
    protected IExpression leftExpression;
    protected IExpression rightExpression;

    //Methods
    public LogicExpression() {
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
