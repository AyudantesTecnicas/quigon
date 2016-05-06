import creation.GameCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreationTests {

    @Test
    public void getNameCorrect(){
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().getName(), "OpenDoor");
    }
}