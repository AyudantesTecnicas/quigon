import creation.Game;
import creation.GameBuilderImp;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OpenDoor2Test {
    @Test
    public void completeGameTestOpenDoor2OpenBoxAndPickKeyAndOpenDoor() {
        GameBuilderImp gameBuilderImp = new OpenDoor2Builder();
        Game OpenDoor2 = gameBuilderImp.build();
        OpenDoor2Constants od2Constants = new OpenDoor2Constants();
        String openBoxCommand = od2Constants.open + " " + od2Constants.box;
        assertEquals(OpenDoor2.receiveCommands(openBoxCommand), od2Constants.openBox);
        String pickKeyCommand = od2Constants.pick + " " + od2Constants.key;
        assertEquals(OpenDoor2.receiveCommands(pickKeyCommand), od2Constants.pickKey);
        String openDoorCommand = od2Constants.open + " " + od2Constants.door;
        assertEquals(OpenDoor2.receiveCommands(openDoorCommand), gameBuilderImp.winText);
    }
}
