package model.rules;

public interface IExpression {

    public Boolean interpret();
    public String getFailMessage();

}
