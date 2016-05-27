import org.junit.Test;
import parser.GameAction;
import parser.GameParser;
import parser.SupportedAction;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameParserTests {
    @Test
    public void testSupportAnActionIDWithMoreThanOneWord() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir fuerte");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);
        GameAction gameAction = gameParser.parseInstruction("abrir fuerte puerta");
        assertTrue(gameAction.getActionID().equals("abrir fuerte")
                && gameAction.getItemsID().size() == supportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testTwoActionsWithSameIDButDifferentExpectedItemsAndInstructionWithThreeItems() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(2, "abrir");
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(3, "abrir");
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("abrir caja puerta gato");
        ArrayList<String> itemsIDFromAction = gameAction.getItemsID();

        assertTrue(gameAction.isASupportedAction() && itemsIDFromAction.size() == 3);
    }

    @Test
    public void testAddASupportedActionAndParserAsCorrect() {
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("cerrar puerta cofre");
        assertFalse(gameAction.isASupportedAction());

        SupportedAction supportedAction = new SupportedAction(2, "cerrar");
        gameParser.addSupportedAction(supportedAction);

        gameAction = gameParser.parseInstruction("cerrar puerta cofre");
        assertTrue(gameAction.isASupportedAction());
    }
}