package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.rulesexpressions.rules.HasContainerRule;
import model.rulesexpressions.rules.HasStateRule;
import model.rulesexpressions.expressions.IExpression;

public final class OpenDoor2 extends GameBuilder {

    public static final String gameDescription = "There is a door on this game. But no key around.";

    public OpenDoor2() {
        gameName = "OpenDoor2";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create states
        ComplexElement closedBoxState = new ComplexElement("Closed");
        ComplexElement closedDoorState = new ComplexElement("Closed");
        ComplexElement openDoorState = new ComplexElement("Open");
        ComplexElement openBoxState = new ComplexElement("Open");

        //Create and add elements
        ComplexElement room1 = createAndAddElement("room1", null, null);
        ComplexElement room2 = createAndAddElement("room2", null, null);
        ComplexElement door = createAndAddElement("door", room1, closedDoorState);
        ComplexElement box = createAndAddElement("box", room1, closedBoxState);
        ComplexElement key = createAndAddElement("key", box, null);
        ComplexElement character = createAndAddElement("character", room1, null);
        game.character = character;

        //Crear reglas para movimientos
        HasContainerRule victoryRule = checkContainerRule(character, room2, "it's a pitty");
        HasStateRule closedBoxRule = checkStateRule(box, closedBoxState, "the box was open");
        HasContainerRule keyIsInRoom1 = checkContainerRule(key, room1, "key is not in room1");
        HasStateRule closedDoorRule = checkStateRule(door, closedDoorState, "the door was open");
        HasContainerRule characterHasKey = checkContainerRule(key, character, "character doesn't have the key");

        //Build Actions
        Action addOpenedStateToBox = buildAddStatesAction(box, openBoxState);
        Action removeOpenedStateToBox = buildRemoveStatesAction(box, closedBoxState);
        Action addKeyToRoom1 = buildChangeContainerAction(key, room1);
        Action addKeyToCharacter = buildChangeContainerAction(key, character);
        Action addOpendStateToDoor = buildAddStatesAction(door, openDoorState);
        Action removeOpenedStateToDoor = buildRemoveStatesAction(door, closedDoorState);
        Action moveCharacterToRoom2 = buildChangeContainerAction(character, room2);

        //Rules to Open Door
        LogicBuilder logicBuilder = new LogicBuilder();
        IExpression openingRules = null;
        try {
            openingRules = logicBuilder.build(closedDoorRule, characterHasKey, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Create moves
        Move openBox = moveWithActionsAndRules("open", addOpenedStateToBox, closedBoxRule, "The box is opened!");
        Move pickKey = moveWithActionsAndRules("pick", addKeyToCharacter, keyIsInRoom1, "There you go!");
        Move openDoor = moveWithActionsAndRules("open", addOpendStateToDoor, openingRules, "You enter room 2.");

        //Inject Actions to moves
        openBox.addAction(removeOpenedStateToBox);
        openBox.addAction(addKeyToRoom1);

        openDoor.addAction(removeOpenedStateToDoor);
        openDoor.addAction(moveCharacterToRoom2);

        //Inject Moves to Elements
        key.addMove(pickKey);
        door.addMove(openDoor);
        box.addMove(openBox);

        game.setVictoryCondition(victoryRule);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
    }

}
