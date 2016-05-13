import creation.GameCreator;
import games.constants.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBehaviourTests {
    @Test
    public void gameTestOpenDoorPickKey() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        OpenDoorConstants ODConstants = new OpenDoorConstants();
        String pickKeyCommand = ODConstants.pick + " " + ODConstants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), ODConstants.pickKey);
    }

    @Test
    public void completeGameTestOpenDoorPickKeyAndOpenDoor() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        OpenDoorConstants ODConstants = new OpenDoorConstants();
        String pickKeyCommand = ODConstants.pick + " " + ODConstants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), ODConstants.pickKey);
        String openDoorCommand = ODConstants.open + " " + ODConstants.door;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCommand), "You won the game!");
    }

    @Test
    public void completeGameTestOpenDoor2OpenBoxAndPickKeyAndOpenDoor() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor2");
        OpenDoor2Constants ODConstants = new OpenDoor2Constants();
        String openBoxCommand = ODConstants.open + " " + ODConstants.box;
        assertEquals(gameCreator.getGame().receiveCommands(openBoxCommand), ODConstants.openBox);
        String pickKeyCommand = ODConstants.pick + " " + ODConstants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), ODConstants.pickKey);
        String openDoorCommand = ODConstants.open + " " + ODConstants.door;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCommand), "You won the game!");
    }

    @Test
    public void gameTestFetchQuest() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("FetchQuest");
        FetchQuestConstants FQConstants = new FetchQuestConstants();
        String pickStickCommand = FQConstants.pick + " " + FQConstants.stick;
        assertEquals(gameCreator.getGame().receiveCommands(pickStickCommand), "You won the game!");
    }

    @Test
    public void gameTestWolfSheepCabbage() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("WolfSheep");
        WolfSheepConstants WSConstants = new WolfSheepConstants();
        String takeCabbageCommand = WSConstants.take + " " + WSConstants.cabbage;
        assertEquals(gameCreator.getGame().receiveCommands(takeCabbageCommand), WSConstants.ok);
        String crossNorthCommand = WSConstants.cross + " " + WSConstants.northShore;
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), WSConstants.wolfOnSouth);
        String leaveCabbageCommand = WSConstants.leave + " " + WSConstants.cabbage;
        assertEquals(gameCreator.getGame().receiveCommands(leaveCabbageCommand), WSConstants.ok);
        String takeSheepCommand = WSConstants.take + " " + WSConstants.sheep;
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), WSConstants.youCrossed);
        String leaveSheepCommand = WSConstants.leave + " " + WSConstants.sheep;
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), WSConstants.ok);
        String crossSouthCommand = WSConstants.cross + " " + WSConstants.southShore;
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), WSConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(takeCabbageCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), WSConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveCabbageCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), WSConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), WSConstants.ok);
        String takeWolfCommand = WSConstants.take + " " + WSConstants.wolf;
        assertEquals(gameCreator.getGame().receiveCommands(takeWolfCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), WSConstants.youCrossed);
        String leaveWolfCommand = WSConstants.leave + " " + WSConstants.wolf;
        assertEquals(gameCreator.getGame().receiveCommands(leaveWolfCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), WSConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), WSConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), WSConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), "You won the game!");
    }

    @Test
    public void gameTestCursedObject() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        CursedObjectConstants COConstants = new CursedObjectConstants();
        String pickObjectCommand = COConstants.pick + " " + COConstants.cursedObject;
        assertEquals(gameCreator.getGame().receiveCommands(pickObjectCommand), COConstants.pickObject);
        String openDoor1Command = COConstants.open + " " + COConstants.door0to1;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), COConstants.goToRoom1);
        String talkToThiefCommand = COConstants.talkTo + " " + COConstants.thief;
        assertEquals(gameCreator.getGame().receiveCommands(talkToThiefCommand), COConstants.talkThief);
        String openDoor2Command = COConstants.open + " " + COConstants.door1to2;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), "You won the game!");
    }

    @Test
    public void gameTestCursedObjectFail() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        CursedObjectConstants COConstants = new CursedObjectConstants();
        String openDoor1Command = COConstants.open + " " + COConstants.door0to1;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), COConstants.missingObject);
        String pickObjectCommand = COConstants.pick + " " + COConstants.cursedObject;
        assertEquals(gameCreator.getGame().receiveCommands(pickObjectCommand), COConstants.pickObject);
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), COConstants.goToRoom1);
        String openDoor2Command = COConstants.open + " " + COConstants.door1to2;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), COConstants.thiefNeedsObject);
        String talkToThiefCommand = COConstants.talkTo + " " + COConstants.thief;
        assertEquals(gameCreator.getGame().receiveCommands(talkToThiefCommand), COConstants.talkThief);
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), "You won the game!");
    }
}
