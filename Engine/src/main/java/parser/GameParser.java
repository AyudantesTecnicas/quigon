package parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class GameParser {

    private ArrayList<SupportedAction> supportedActions;

    public GameParser(ArrayList<SupportedAction> possibleActions) {

        this.supportedActions = possibleActions;
    }

    public GameParser() {
        this.supportedActions = new ArrayList<>();
    }

    public void addSupportedAction(SupportedAction supportedAction) {
        if (!this.supportedActions.contains(supportedAction)) {
            this.supportedActions.add(supportedAction);
        }
    }

    public void setSupportedActions(ArrayList<SupportedAction> possibleActions) {
        supportedActions = possibleActions;
    }

    public GameAction parseInstruction(String message) {
        GameAction actionToReturn;
        String instruction = message.toLowerCase();
        SupportedAction supportedAction = this.getSupportedAction(instruction);
        if (supportedAction != null) { //parse current instruction
            //create a supported GameAction
            ArrayList<String> itemsIDArray;
            if (supportedAction.getNumberOfItemsAffected() == 0) {
                itemsIDArray = new ArrayList<>();
            } else {
                String itemsString = instruction.substring(supportedAction.getActionID().length() + 1);
                itemsIDArray = new ArrayList<>(Arrays.asList(itemsString.split(" ")));
            }
            actionToReturn = new GameAction(supportedAction.getActionID(), itemsIDArray);
        } else {
            //create an unsupported GameAction
            actionToReturn = new GameAction("Unsupported Action");
        }
        return actionToReturn;
    }

    private SupportedAction getSupportedAction(String actionID) {
        Iterator<SupportedAction> iterator = supportedActions.iterator();
        Boolean supported = false;
        SupportedAction auxSupportedAction = null;
        while (iterator.hasNext() && !supported) {
            auxSupportedAction = iterator.next();
            supported = auxSupportedAction.isEqual(actionID);
        }
        if (supported) {
            return auxSupportedAction;
        } else {
            return null;
        }

    }
}
