import Model.State;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class StateTests {

    @Test
    public void testStatesShouldBeEqualIfTheyAreTheSameInstance() {
        State aState = new State();
        aState.setName("Un estado");

        State anotherState = aState;
        anotherState.setName("Un estado");

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testStatesShouldBeEqualIfTheirNamesAreTheSame() {
        State aState = new State();
        aState.setName("Un estado");

        State anotherState = new State();
        anotherState.setName("Un estado");

        assertTrue(aState.equals(anotherState));
    }

    @Test
    public void testSatesShouldntBeEqualIfTheyAreNotTheSameInstanceAndTheirNamesAreNotToo() {
        State aState = new State();
        aState.setName("Un estado");

        State anotherState = new State();
        anotherState.setName("Otro estado");

        assertFalse(aState.equals(anotherState));
    }

}
