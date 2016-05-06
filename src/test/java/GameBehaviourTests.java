import creation.GameCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBehaviourTests {
    @Test
    public void GameTestOpenDoorPickKey(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("OpenDoor");
        assertEquals(aGameCreator.getGame().receiveCommands("pick key"),"There you go!");
    }

    @Test
    public void CompleteGameTestOpenDoorPickKeyAndOpenDoor(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("OpenDoor");
        assertEquals(aGameCreator.getGame().receiveCommands("pick key"),"There you go!");
        assertEquals(aGameCreator.getGame().receiveCommands("open door"),"You won the game!");
    }

    @Test
    public void GameTestFetchQuest(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("FetchQuest");
        assertEquals(aGameCreator.getGame().receiveCommands("pick stick"),"You won the game!");
    }

    @Test
    public void GameTestWolfSheepcabbage(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("WolfSheep");
        assertEquals(aGameCreator.getGame().receiveCommands("take cabbage"),"Ok");
        assertEquals(aGameCreator.getGame().receiveCommands("cross north-shore"),"the wolf is on the south-shore");
    }

    @Test
    public void GameTestCursedObject(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("CursedObject");
        assertEquals(aGameCreator.getGame().receiveCommands("pick object"),"Ohoh, you have picked a cursed object =( ");
        assertEquals(aGameCreator.getGame().receiveCommands("open door"),"There is another room! - Room 1 -");
        assertEquals(aGameCreator.getGame().receiveCommands("talk to thief"),"The thief have robbed you!!!");
        assertEquals(aGameCreator.getGame().receiveCommands("open golden_door"),"You won the game!");
    }

    @Test
    public void GameTestCursedObjectFail(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("CursedObject");
        assertEquals(aGameCreator.getGame().receiveCommands("open door"),"You need an object");
        assertEquals(aGameCreator.getGame().receiveCommands("pick object"),"Ohoh, you have picked a cursed object =( ");
        assertEquals(aGameCreator.getGame().receiveCommands("open door"),"There is another room! - Room 1 -");
        assertEquals(aGameCreator.getGame().receiveCommands("open golden_door"),"You can't go to the next room");
        assertEquals(aGameCreator.getGame().receiveCommands("talk to thief"),"The thief have robbed you!!!");
        assertEquals(aGameCreator.getGame().receiveCommands("open golden_door"),"You won the game!");
    }
}
