package Model.rules;

/**
 * Created by metro on 24/04/16.
 */
public interface IExpression {

    public Boolean interpret();
    public String getFailMessage();

}