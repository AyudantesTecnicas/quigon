import creation.GameCreator;
import games.constants.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameBehaviourTests {
    @Test
    public void gameTestOpenDoorPickKey() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        OpenDoorConstants odConstants = new OpenDoorConstants();
        String pickKeyCommand = odConstants.pick + " " + odConstants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), odConstants.pickKey);
    }

    @Test
    public void completeGameTestOpenDoorPickKeyAndOpenDoor() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        OpenDoorConstants odConstants = new OpenDoorConstants();
        String pickKeyCommand = odConstants.pick + " " + odConstants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), odConstants.pickKey);
        String openDoorCommand = odConstants.open + " " + odConstants.door;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCommand), "You won the game!");
    }

    @Test
    public void completeGameTestOpenDoor2OpenBoxAndPickKeyAndOpenDoor() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor2");
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = od2Constants.open + " " + od2Constants.box;
        assertEquals(gameCreator.getGame().receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyCommand = od2Constants.pick + " " + od2Constants.key;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyCommand), od2Constants.pickKey);
        String openDoorCommand = od2Constants.open + " " + od2Constants.door;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCommand), "You won the game!");
    }

    @Test
    public void gameTestFetchQuest() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("FetchQuest");
        FetchQuestConstants fqConstants = new FetchQuestConstants();
        String pickStickCommand = fqConstants.pick + " " + fqConstants.stick;
        assertEquals(gameCreator.getGame().receiveCommands(pickStickCommand), "You won the game!");
    }

    @Test
    public void gameTestWolfSheepCabbage() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("WolfSheep");
        WolfSheepConstants wsConstants = new WolfSheepConstants();
        String takeCabbageCommand = wsConstants.take + " " + wsConstants.cabbage;
        assertEquals(gameCreator.getGame().receiveCommands(takeCabbageCommand), wsConstants.ok);
        String crossNorthCommand = wsConstants.cross + " " + wsConstants.northShore;
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), wsConstants.wolfOnSouth);
        String leaveCabbageCommand = wsConstants.leave + " " + wsConstants.cabbage;
        assertEquals(gameCreator.getGame().receiveCommands(leaveCabbageCommand), wsConstants.ok);
        String takeSheepCommand = wsConstants.take + " " + wsConstants.sheep;
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveSheepCommand = wsConstants.leave + " " + wsConstants.sheep;
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), wsConstants.ok);
        String crossSouthCommand = wsConstants.cross + " " + wsConstants.southShore;
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(takeCabbageCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveCabbageCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), wsConstants.ok);
        String takeWolfCommand = wsConstants.take + " " + wsConstants.wolf;
        assertEquals(gameCreator.getGame().receiveCommands(takeWolfCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveWolfCommand = wsConstants.leave + " " + wsConstants.wolf;
        assertEquals(gameCreator.getGame().receiveCommands(leaveWolfCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(gameCreator.getGame().receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(gameCreator.getGame().receiveCommands(leaveSheepCommand), "You won the game!");
    }

    @Test
    public void gameTestCursedObject() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        CursedObjectConstants coConstants = new CursedObjectConstants();
        String pickObjectCommand = coConstants.pick + " " + coConstants.cursedObject;
        assertEquals(gameCreator.getGame().receiveCommands(pickObjectCommand), coConstants.pickObject);
        String openDoor1Command = coConstants.open + " " + coConstants.door0to1;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), coConstants.goToRoom1);
        String talkToThiefCommand = coConstants.talkTo + " " + coConstants.thief;
        assertEquals(gameCreator.getGame().receiveCommands(talkToThiefCommand), coConstants.talkThief);
        String openDoor2Command = coConstants.open + " " + coConstants.door1to2;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), "You won the game!");
    }

    @Test
    public void gameTestCursedObjectFail() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("CursedObject");
        CursedObjectConstants coConstants = new CursedObjectConstants();
        String openDoor1Command = coConstants.open + " " + coConstants.door0to1;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), coConstants.missingObject);
        String pickObjectCommand = coConstants.pick + " " + coConstants.cursedObject;
        assertEquals(gameCreator.getGame().receiveCommands(pickObjectCommand), coConstants.pickObject);
        assertEquals(gameCreator.getGame().receiveCommands(openDoor1Command), coConstants.goToRoom1);
        String openDoor2Command = coConstants.open + " " + coConstants.door1to2;
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), coConstants.thiefNeedsObject);
        String talkToThiefCommand = coConstants.talkTo + " " + coConstants.thief;
        assertEquals(gameCreator.getGame().receiveCommands(talkToThiefCommand), coConstants.talkThief);
        assertEquals(gameCreator.getGame().receiveCommands(openDoor2Command), "You won the game!");
    }
}
