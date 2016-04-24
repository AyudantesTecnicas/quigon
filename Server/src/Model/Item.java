package Model;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A basic component.
 * It can execute actions and have got a state.
 */
public class Item {
    private String identifier;
    private String state;   // This will be a class maybe
    private ArrayList<String> actions; // This will be an Action Class array
    private ArrayList<String> states; // This will be a State Class array

    public Item(String identifier) {
        this.identifier = identifier;
    }

    public Item(String identifier, ArrayList<String> validActions) {
        this.identifier = identifier;
        this.actions = validActions;
    }

    public void execute(String action) throws Exception{
        boolean haveExecute = false;
        Iterator<String> it = actions.iterator();
        while(it.hasNext()) {
            String currentActon = it.next();
            if (currentActon == action) {
                // execute action with state
                haveExecute = true;
            }
        }

        if (!haveExecute){
            throw new Exception("No valid action");
        }
    }
}
