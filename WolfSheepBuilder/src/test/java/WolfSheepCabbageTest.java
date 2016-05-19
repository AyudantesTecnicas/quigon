import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
@SuppressWarnings("CPD-START")
public class WolfSheepCabbageTest {
    @Test
    public void gameTestWolfSheepCabbage() {
        GameBuilderImp gameBuilderImp = new WolfSheepBuilder();
        Game WolfSheep = gameBuilderImp.build();
        WolfSheepConstants wsConstants = new WolfSheepConstants();
        String takeCabbageCommand = wsConstants.take + " " + wsConstants.cabbage;
        assertEquals(WolfSheep.receiveCommands(takeCabbageCommand), wsConstants.ok);
        String crossNorthCommand = wsConstants.cross + " " + wsConstants.northShore;
        assertEquals(WolfSheep.receiveCommands(crossNorthCommand), wsConstants.wolfOnSouth);
        String leaveCabbageCommand = wsConstants.leave + " " + wsConstants.cabbage;
        assertEquals(WolfSheep.receiveCommands(leaveCabbageCommand), wsConstants.ok);
        String takeSheepCommand = wsConstants.take + " " + wsConstants.sheep;
        assertEquals(WolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveSheepCommand = wsConstants.leave + " " + wsConstants.sheep;
        assertEquals(WolfSheep.receiveCommands(leaveSheepCommand), wsConstants.ok);
        String crossSouthCommand = wsConstants.cross + " " + wsConstants.southShore;
        assertEquals(WolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(WolfSheep.receiveCommands(takeCabbageCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(WolfSheep.receiveCommands(leaveCabbageCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(WolfSheep.receiveCommands(leaveSheepCommand), wsConstants.ok);
        String takeWolfCommand = wsConstants.take + " " + wsConstants.wolf;
        assertEquals(WolfSheep.receiveCommands(takeWolfCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        String leaveWolfCommand = wsConstants.leave + " " + wsConstants.wolf;
        assertEquals(WolfSheep.receiveCommands(leaveWolfCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossSouthCommand), wsConstants.youCrossed);
        assertEquals(WolfSheep.receiveCommands(takeSheepCommand), wsConstants.ok);
        assertEquals(WolfSheep.receiveCommands(crossNorthCommand), wsConstants.youCrossed);
        assertEquals(WolfSheep.receiveCommands(leaveSheepCommand), gameBuilderImp.winText);
    }
}
