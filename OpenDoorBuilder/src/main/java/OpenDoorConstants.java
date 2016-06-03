import java.util.ArrayList;

@SuppressWarnings("CPD-START")
public class OpenDoorConstants {
    //Elements
    public static final int numberOfPlayers = 1;
    public static final String door = "door";
    public static final String key = "key";
    public static final String room1 = "room1";
    public static final String room2 = "room2";

    //States
    public static final String opened = "open";
    public static final String closed = "close";

    //Actions
    public static final String open = "open";
    public static final String pick = "pick";

    //Rules
    public static final String doorOpened = "Door is open";
    public static final String keyNotInRoom1 = "Key is't in room 1.";
    public static final String notWon = "it's a pitty";
    public static final String room2Locked = "Ey! Where do you go?! Room 2 is locked.";

    //Moves
    public static final String openDoor = "You enter room 2.";
    public static final String pickKey = "There you go!";
}
