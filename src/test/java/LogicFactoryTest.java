import model.elements.ComplexElement;
import model.elements.Element;
import model.rules.HasStateRule;
import model.rules.IExpression;
import model.rules.RuleExpression;
import logic.ProxyLogicBuilder;
import logic.WrongLogicException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class LogicFactoryTest {

    private Element state1;
    private Element state2;
    private Element state3;

    private ComplexElement anItem;

    private HasStateRule rule1;
    private HasStateRule rule2;
    private HasStateRule rule3;

    private HashMap<Character, RuleExpression> rules;
    ProxyLogicBuilder lBuilder;

    @Before
    public void setUp(){
        state1 = new Element("S1");
        state2 = new Element("S2");
        state3 = new Element("S3");

        anItem = new ComplexElement("Un item");
        anItem.addState(state1);
        anItem.addState(state2);
        anItem.addState(state3);

        rule1 = new HasStateRule();
        rule1.setElementToValidate(anItem);
        rule1.setElementOfElementToValidate(state1);

        rule2 = new HasStateRule();
        rule2.setElementToValidate(anItem);
        rule2.setElementOfElementToValidate(state2);

        rule3 = new HasStateRule();
        rule3.setElementToValidate(anItem);
        rule3.setElementOfElementToValidate(state3);

        rules = new HashMap();
        rules.put('a', rule1);
        rules.put('b', rule2);
        rules.put('c', rule3);

        lBuilder = new ProxyLogicBuilder();

    }

    private IExpression buildExpression(String logic) throws AssertionError{
        try {
            return (lBuilder.parse(rules, logic));
        } catch (WrongLogicException e) {
            e.printStackTrace();
            throw new AssertionError();
        }
    }

    @Test
    public void testCreateOneRule(){
        String logic = "a";

        IExpression expression = buildExpression(logic);

        assert (expression == rule1);
    }

    @Test
    public void testCreateAnd(){
        String logic = "(a)&(b)";

        IExpression expression = buildExpression(logic);

        assert (expression.interpret() == true);

        anItem.removeState(state2);

        assert (expression.interpret() == false);
    }

    @Test
    public void testCreateOr(){
        String logic = "(a)|(b)";

        IExpression expression = buildExpression(logic);

        assert (expression.interpret() == true);

        anItem.removeState(state2);

        assert (expression.interpret() == true);
    }

    @Test
    public void testCreateAndOr(){
        String logic = "((a)&(b))|(c)";

        IExpression expression = buildExpression(logic);

        assert (expression.interpret() == true);

        anItem.removeState(state1);

        assert (expression.interpret() == true);

        anItem.removeState(state3);

        assert (expression.interpret() == false);

        anItem.addState(state1);

        assert (expression.interpret() == true);
    }

    @Test
    public void testCreateXorAnd(){
        String logic = "((a)^(b))&(c)";

        IExpression expression = buildExpression(logic);

        assert (expression.interpret() == false);

        anItem.removeState(state1);

        assert (expression.interpret() == true);

        anItem.removeState(state2);

        assert (expression.interpret() == false);
    }

    private void tryWrongLogic(String logic) throws AssertionError{
        IExpression expression = null;
        try {
            expression = buildExpression(logic);
        } catch (AssertionError e) {
            assert (expression == null);
        }
    }

    @Test
    public void testWrongLogic(){
        String logic;

        logic = "(";
        tryWrongLogic(logic);

        logic = "a&b";
        tryWrongLogic(logic);

        logic = "()&(b)";
        tryWrongLogic(logic);

        logic = "(a)(b)&(c)";
        tryWrongLogic(logic);
    }

}
