package Model;

/**
 * Created by metro on 24/04/16.
 */
public class XorExpression extends LogicExpression {

    //Methods
    public XorExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() ^ this.rightExpression.interpret());
    }

}
