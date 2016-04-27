package GameParser;

import java.util.ArrayList;

/**
 * Created by nicolas on 24/04/16.
 */
public class GameAction {
    private Boolean supportedAction;
    private String actionID;
    private ArrayList<String> itemsID;
    private String message;

    public GameAction (String actionID , ArrayList<String> itemsID) {
        this.actionID = actionID;
        this.itemsID = itemsID;
        this.supportedAction = true;
        this.message = "Correct Action"; // This could be changed
    }

    public GameAction(String message){
        this.message = message;
        this.supportedAction = false;
        this.actionID = "";
        this.itemsID = null;
    }

    public GameAction() {
        this.message = "Unsupported Action";
        this.supportedAction = false;
        this.actionID = "";
        this.itemsID = null;
    }

    public String getActionID() {
        return this.actionID;
    }

    public ArrayList<String> getItemsID() {
        return this.itemsID;
    }

    public Boolean isASupportedAction() {
        return this.supportedAction;
    }

    public String getMessage() {
        return this.message;
    }
}
