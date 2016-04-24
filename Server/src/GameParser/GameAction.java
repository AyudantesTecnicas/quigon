package GameParser;

/**
 * Created by nicolas on 24/04/16.
 */
public class GameAction {
    private Boolean supportedAction;
    private String actionID;
    private String itemID;
    private String message;

    public GameAction (String actionID , String itemID){
        this.actionID = actionID;
        this.itemID = itemID;
        this.supportedAction = true;
        this.message = "Correct Action"; // This could be changed
    }
}
