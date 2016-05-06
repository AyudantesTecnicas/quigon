import creation.GameCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CreationalTests {

    @Test
    public void getNameCorrect(){
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().getName(), "OpenDoor");
    }
}