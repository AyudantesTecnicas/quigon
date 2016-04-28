import gameCreation.GameCreator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class creationalTests {
    @Test
    public void amountOfRoomsIsCorrect(){
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().getRooms().size(), 2);
    }
    @Test
    public void amountOfItemsIsCorrect(){
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().getRooms().get(0).itemsSize(), 2);
    }
}
