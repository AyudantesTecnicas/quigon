package games.constants;

/**
 * Created by francisco on 5/11/16.
 */
public class CursedObjectConstants {
    //Elements
    public final String character = "character";
    public final String cursedObject = "object";
    public final String door0to1 = "door";
    public final String door1to2 = "golden_door";
    public final String room0 = "room0";
    public final String room1 = "room1";
    public final String room2 = "room2";
    public final String thief = "thief";

    //States
    public final String opened = "Open";

    //Actions
    public final String open = "open";
    public final String pick = "pick";
    public final String talkTo = "talk to";

    //Rules
    public final String hasObject = "You can't take that!!";
    public final String notWon = "it's a pitty";
    public final String missingObject = "You need an object";
    public final String thiefNeedsObject = "You can't go to the next room";
    public final String wrongRoom = "You are in other room";

    //Moves
    public final String goToRoom1 = "There is another room! - Room 1 -";
    public final String goToRoom2 = "There is another room! - Room 2 -";
    public final String pickObject = "Ohoh, you have picked a cursed object =( ";
    public final String talkThief = "The thief has robbed you!!!";
}
