import creation.GameCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBehaviourTests {
    @Test
    public void gameTestOpenDoorPickKey() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().receiveCommands("pick key"), "There you go!");
    }

    @Test
    public void completeGameTestOpenDoorPickKeyAndOpenDoor() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().receiveCommands("pick key"), "There you go!");
        assertEquals(gameCreator.getGame().receiveCommands("open door"), "You won the game!");
    }

    @Test
    public void gameTestFetchQuest() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("FetchQuest");
        assertEquals(gameCreator.getGame().receiveCommands("pick stick"), "You won the game!");
    }

//    @Test
//    public void gameTestWolfSheepCabbage() {
//        GameCreator gameCreator = new GameCreator();
//        gameCreator.createGame("WolfSheep");
//        assertEquals(gameCreator.getGame().receiveCommands("take cabbage"), "Ok");
//        assertEquals(gameCreator.getGame().receiveCommands("cross north-shore"), "the wolf is on the south-shore");
//    }

    @Test
    public void gameTestCursedObject() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        assertEquals(gameCreator.getGame().receiveCommands("pick object"), "Ohoh, you have picked a cursed object =( ");
        assertEquals(gameCreator.getGame().receiveCommands("open door"), "There is another room! - Room 1 -");
        assertEquals(gameCreator.getGame().receiveCommands("talk to thief"), "The thief have robbed you!!!");
        assertEquals(gameCreator.getGame().receiveCommands("open golden_door"), "You won the game!");
    }

    @Test
    public void gameTestCursedObjectFail() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        assertEquals(gameCreator.getGame().receiveCommands("open door"), "You need an object");
        assertEquals(gameCreator.getGame().receiveCommands("pick object"), "Ohoh, you have picked a cursed object =( ");
        assertEquals(gameCreator.getGame().receiveCommands("open door"), "There is another room! - Room 1 -");
        assertEquals(gameCreator.getGame().receiveCommands("open golden_door"), "You can't go to the next room");
        assertEquals(gameCreator.getGame().receiveCommands("talk to thief"), "The thief have robbed you!!!");
        assertEquals(gameCreator.getGame().receiveCommands("open golden_door"), "You won the game!");
    }
}
