import org.junit.Test;
import GameParser.SupportedAction;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Created by nicolas on 26/04/16.
 */
public class SupportedActionTest {
    @Test
    public void testReceiveActionLongerThanActionID(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertFalse(aSupportedAction.isEqual("abrir"));
    }

    @Test
    public void testReceiveActionShorterThanActionID(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abrir");

        assertFalse(aSupportedAction.isEqual("abre"));
    }

    @Test
    public void testReceiveActionWithLessItemExpected(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertFalse(aSupportedAction.isEqual("abre"));
    }

    @Test
    public void testReceiveActionWithCorrectItemExpected(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertTrue(aSupportedAction.isEqual("abre puerta cofre"));
    }

    @Test
    public void testReceiveActionNotSupportedWithItem(){
        SupportedAction aSupportedAction = new SupportedAction(0,"abre");

        assertFalse(aSupportedAction.isEqual("abre puerta"));
    }

    @Test
    public void testReceiveActionWithMoreItemThanExpected(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertFalse(aSupportedAction.isEqual("abre puerta caja cofre"));
    }

    @Test
    public void testReceiveBlankInstruction(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertFalse(aSupportedAction.isEqual(""));
    }

    @Test
    public void testReceiveBlankInstructionWithSpaces(){
        SupportedAction aSupportedAction = new SupportedAction(2,"abre");

        assertFalse(aSupportedAction.isEqual("   "));
    }
}
