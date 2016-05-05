package Model.ruleExpressions.expressions;

public interface IExpression {

    Boolean interpret();

    String getFailMessage();

}
