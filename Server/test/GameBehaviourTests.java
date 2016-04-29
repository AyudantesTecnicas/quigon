import gameCreation.GameCreator;
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
    public void GameTestCursedObject(){
        GameCreator aGameCreator = new GameCreator();
        aGameCreator.createGame("CursedObject");
        assertEquals(aGameCreator.getGame().receiveCommands("pick object"),"Ohoh, you have picked a cursed object =( ");
        assertEquals(aGameCreator.getGame().receiveCommands("open door"),"There is another room! - Room 1 -");
        assertEquals(aGameCreator.getGame().receiveCommands("talk to thief"),"The thief have robbed you!!!");
        assertEquals(aGameCreator.getGame().receiveCommands("open golden_door"),"You won the game!");
    }

}
