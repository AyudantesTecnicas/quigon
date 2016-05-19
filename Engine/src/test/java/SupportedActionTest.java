import org.junit.Test;
import parser.SupportedAction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SupportedActionTest {
    @Test
    public void testReceiveActionShorterThanActionID() {
        SupportedAction supportedAction = new SupportedAction(2, "abrir");

        assertFalse(supportedAction.isEqual("abre"));
    }

    @Test
    public void testReceiveActionWithCorrectItemExpected() {
        SupportedAction supportedAction = new SupportedAction(2, "abre");

        assertTrue(supportedAction.isEqual("abre puerta cofre"));
    }

    @Test
    public void testReceiveActionNotSupportedWithItem() {
        SupportedAction supportedAction = new SupportedAction(0, "abre");

        assertFalse(supportedAction.isEqual("abre puerta"));
    }

    @Test
    public void testReceiveBlankInstructionWithSpaces() {
        SupportedAction supportedAction = new SupportedAction(2, "abre");

        assertFalse(supportedAction.isEqual("   "));
    }
}
