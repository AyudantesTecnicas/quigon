import Model.Item;
import Model.State;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class ItemTests {

    @Test
    public void testItemContainsAState() {
        State aState = new State();
        aState.setName("UN estado");

        Item anItem = new Item("Un item");
        anItem.addState(aState);

        State anotherState = new State();
        anotherState.setName("UN estado");

        assertTrue(anItem.hasState(anotherState));

    }

}
