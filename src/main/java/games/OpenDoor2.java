package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.HasContainerRule;
import model.rulesexpressions.rules.HasStateRule;


@SuppressWarnings("CPD-START")
public final class OpenDoor2 extends GameBuilder {

    public static final String gameDescription = "There is a door on this game. But no key around.";
    private ComplexElement closedBoxState;
    private ComplexElement closedDoorState;
    private ComplexElement openDoorState;
    private ComplexElement openBoxState;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door;
    private ComplexElement box;
    private ComplexElement key;
    private ComplexElement character;
    private HasStateRule closedBoxRule;
    private HasStateRule closedDoorRule;
    private HasContainerRule victoryRule;
    private HasContainerRule keyIsInRoom1;
    private HasContainerRule characterHasKey;
    private Action addOpenedStateToBox;
    private Action removeOpenedStateToBox;
    private Action addKeyToRoom1;
    private Action addKeyToCharacter;
    private Action addOpendStateToDoor;
    private Action removeOpenedStateToDoor;
    private Action moveCharacterToRoom2;
    private Move openBox;
    private Move pickKey;
    private Move openDoor;
    private LogicBuilder logicBuilder;
    private IExpression openingRules;

    public OpenDoor2() {
        gameName = "OpenDoor2";
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
        openBox = moveWithActionsAndRules("open", addOpenedStateToBox, closedBoxRule, "The box is opened!");
        pickKey = moveWithActionsAndRules("pick", addKeyToCharacter, keyIsInRoom1, "There you go!");
        openDoor = moveWithActionsAndRules("open", addOpendStateToDoor, openingRules, "You enter room 2.");

        //Inject Actions to moves
        openBox.addAction(removeOpenedStateToBox);
        openBox.addAction(addKeyToRoom1);

        openDoor.addAction(removeOpenedStateToDoor);
        openDoor.addAction(moveCharacterToRoom2);

        //Inject Moves to Elements
        key.addMove(pickKey);
        door.addMove(openDoor);
        box.addMove(openBox);
    }

    private void createComplexRules() {
        //Rules to Open Door
        logicBuilder = new LogicBuilder();
        openingRules = null;
        try {
            openingRules = logicBuilder.build(closedDoorRule, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
    }

    private void createActions() {
        addOpenedStateToBox = buildAddStatesAction(box, openBoxState);
        removeOpenedStateToBox = buildRemoveStatesAction(box, closedBoxState);
        addKeyToRoom1 = buildChangeContainerAction(key, room1);
        addKeyToCharacter = buildChangeContainerAction(key, character);
        addOpendStateToDoor = buildAddStatesAction(door, openDoorState);
        removeOpenedStateToDoor = buildRemoveStatesAction(door, closedDoorState);
        moveCharacterToRoom2 = buildChangeContainerAction(character, room2);
    }

    private void createRules() {
        victoryRule = checkContainerRule(character, room2, "it's a pitty");
        closedBoxRule = checkStateRule(box, closedBoxState, "the box was open");
        keyIsInRoom1 = checkContainerRule(key, room1, "key is not in room1");
        closedDoorRule = checkStateRule(door, closedDoorState, "the door was open");
        characterHasKey = checkContainerRule(key, character, "character doesn't have the key");
        game.setVictoryCondition(victoryRule);
    }

    private void createElements() {
        //Create states
        closedBoxState = new ComplexElement("Closed");
        closedDoorState = new ComplexElement("Closed");
        openDoorState = new ComplexElement("Open");
        openBoxState = new ComplexElement("Open");

        //Create and add elements
        room1 = createAndAddElement("room1", null, null);
        room2 = createAndAddElement("room2", null, null);
        door = createAndAddElement("door", room1, closedDoorState);
        box = createAndAddElement("box", room1, closedBoxState);
        key = createAndAddElement("key", box, null);
        character = createAndAddElement("character", room1, null);
        game.character = character;
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
    }

}
