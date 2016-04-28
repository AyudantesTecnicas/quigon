package Model;

/**
 * Created by metro on 24/04/16.
 */
public class AndExpression extends LogicExpression {

    //Methods
    public AndExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() && this.rightExpression.interpret());
    }

}
