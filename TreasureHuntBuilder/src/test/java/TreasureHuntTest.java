import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class TreasureHuntTest {
    @Test
    public void gameTestTreasureHunt() {
        GameBuilderImp gameBuilderImp = new TreasureHuntBuilder();
        Game TreasureHunt = gameBuilderImp.build();
        TreasureHuntConstants wsConstants = new TreasureHuntConstants();

        String openDoorWCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorWC;
        assertEquals(TreasureHunt.receiveCommands(openDoorWCCommand), TreasureHuntConstants.movedToCenter);

        String openBoxCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.boxC;
        assertEquals(TreasureHunt.receiveCommands(openBoxCCommand), TreasureHuntConstants.openBox);
        String pickKeySCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyS;
        assertEquals(TreasureHunt.receiveCommands(pickKeySCommand), TreasureHuntConstants.pickKey);
        String unlockDoorCSCommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCS;
        assertEquals(TreasureHunt.receiveCommands(unlockDoorCSCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCSCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCS;
        assertEquals(TreasureHunt.receiveCommands(openDoorCSCommand), TreasureHuntConstants.movedToSouth);

        String openTrunkSCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkS;
        assertEquals(TreasureHunt.receiveCommands(openTrunkSCommand), TreasureHuntConstants.openTrunk);
        String pickAntidote1Command = TreasureHuntConstants.pick + " " + TreasureHuntConstants.antidote1;
        assertEquals(TreasureHunt.receiveCommands(pickAntidote1Command), TreasureHuntConstants.pickAntidote);
        String dropKeySCommand = TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyS;
        assertEquals(TreasureHunt.receiveCommands(dropKeySCommand), TreasureHuntConstants.dropKey);
        String pickKeyECommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyE;
        assertEquals(TreasureHunt.receiveCommands(pickKeyECommand), TreasureHuntConstants.pickKey);
        String openDoorSCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorSC;
        assertEquals(TreasureHunt.receiveCommands(openDoorSCCommand), TreasureHuntConstants.movedToCenter);

        String unlockDoorCECommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCE;
        assertEquals(TreasureHunt.receiveCommands(unlockDoorCECommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCE;
        assertEquals(TreasureHunt.receiveCommands(openDoorCECommand), TreasureHuntConstants.movedToEast);

        String openTrunkECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkE;
        assertEquals(TreasureHunt.receiveCommands(openTrunkECommand), TreasureHuntConstants.openTrunk);
        String openBoxECommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.boxE;
        assertEquals(TreasureHunt.receiveCommands(openBoxECommand), TreasureHuntConstants.openBox);
        String dropKeyECommand = TreasureHuntConstants.drop + " " + TreasureHuntConstants.keyE;
        assertEquals(TreasureHunt.receiveCommands(dropKeyECommand), TreasureHuntConstants.dropKey);
        String pickKeyNCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.keyN;
        assertEquals(TreasureHunt.receiveCommands(pickKeyNCommand), TreasureHuntConstants.pickKey);
        String openDoorECCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorEC;
        assertEquals(TreasureHunt.receiveCommands(openDoorECCommand), TreasureHuntConstants.movedToCenter);

        String unlockDoorCNCommand = TreasureHuntConstants.unlock + " " + TreasureHuntConstants.doorCN;
        assertEquals(TreasureHunt.receiveCommands(unlockDoorCNCommand), TreasureHuntConstants.doorUnlocked);
        String openDoorCNCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCN;
        assertEquals(TreasureHunt.receiveCommands(openDoorCNCommand), TreasureHuntConstants.movedToNorth);

        String openTrunkNCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.trunkN;
        assertEquals(TreasureHunt.receiveCommands(openTrunkNCommand), TreasureHuntConstants.openPoisonTrunk);
        String useAntidoteCommand = TreasureHuntConstants.use + " " + TreasureHuntConstants.antidote1;
        assertEquals(TreasureHunt.receiveCommands(useAntidoteCommand), TreasureHuntConstants.healed);
        String pickTreasureCommand = TreasureHuntConstants.pick + " " + TreasureHuntConstants.treasure;
        assertEquals(TreasureHunt.receiveCommands(pickTreasureCommand), TreasureHuntConstants.pickTreasure);
        String openDoorNCCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorNC;
        assertEquals(TreasureHunt.receiveCommands(openDoorNCCommand), TreasureHuntConstants.movedToCenter);

        String openDoorCWCommand = TreasureHuntConstants.open + " " + TreasureHuntConstants.doorCW;
        assertEquals(TreasureHunt.receiveCommands(openDoorCWCommand), gameBuilderImp.winText);
    }
}
