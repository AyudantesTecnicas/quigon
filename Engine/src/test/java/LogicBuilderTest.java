import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.HasStateRule;
import org.junit.Before;
import org.junit.Test;

public class LogicBuilderTest {
    private Element state1;
    private Element state2;
    private Element state3;

    private ComplexElement anItem;

    private HasStateRule rule1;
    private HasStateRule rule2;
    private HasStateRule rule3;

    private LogicBuilder logicBuilder;

    @Before
    public void setUp() {
        state1 = new Element("S1");
        state2 = new Element("S2");
        state3 = new Element("S3");

        anItem = new ComplexElement("Un item");
        anItem.addState(state1);
        anItem.addState(state2);
        anItem.addState(state3);

        rule1 = new HasStateRule();
        rule1.setElementToValidate(anItem);
        rule1.setStateToValidate(state1);

        rule2 = new HasStateRule();
        rule2.setElementToValidate(anItem);
        rule2.setStateToValidate(state2);

        rule3 = new HasStateRule();
        rule3.setElementToValidate(anItem);
        rule3.setStateToValidate(state3);

        logicBuilder = new LogicBuilder();

    }

    private IExpression buildExpression(IExpression exp1, IExpression exp2, char symbol)
            throws AssertionError {
        try {
            return (logicBuilder.build(exp1, exp2, symbol));
        } catch (WrongLogicSymbolException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }

    @Test
    public void testAndBuilding() {
        IExpression resultRule = buildExpression(rule1, rule2, '&');

        assert (resultRule.interpret());

        anItem.removeState(state2);

        assert (!resultRule.interpret());
    }

    @Test
    public void testOrBuilding() {
        IExpression resultRule = buildExpression(rule1, rule2, '|');

        assert (resultRule.interpret());

        anItem.removeState(state2);

        assert (resultRule.interpret());
    }

    @Test
    public void testAndAndBuilding() {
        IExpression resultRule = buildExpression(rule1, rule2, '&');
        resultRule = buildExpression(resultRule, rule3, '&');

        assert (resultRule.interpret());

        anItem.removeState(state1);

        assert (!resultRule.interpret());

        anItem.addState(state1);
        anItem.removeState(state2);

        assert (!resultRule.interpret());

        anItem.addState(state2);
        anItem.removeState(state3);

        assert (!resultRule.interpret());
    }

}