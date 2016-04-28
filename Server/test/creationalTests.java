import Model.actions.*;
import Model.elements.*;
import Model.rules.*;
import gameCreation.GameBuilder;
import gameCreation.GameCreator;
import gameFiles.CursedObject;
import logicFactory.ProxyLogicBuilder;
import logicFactory.WrongLogicException;
import org.junit.Test;

import java.beans.Expression;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class creationalTests {

    @Test
    public void getNameCorrect(){
        GameCreator gameCreator = new GameCreator();
        gameCreator.createGame("OpenDoor");
        assertEquals(gameCreator.getGame().getName(), "OpenDoor");
    }
}