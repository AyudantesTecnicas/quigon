package Model.rules;

public class XorExpression extends LogicExpression {

    //Methods
    public XorExpression() {
        super();
    }

    @Override
    public Boolean interpret() {
        return (this.leftExpression.interpret() ^ this.rightExpression.interpret());
    }

    @Override
    public String getFailMessage() {
        return this.leftExpression.getFailMessage();
    }

}
