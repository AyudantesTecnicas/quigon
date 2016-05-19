import creation.GameCreator;
import games.TreasureHunt;
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

    @Test
    public void gameTestTreasureHunt() {
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("TreasureHunt");
        String victoryMssg = "You won the game!";

        String openDoorWCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorWC;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorWCCommand), TreasureHuntConstants.movedToCenter);

        String openBoxCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.boxC;
        assertEquals(gameCreator.getGame().receiveCommands(openBoxCCommand), TreasureHuntConstants.openBox);
        String pickKeySCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyS;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeySCommand), TreasureHuntConstants.pickKey);
        String unlockDoorCSCommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCS;
        assertEquals(gameCreator.getGame().receiveCommands(unlockDoorCSCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCSCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCS;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCSCommand), TreasureHuntConstants.movedToSouth);

        String openTrunkSCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkS;
        assertEquals(gameCreator.getGame().receiveCommands(openTrunkSCommand), TreasureHuntConstants.openTrunk);
        String pickAntidote1Command = TreasureHuntConstants.pick + " " + TreasureHuntConstants.antidote1;
        assertEquals(gameCreator.getGame().receiveCommands(pickAntidote1Command), TreasureHuntConstants.pickAntidote);
        String dropKeySCommand = TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyS;
        assertEquals(gameCreator.getGame().receiveCommands(dropKeySCommand), TreasureHuntConstants.dropKey);
        String pickKeyECommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyE;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyECommand), TreasureHuntConstants.pickKey);
        String openDoorSCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorSC;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorSCCommand), TreasureHuntConstants.movedToCenter);

        String unlockDoorCECommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCE;
        assertEquals(gameCreator.getGame().receiveCommands(unlockDoorCECommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCE;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCECommand), TreasureHuntConstants.movedToEast);

        String openTrunkECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkE;
        assertEquals(gameCreator.getGame().receiveCommands(openTrunkECommand), TreasureHuntConstants.openTrunk);
        String openBoxECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.boxE;
        assertEquals(gameCreator.getGame().receiveCommands(openBoxECommand), TreasureHuntConstants.openBox);
        String dropKeyECommand = TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyE;
        assertEquals(gameCreator.getGame().receiveCommands(dropKeyECommand), TreasureHuntConstants.dropKey);
        String pickKeyNCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyN;
        assertEquals(gameCreator.getGame().receiveCommands(pickKeyNCommand), TreasureHuntConstants.pickKey);
        String openDoorECCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorEC;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorECCommand), TreasureHuntConstants.movedToCenter);

        String unlockDoorCNCommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCN;
        assertEquals(gameCreator.getGame().receiveCommands(unlockDoorCNCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCNCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCN;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCNCommand), TreasureHuntConstants.movedToNorth);

        String openTrunkNCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkN;
        assertEquals(gameCreator.getGame().receiveCommands(openTrunkNCommand), TreasureHuntConstants.openPoisonTrunk);
        String useAntidoteCommand = TreasureHuntConstants.use + " " + TreasureHuntConstants.antidote1;
        assertEquals(gameCreator.getGame().receiveCommands(useAntidoteCommand), TreasureHuntConstants.healed);
        String pickTreasureCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.treasure;
        assertEquals(gameCreator.getGame().receiveCommands(pickTreasureCommand), TreasureHuntConstants.pickTreasure);
        String openDoorNCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorNC;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorNCCommand), TreasureHuntConstants.movedToCenter);

        String openDoorCWCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCW;
        assertEquals(gameCreator.getGame().receiveCommands(openDoorCWCommand), victoryMssg);
    }
}
