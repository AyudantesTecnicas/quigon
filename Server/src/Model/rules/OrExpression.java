package Model.rules;

/**
 * Created by metro on 24/04/16.
 */
public class OrExpression extends LogicExpression {

    //Methods
    public OrExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() || this.rightExpression.interpret());
    }

    @Override
    public String getFailMessage() {
        String failMessage = this.leftExpression.getFailMessage();

        if (failMessage.isEmpty()) return this.rightExpression.getFailMessage();
        return failMessage;
    }

}
