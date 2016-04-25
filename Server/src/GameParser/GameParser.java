package GameParser;

/**
 * Created by nicolas on 24/04/16.
 */

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Iterator;

public class GameParser {

    private ArrayList<SupportedAction> supportedActions;
    public GameParser (ArrayList<SupportedAction> possibleActions) {
        this.supportedActions = possibleActions;
    }

    public GameAction parseInstrucction(String instrucction) {
        GameAction actionToReturn = null;
        SupportedAction supportedAction = this.supportedAction(instrucction);
        if (supportedAction != null) { //parse current instruction
            //create a supported GameAction
            String itemsString = instrucction.substring(0,supportedAction.getActionID().length());
            String [] itemsID = itemsString.split(" ");
            actionToReturn = new GameAction(supportedAction.getActionID(), new ArrayList<String>(Arrays.asList(itemsString.split(" "))));
        } else {
            //create an unsupported GameAction
            actionToReturn = new GameAction();
        }
        return  actionToReturn;
    }

    private SupportedAction supportedAction(String actionID){
        Iterator<SupportedAction> iterator = supportedActions.iterator();
        Boolean supported = false;
        SupportedAction  auxSupportedAction = null;
        while (iterator.hasNext() && !supported) {
            auxSupportedAction = iterator.next();
            supported = auxSupportedAction.isEqual(actionID);
        }

        return  auxSupportedAction;
    }
}
