import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class TreasureHuntTest {

    GameBuilderImp gameBuilderImp;
    Game treasureHunt;

    public void gameMoveToSouth(){
        gameBuilderImp = new TreasureHuntBuilder();
        treasureHunt = gameBuilderImp.build();
        TreasureHuntConstants wsConstants = new TreasureHuntConstants();

        String openDoorWCCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorWC;
        assertEquals(treasureHunt.receiveCommands(openDoorWCCommand), TreasureHuntConstants.movedToCenter);

        String openBoxCCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.boxC;
        assertEquals(treasureHunt.receiveCommands(openBoxCCommand), TreasureHuntConstants.openBox);
        String pickKeySCommand = "0:" + TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyS;
        assertEquals(treasureHunt.receiveCommands(pickKeySCommand), TreasureHuntConstants.pickKey);
        String unlockDoorCSCommand = "0:" + TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCS;
        assertEquals(treasureHunt.receiveCommands(unlockDoorCSCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCSCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCS;
        assertEquals(treasureHunt.receiveCommands(openDoorCSCommand), TreasureHuntConstants.movedToSouth);
        String openTrunkSCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkS;
        assertEquals(treasureHunt.receiveCommands(openTrunkSCommand), TreasureHuntConstants.openTrunk);
        String pickAntidote1Command = "0:" + TreasureHuntConstants.pick + " " + TreasureHuntConstants.antidote1;
        assertEquals(treasureHunt.receiveCommands(pickAntidote1Command), TreasureHuntConstants.pickAntidote);
    }

    public void gameTestDropKey() {
        String dropKeySCommand = "0:" + TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyS;
        assertEquals(treasureHunt.receiveCommands(dropKeySCommand), TreasureHuntConstants.dropKey);
        String pickKeyECommand = "0:" + TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyE;
        assertEquals(treasureHunt.receiveCommands(pickKeyECommand), TreasureHuntConstants.pickKey);
        String openDoorSCCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorSC;
        assertEquals(treasureHunt.receiveCommands(openDoorSCCommand), TreasureHuntConstants.movedToCenter);

        String unlockDoorCECommand = "0:" + TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCE;
        assertEquals(treasureHunt.receiveCommands(unlockDoorCECommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCECommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCE;
        assertEquals(treasureHunt.receiveCommands(openDoorCECommand), TreasureHuntConstants.movedToEast);

        String openTrunkECommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkE;
        assertEquals(treasureHunt.receiveCommands(openTrunkECommand), TreasureHuntConstants.openTrunk);
        String openBoxECommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.boxE;
        assertEquals(treasureHunt.receiveCommands(openBoxECommand), TreasureHuntConstants.openBox);
        String dropKeyECommand = "0:" + TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyE;
        assertEquals(treasureHunt.receiveCommands(dropKeyECommand), TreasureHuntConstants.dropKey);
    }

    public void gameTestKeys() {
        String pickKeyNCommand = "0:" + TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyN;
        assertEquals(treasureHunt.receiveCommands(pickKeyNCommand), TreasureHuntConstants.pickKey);
        String openDoorECCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorEC;
        assertEquals(treasureHunt.receiveCommands(openDoorECCommand), TreasureHuntConstants.movedToCenter);
        String unlockDoorCNCommand = "0:" + TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCN;
        assertEquals(treasureHunt.receiveCommands(unlockDoorCNCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCNCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCN;
        assertEquals(treasureHunt.receiveCommands(openDoorCNCommand), TreasureHuntConstants.movedToNorth);

        String openTrunkNCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkN;
        assertEquals(treasureHunt.receiveCommands(openTrunkNCommand), TreasureHuntConstants.openPoisonTrunk);
        String useAntidoteCommand = "0:" + TreasureHuntConstants.use + " " + TreasureHuntConstants.antidote1;
        assertEquals(treasureHunt.receiveCommands(useAntidoteCommand), TreasureHuntConstants.healed);
        String pickTreasureCommand = "0:" + TreasureHuntConstants.pick + " " + TreasureHuntConstants.treasure;
        assertEquals(treasureHunt.receiveCommands(pickTreasureCommand), TreasureHuntConstants.pickTreasure);
        String openDoorNCCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorNC;
        assertEquals(treasureHunt.receiveCommands(openDoorNCCommand), TreasureHuntConstants.movedToCenter);
    }

    @Test
    public void gameTestTreasureHunt() {
        gameMoveToSouth();
        gameTestDropKey();
        gameTestKeys();
        String openDoorCWCommand = "0:" + TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCW;
        assertEquals(treasureHunt.receiveCommands(openDoorCWCommand), gameBuilderImp.winText);
    }
}
