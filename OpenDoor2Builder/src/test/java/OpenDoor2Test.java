import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoor2Test {
    @Test
    public void TestCompleteGameOpenDoor2WithOnePlayer() {
        GameBuilderImp gameBuilderImp = new OpenDoor2Builder();
        Game openDoor2 = gameBuilderImp.build();
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = "0:" + od2Constants.open + " " + od2Constants.box;
        assertEquals(openDoor2.receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyCommand = "0:" + od2Constants.pick + " " + od2Constants.key;
        assertEquals(openDoor2.receiveCommands(pickKeyCommand), od2Constants.pickKey);
        String openDoorCommand = "0:" + od2Constants.open + " " + od2Constants.door;
        assertEquals(openDoor2.receiveCommands(openDoorCommand), gameBuilderImp.winText);
    }
}
