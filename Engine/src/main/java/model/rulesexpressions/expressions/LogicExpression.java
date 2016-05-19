package model.rulesexpressions.expressions;

public abstract class LogicExpression implements IExpression {

    //Attributes
    IExpression leftExpression;
    IExpression rightExpression;
    String failMessage;

    //Methods
    LogicExpression() {
        this.leftExpression = null;
        this.rightExpression = null;
        this.failMessage = "";
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    public String getFailMessage() {
        if (this.failMessage.isEmpty()) {
            return this.getFinalMessage();
        }
        return this.failMessage;
    }

    protected abstract String getFinalMessage();

    public void setLeftExpression(IExpression anExpression) {
        this.leftExpression = anExpression;
    }

    public void setRightExpression(IExpression anExpression) {
        this.rightExpression = anExpression;
    }

}
