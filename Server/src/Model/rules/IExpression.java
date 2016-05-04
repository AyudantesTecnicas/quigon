package Model.rules;

public interface IExpression {

    Boolean interpret();

    String getFailMessage();

}
