package games;

import creation.GameBuilder;
import games.constants.OpenDoorConstants;
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
    private OpenDoorConstants constants = new OpenDoorConstants();
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
        pickKey = moveWithActionsAndRules(constants.pick, addKeyToCharacter, roomHasKey, constants.pickKey);
        openDoor = moveWithActionsAndRules(constants.open, addOpenedDoorState, conditionsToOpenDoor, constants.openDoor);

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
        roomHasKey = checkContainerRule(key, room1, constants.keyNotInRoom1);
        characterHasKey = checkContainerRule(key, character, constants.room2Locked);
        doorIsClosed = checkStateRule(door, closedDoor, constants.doorOpened);
        victoryCondition = checkContainerRule(character, room2, constants.notWon);
        game.setVictoryCondition(victoryCondition);
    }

    private void createElements() {
        closedDoor = new Element(constants.closed);
        openedDoor = new Element(constants.opened);
        room1 = createAndAddElement(constants.room1, null, null);
        room2 = createAndAddElement(constants.room2, null, null);
        door = createAndAddElement(constants.door, room1, closedDoor);
        key = createAndAddElement(constants.key, room1, null);
        character = createAndAddElement(constants.character, room1, null);
        game.character = character;
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
        createAndAddSuportedAction(1, constants.open);
    }
}
