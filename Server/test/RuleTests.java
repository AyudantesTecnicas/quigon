import Model.Item;
import Model.PossessionState;
import Model.Rule;
import Model.State;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class RuleTests {

    @Test
    public void testRuleWithNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anItem = new Item("Un item");
        anItem.addState(aState);

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNeeded(aState);

        assertTrue(aRule.doesTheRuleMet());
    }

    @Test
    public void testRuleWithNotNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anItem = new Item("Un item");

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNotNeeded(aState);

        assertTrue(aRule.doesTheRuleMet());
    }

    @Test
    public void testRuleWithNotNeededStateAndNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anotherItem = new Item("Otro item");

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Otro estado");
        anotherState.setItem(anotherItem);

        State otherStateMore = new State();
        otherStateMore.setName("Otro estado mas");

        Item anItem = new Item("Un item");
        anItem.addState(anotherState);
        anItem.addState(otherStateMore);

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNotNeeded(aState);
        aRule.setStateNeeded(anotherState);

        assertTrue(aRule.doesTheRuleMet());
    }

}
