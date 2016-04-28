import Model.Item;
import Model.PossessionState;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class PossessionStateTests {

    @Test
    public void testPossessionStatesShouldBeEqualIfTheyAreTheSameInstanceAndTheirNamesToo() {
        PossessionState aState = new PossessionState();
        aState.setName("Un estado");

        PossessionState anotherState = aState;
        anotherState.setName("Un estado");

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testPossessionStatesShouldBeEqualIfTheyAreTheSameInstanceButTheirNamesAreNot() {
        PossessionState aState = new PossessionState();
        aState.setName("Un estado");

        PossessionState anotherState = aState;
        anotherState.setName("Otro estado");

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldntBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreNotToo() {
        PossessionState aState = new PossessionState();
        aState.setName("Un estado");

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Otro estado");

        assertFalse(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreAndTheyDontHaveAnyItem() {
        PossessionState aState = new PossessionState();
        aState.setName("Un estado");

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Un estado");

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldNotBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreAndOneOfThemHasAnItemButTheOtherDoesNot() {
        PossessionState aState = new PossessionState();
        aState.setName("Un estado");
        aState.setItem(new Item("Un item"));

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Un estado");

        assertFalse(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreAndTheirItemsAreTheSame() {
        Item anItem = new Item("Un item");

        PossessionState aState = new PossessionState();
        aState.setName("Un estado");
        aState.setItem(anItem);

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Un estado");
        anotherState.setItem(anItem);

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldNotBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreButTheirItemsAreNotTheSame() {
        Item anItem = new Item("Un item");

        PossessionState aState = new PossessionState();
        aState.setName("Un estado");
        aState.setItem(anItem);

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Un estado");
        anotherState.setItem(new Item("otro item"));

        assertFalse(aState.equals(anotherState));
    }

    @Test
    public void testPossessionSatesShouldNotBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreNotTooButTheirItemsAreTheSame() {
        Item anItem = new Item("Un item");

        PossessionState aState = new PossessionState();
        aState.setName("Un estado");
        aState.setItem(anItem);

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Otro estado");
        anotherState.setItem(anItem);

        assertFalse(aState.equals(anotherState));
    }

}
