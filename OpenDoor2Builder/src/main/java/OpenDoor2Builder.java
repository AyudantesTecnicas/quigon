import creation.GameBuilderImp;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Player;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.HasContainerRule;
import model.rulesexpressions.rules.HasStateRule;

import java.util.ArrayList;


@SuppressWarnings("CPD-START")
public final class OpenDoor2Builder extends GameBuilderImp {

    private OpenDoor2Constants constants = new OpenDoor2Constants();
    private ComplexElement closedBoxState;
    private ComplexElement closedDoorState;
    private ComplexElement openDoorState;
    private ComplexElement openBoxState;
    private ComplexElement visibleKeyState;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door;
    private ComplexElement box;
    private ComplexElement key;
    private ArrayList<Player> characters;
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
    private Action makeVisibleKey;
    private Move openBox;
    private Move pickKey;
    private Move openDoor;
    private LogicBuilder logicBuilder;
    private IExpression openingRules;

    public OpenDoor2Builder() {
        gameName = "OpenDoor2";
        gameDescription = "There is a door on this game. But no key around.";
    }

    public void setElements() {
        createElements();
        loadPlayers();
        setNotVisibleElements();
        createRules();
        createActions();
        createComplexRules();
        createMoves();
    }

    private void loadPlayers() {
        characters = new ArrayList<>();
        for (int i = 0; i < constants.numberOfPlayers; i++) {
            characters.add(createAndAddPlayer("character" + i, room1, null));
        }
        game.currentPlayer = characters.get(0);
        game.characters = characters;
    }

    private void createMoves() {
        //Create moves
        openBox = moveWithActionsAndRules(constants.open, addOpenedStateToBox, closedBoxRule, constants.openBox);
        pickKey = moveWithActionsAndRules(constants.pick, addKeyToCharacter, keyIsInRoom1, constants.pickKey);
        openDoor = moveWithActionsAndRules(constants.open, addOpendStateToDoor, openingRules, constants.openDoor);

        //Inject Actions to moves
        openBox.addAction(removeOpenedStateToBox);
        openBox.addAction(addKeyToRoom1);
        openBox.addAction(makeVisibleKey);

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
        addKeyToCharacter = buildChangeContainerAction(key, game.currentPlayer);
        addOpendStateToDoor = buildAddStatesAction(door, openDoorState);
        removeOpenedStateToDoor = buildRemoveStatesAction(door, closedDoorState);
        moveCharacterToRoom2 = buildChangeContainerAction(game.currentPlayer, room2);
        makeVisibleKey = new ChangeVisibleAction();
        addElementsToAction(makeVisibleKey, key, visibleKeyState);
    }

    private void createRules() {
        closedBoxRule = checkStateRule(box, closedBoxState, constants.boxOpened);
        keyIsInRoom1 = checkContainerRule(key, room1, constants.keyNotInRoom1);
        closedDoorRule = checkStateRule(door, closedDoorState, constants.doorOpened);
        characterHasKey = checkContainerRule(key, game.currentPlayer, constants.missingKey);
        for (Player character:characters) {
            victoryRule = checkContainerRule(character, room2, constants.notWon);
            character.setVictoryCondition(victoryRule);
        }
    }

    private void createElements() {
        //Create states
        closedBoxState = new ComplexElement(constants.closed);
        closedDoorState = new ComplexElement(constants.closed);
        openDoorState = new ComplexElement(constants.opened);
        openBoxState = new ComplexElement(constants.opened);
        visibleKeyState = new ComplexElement(constants.visible);
        visibleKeyState.setVisible(true);

        //Create and add elements
        room1 = createAndAddElement(constants.room1, null, null);
        room2 = createAndAddElement(constants.room2, null, null);
        door = createAndAddElement(constants.door, room1, closedDoorState);
        box = createAndAddElement(constants.box, room1, closedBoxState);
        key = createAndAddElement(constants.key, box, null);
    }

    private void setNotVisibleElements() {
        key.setVisible(false);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
        createAndAddSuportedAction(1, constants.open);
    }

}
