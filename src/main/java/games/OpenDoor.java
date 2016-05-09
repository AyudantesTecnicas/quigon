package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rulesexpressions.rules.*;
import model.rulesexpressions.expressions.*;

public final class OpenDoor extends GameBuilder {

    public static final String gameDescription = "There is a door on this game. Also, it is locked.";

    public OpenDoor() {
        gameName = "OpenDoor";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create states
        Element closedDoor = new Element("close");
        Element openedDoor = new Element("open");

        //Create elements
        ComplexElement room1 = createAndAddElement("room1", null, null);
        ComplexElement room2 = createAndAddElement("room2", null, null);
        ComplexElement door = createAndAddElement("door", room1, closedDoor);
        ComplexElement key = createAndAddElement("key", room1, null);
        ComplexElement character = createAndAddElement("character", room1, null);
        game.character = character;

        //Create movement rules
        HasContainerRule roomHasKey = checkContainerRule(key, room1, "Key is't in room 1.");
        HasContainerRule characterHasKey = checkContainerRule(key, character, "Ey! Where do you go?! Room 2 is locked.");
        HasStateRule doorIsClosed = checkStateRule(door, closedDoor, "Door is open");
        HasContainerRule victoryCondition = checkContainerRule(character, room2, "it's a pitty");

        //Rules to open door
        LogicBuilder logicBuilder = new LogicBuilder();
        IExpression conditionsToOpenDoor = null;
        try {
            conditionsToOpenDoor = logicBuilder.build(doorIsClosed, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Create actions
        Action addOpenedDoorState = buildAddStatesAction(door, openedDoor);
        Action removeClosedDoorState = buildRemoveStatesAction(door, closedDoor);
        Action addKeyToCharacter = buildChangeContainerAction(key, character);
        Action addCharacterToRoom2 = buildChangeContainerAction(character, room2);

        //Create moves
        Move pickKey = moveWithActionsAndRules("pick", addKeyToCharacter, roomHasKey, "There you go!");
        Move openDoor = moveWithActionsAndRules("open", addOpenedDoorState, conditionsToOpenDoor, "You enter room 2.");

        //Inject further actions
        openDoor.addAction(removeClosedDoorState);
        openDoor.addAction(addCharacterToRoom2);

        //Inyectar Moves a Elements
        key.addMove(pickKey);
        door.addMove(openDoor);

        game.setVictoryCondition(victoryCondition);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
    }
}
