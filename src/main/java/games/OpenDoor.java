package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;

@SuppressWarnings("CPD-START")
public final class OpenDoor extends GameBuilder {

    public static final String gameDescription = "There is a door on this game. Also, it is locked.";
    private Element closedDoor;
    private Element openedDoor;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door;
    private ComplexElement key;
    private ComplexElement character;
    private HasContainerRule roomHasKey;
    private HasContainerRule characterHasKey;
    private HasStateRule doorIsClosed;
    private HasContainerRule victoryCondition;
    private LogicBuilder logicBuilder;
    private IExpression conditionsToOpenDoor;
    private Action addOpenedDoorState;
    private Action removeClosedDoorState;
    private Action addKeyToCharacter;
    private Action addCharacterToRoom2;
    private Move pickKey;
    private Move openDoor;

    public OpenDoor() {
        gameName = "OpenDoor";
    }

    public void setElements() {
        createElements();
        createRules();
        createActions();
        createComplexRules();
        createMoves();
    }

    private void createMoves() {
        //Create moves
        pickKey = moveWithActionsAndRules("pick", addKeyToCharacter, roomHasKey, "There you go!");
        openDoor = moveWithActionsAndRules("open", addOpenedDoorState, conditionsToOpenDoor, "You enter room 2.");

        //Inject further actions
        openDoor.addAction(removeClosedDoorState);
        openDoor.addAction(addCharacterToRoom2);

        //Inyectar Moves a Elements
        key.addMove(pickKey);
        door.addMove(openDoor);
    }

    private void createComplexRules() {
        //Rules to open door
        logicBuilder = new LogicBuilder();
        conditionsToOpenDoor = null;
        try {
            conditionsToOpenDoor = logicBuilder.build(doorIsClosed, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
    }

    private void createActions() {
        addOpenedDoorState = buildAddStatesAction(door, openedDoor);
        removeClosedDoorState = buildRemoveStatesAction(door, closedDoor);
        addKeyToCharacter = buildChangeContainerAction(key, character);
        addCharacterToRoom2 = buildChangeContainerAction(character, room2);

    }

    private void createRules() {
        roomHasKey = checkContainerRule(key, room1, "Key is't in room 1.");
        characterHasKey = checkContainerRule(key, character, "Ey! Where do you go?! Room 2 is locked.");
        doorIsClosed = checkStateRule(door, closedDoor, "Door is open");
        victoryCondition = checkContainerRule(character, room2, "it's a pitty");
        game.setVictoryCondition(victoryCondition);
    }

    private void createElements() {
        closedDoor = new Element("close");
        openedDoor = new Element("open");
        room1 = createAndAddElement("room1", null, null);
        room2 = createAndAddElement("room2", null, null);
        door = createAndAddElement("door", room1, closedDoor);
        key = createAndAddElement("key", room1, null);
        character = createAndAddElement("character", room1, null);
        game.character = character;
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
    }
}
