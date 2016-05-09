import org.junit.Test;
import parser.GameAction;
import parser.GameParser;
import parser.SupportedAction;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GameParserTests {
    @Test
    public void testParseASupportedActionAndCheckIfItsCorrectActionID() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);
        GameAction gameAction = gameParser.parseInstruction("abrir puerta");
        assertTrue(gameAction.getActionID().equals("abrir")
                && gameAction.getItemsID().size() == supportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseInstructionWithUpperCaseAction() {
        SupportedAction supportedAction = new SupportedAction(1, "ABRIR");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);
        GameAction gameAction = gameParser.parseInstruction("abrir puerta");
        assertTrue(gameAction.getActionID().equals("abrir")
                && gameAction.getItemsID().size() == supportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseInstructionWithUpperCaseMessage() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);
        GameAction gameAction = gameParser.parseInstruction("ABRIR puerta");
        assertTrue(gameAction.getActionID().equals("abrir")
                && gameAction.getItemsID().size() == supportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseASupportedActionButNotCorrectNumberOfItems() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);
        GameAction gameAction = gameParser.parseInstruction("abrir puerta caja");
        assertFalse(gameAction.isASupportedAction());
    }

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
    public void testParseASupportedActionAndCheckCorrectsItemsID() {
        SupportedAction supportedAction = new SupportedAction(3, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("abrir puerta caja cofre");
        ArrayList<String> itemsIDFromAction = gameAction.getItemsID();
        String firstItem = itemsIDFromAction.get(0);
        String secondItem = itemsIDFromAction.get(1);
        String thirdItem = itemsIDFromAction.get(2);

        assertTrue(firstItem.equals("puerta") && secondItem.equals("caja") && thirdItem.equals("cofre"));
    }

    @Test
    public void testParseASupportedActionWithNoItemsAffected() {
        SupportedAction supportedAction = new SupportedAction(0, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("abrir");
        ArrayList<String> itemsIDFromAction = gameAction.getItemsID();

        assertTrue(gameAction.isASupportedAction() && itemsIDFromAction.size() == 0);
    }

    @Test
    public void testTwoActionsWithSameIDButDifferentExpectedItems() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(2, "abrir");
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(3, "abrir");
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("abrir caja");
        ArrayList<String> itemsIDFromAction = gameAction.getItemsID();

        assertTrue(gameAction.isASupportedAction() && itemsIDFromAction.size() == 1);
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
    public void testThreeSupportedActionButClientActionIsNotSupported() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(2, "cerrar");
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(3, "romper");
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("mirar");

        assertFalse(gameAction.isASupportedAction());
    }

    @Test
    public void testThreeSupportedActionAndClientActionIsSupported() {
        SupportedAction supportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(2, "cerrar");
        supportedActions.add(supportedAction);
        supportedAction = new SupportedAction(3, "romper");
        supportedActions.add(supportedAction);
        GameParser gameParser = new GameParser(supportedActions);

        GameAction gameAction = gameParser.parseInstruction("cerrar puerta cofre");
        ArrayList<String> itemsIDFromAction = gameAction.getItemsID();

        assertTrue(gameAction.isASupportedAction() && itemsIDFromAction.size() == 2 && gameAction.getActionID().equals("cerrar"));
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
