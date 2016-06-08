import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class OpenDoor2Test {
    @Test
    public void testWinPlayer2GameOpenDoor2() {
        GameBuilderImp gameBuilderImp = new OpenDoor2Builder();
        Game openDoor2 = gameBuilderImp.build();
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = "1:" + od2Constants.open + " " + od2Constants.box;
        assertEquals(openDoor2.receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyCommand = "1:" + od2Constants.pick + " " + od2Constants.key;
        assertEquals(openDoor2.receiveCommands(pickKeyCommand), od2Constants.pickKey);
        String openDoorCommand = "1:" + od2Constants.open + " " + od2Constants.door;
        assertEquals(openDoor2.receiveCommands(openDoorCommand), gameBuilderImp.winText);
    }

    @Test
    public void testCantOpenDoorWhenOtherPlayerHasKey() {
        GameBuilderImp gameBuilderImp = new OpenDoor2Builder();
        Game openDoor2 = gameBuilderImp.build();
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = "1:" + od2Constants.open + " " + od2Constants.box;
        assertEquals(openDoor2.receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyCommand = "1:" + od2Constants.pick + " " + od2Constants.key;
        assertEquals(openDoor2.receiveCommands(pickKeyCommand), od2Constants.pickKey);
        String openDoorCommand = "0:" + od2Constants.open + " " + od2Constants.door;
        assertEquals(openDoor2.receiveCommands(openDoorCommand), od2Constants.missingKey);
    }

    @Test
    public void testCantPickElementFromOtherPlayer() {
        GameBuilderImp gameBuilderImp = new OpenDoor2Builder();
        Game openDoor2 = gameBuilderImp.build();
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = "1:" + od2Constants.open + " " + od2Constants.box;
        assertEquals(openDoor2.receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyPlayer2Command = "1:" + od2Constants.pick + " " + od2Constants.key;
        assertEquals(openDoor2.receiveCommands(pickKeyPlayer2Command), od2Constants.pickKey);
        String pickKeyPlayer1Command = "0:" + od2Constants.pick + " " + od2Constants.key;
        assertEquals(openDoor2.receiveCommands(pickKeyPlayer1Command), "object not found");
    }
}
