package model.rules;

public interface IExpression {

    Boolean interpret();

    String getFailMessage();

}
