import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import gameCreation.ActualGame;

public class creationalTests {
    @Test
    public void amountOfRoomsIsCorrect(){
    ActualGame aGame = new ActualGame("CursedObject");
        assertEquals(aGame.getRooms().size(), 3);
    }
    @Test
    public void amountOfItemsIsCorrect(){
        ActualGame aGame = new ActualGame("CursedObject");
        assertEquals(aGame.getRooms().get(0).itemsSize(), 1);
    }
}
