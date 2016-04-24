package GameParser;

/**
 * Created by nicolas on 24/04/16.
 */

import java.util.ArrayList;
import java.util.Iterator;

public class GameParser {

    private ArrayList<String> supportedActions;
    public GameParser (ArrayList<String> possibleActions) {
        this.supportedActions = possibleActions;
    }

    public GameAction parseInstrucction(String instrucction) {
        GameAction actionToReturn = null;
        if (this.isASupportedAction(instrucction)){ //parse current instruction
            //create a supported GameAction

        } else {
            //create an unsupported GameAction
        }
        return  actionToReturn;
    }

    private Boolean isASupportedAction(String actionID){
        Iterator<String> iterator = supportedActions.iterator();
        Boolean supported = false;
        while (iterator.hasNext()) {
            supported = (actionID.equals(iterator.next()));
        }

        return  supported;
    }
}
