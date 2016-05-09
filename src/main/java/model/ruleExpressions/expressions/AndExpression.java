package model.ruleExpressions.expressions;

public class AndExpression extends LogicExpression {

    //Methods
    public AndExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() && this.rightExpression.interpret());
    }

    @Override
    public String getFailMessage() {
        String failMessageLeft = this.leftExpression.getFailMessage();
        String failMessageRight = this.rightExpression.getFailMessage();

        if (!failMessageRight.isEmpty() && !failMessageLeft.isEmpty()) {
            return (failMessageLeft + ", " + failMessageRight);
        }
        if (!failMessageLeft.isEmpty()) {
            return failMessageLeft;
        }
        return failMessageRight;
    }

}
