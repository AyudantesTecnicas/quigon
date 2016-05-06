import org.junit.Test;
import parser.GameAction;
import parser.GameParser;
import parser.SupportedAction;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class GameParserTests {
    @Test
    public void testParseASupportedActionAndCheckIfItsCorrectActionID() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);
        GameAction aGameAction = aGameParser.parseInstruction("abrir puerta");
        assertTrue(aGameAction.getActionID().equals("abrir") && aGameAction.getItemsID().size() == aSupportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseInstructionWithUpperCaseAction() {
        SupportedAction aSupportedAction = new SupportedAction(1, "ABRIR");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);
        GameAction aGameAction = aGameParser.parseInstruction("abrir puerta");
        assertTrue(aGameAction.getActionID().equals("abrir") && aGameAction.getItemsID().size() == aSupportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseInstructionWithUpperCaseMessage() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);
        GameAction aGameAction = aGameParser.parseInstruction("ABRIR puerta");
        assertTrue(aGameAction.getActionID().equals("abrir") && aGameAction.getItemsID().size() == aSupportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseASupportedActionButNotCorrectNumberOfItems() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);
        GameAction aGameAction = aGameParser.parseInstruction("abrir puerta caja");
        assertFalse(aGameAction.isASupportedAction());
    }

    @Test
    public void testSupportAnActionIDWithMoreThanOneWord() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir fuerte");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);
        GameAction aGameAction = aGameParser.parseInstruction("abrir fuerte puerta");
        assertTrue(aGameAction.getActionID().equals("abrir fuerte") && aGameAction.getItemsID().size() == aSupportedAction.getNumberOfItemsAffected());
    }

    @Test
    public void testParseASupportedActionAndCheckCorrectsItemsID() {
        SupportedAction aSupportedAction = new SupportedAction(3, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("abrir puerta caja cofre");
        ArrayList<String> itemsIDFromAction = aGameAction.getItemsID();
        String firstItem = itemsIDFromAction.get(0);
        String secondItem = itemsIDFromAction.get(1);
        String thirdItem = itemsIDFromAction.get(2);

        assertTrue(firstItem.equals("puerta") && secondItem.equals("caja") && thirdItem.equals("cofre"));
    }

    @Test
    public void testParseASupportedActionWithNoItemsAffected() {
        SupportedAction aSupportedAction = new SupportedAction(0, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("abrir");
        ArrayList<String> itemsIDFromAction = aGameAction.getItemsID();

        assertTrue(aGameAction.isASupportedAction() && itemsIDFromAction.size() == 0);
    }

    @Test
    public void testTwoActionsWithSameIDButDifferentExpectedItems() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(2, "abrir");
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(3, "abrir");
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("abrir caja");
        ArrayList<String> itemsIDFromAction = aGameAction.getItemsID();

        assertTrue(aGameAction.isASupportedAction() && itemsIDFromAction.size() == 1);
    }

    @Test
    public void testTwoActionsWithSameIDButDifferentExpectedItemsAndInstructionWithThreeItems() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(2, "abrir");
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(3, "abrir");
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("abrir caja puerta gato");
        ArrayList<String> itemsIDFromAction = aGameAction.getItemsID();

        assertTrue(aGameAction.isASupportedAction() && itemsIDFromAction.size() == 3);
    }

    @Test
    public void testThreeSupportedActionButClientActionIsNotSupported() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(2, "cerrar");
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(3, "romper");
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("mirar");

        assertFalse(aGameAction.isASupportedAction());
    }

    @Test
    public void testThreeSupportedActionAndClientActionIsSupported() {
        SupportedAction aSupportedAction = new SupportedAction(1, "abrir");
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(2, "cerrar");
        supportedActions.add(aSupportedAction);
        aSupportedAction = new SupportedAction(3, "romper");
        supportedActions.add(aSupportedAction);
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("cerrar puerta cofre");
        ArrayList<String> itemsIDFromAction = aGameAction.getItemsID();

        assertTrue(aGameAction.isASupportedAction() && itemsIDFromAction.size() == 2 && aGameAction.getActionID().equals("cerrar"));
    }

    @Test
    public void testAddASupportedActionAndParserAsCorrect() {
        ArrayList<SupportedAction> supportedActions = new ArrayList<>();
        GameParser aGameParser = new GameParser(supportedActions);

        GameAction aGameAction = aGameParser.parseInstruction("cerrar puerta cofre");
        assertFalse(aGameAction.isASupportedAction());

        SupportedAction aSupportedAction = new SupportedAction(2, "cerrar");
        aGameParser.addSupportedAction(aSupportedAction);

        aGameAction = aGameParser.parseInstruction("cerrar puerta cofre");
        assertTrue(aGameAction.isASupportedAction());
    }
}
