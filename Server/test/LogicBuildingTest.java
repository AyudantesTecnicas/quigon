import Model.IExpression;
import Model.Item;
import Model.Rule;
import Model.State;
import modelFactory.LogicBuilder;
import modelFactory.ProxyLogicBuilder;
import modelFactory.WrongLogicException;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by francisco on 4/26/16.
 */
public class LogicBuildingTest {

    private State state1;
    private State state2;
    private State state3;

    private Item anItem;

    private Rule rule1;
    private Rule rule2;
    private Rule rule3;

    private HashMap<Character, Rule> rules;
    LogicBuilder lBuilder;

    @Before
    public void setUp(){
        state1 = new State();
        state1.setName("S1");

        state2 = new State();
        state2.setName("S2");

        state3 = new State();
        state3.setName("S3");

        anItem = new Item("Un item");
        anItem.addState(state1);
        anItem.addState(state2);
        anItem.addState(state3);

        rule1 = new Rule();
        rule1.setItemToValidate(anItem);
        rule1.setStateNeeded(state1);

        rule2 = new Rule();
        rule2.setItemToValidate(anItem);
        rule2.setStateNeeded(state2);

        rule3 = new Rule();
        rule3.setItemToValidate(anItem);
        rule3.setStateNeeded(state3);

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

        logic = "a&b";
        tryWrongLogic(logic);

        logic = "()&(b)";
        tryWrongLogic(logic);

        logic = "(a)(b)&(c)";
        tryWrongLogic(logic);

    }

}
