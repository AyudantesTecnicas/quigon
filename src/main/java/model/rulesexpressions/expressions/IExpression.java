package model.rulesexpressions.expressions;

public interface IExpression {

    Boolean interpret();

    String getFailMessage();

}
