package games;

import creation.GameBuilder;
import model.actions.*;
import model.elements.*;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;

@SuppressWarnings("CPD-START")
public final class CursedObject extends GameBuilder {

    public static final String gameDescription = "There is a cursed object on this game. And the thief...";
    private ComplexElement room0;
    private ComplexElement room1;
    private ComplexElement room2;
    private ComplexElement door0To1;
    private ComplexElement door1To2;
    private ComplexElement cursedObject;
    private ComplexElement thief;
    private ComplexElement character;
    private Action changeRoom0ForRoom1;
    private Action changeRoom1ForRoom2;
    private Action stolenObject;
    private Action pickedObject;
    private HasContainerRule victoryRule;
    private HasContainerRule thiefHaveCursedObject;
    private HasContainerRule characterHasCursedObject;
    private HasContainerRule objectIsInTheRoom;
    private HasContainerRule characterIsInRoom1;
    private HasContainerRule characterIsInRoom0;
    private AndExpression conditionToPickObject;
    private AndExpression conditionsToTalkWithThief;
    private Move goToRoom1;
    private Move goToRoom2;
    private Move talkThief;
    private Move pickObject;
    private Element openState;

    public CursedObject() {
        gameName = "CursedObject";
    }

    public void setElements() {
        createElements();
        createActions();
        createRules();
        createComplexRules();
        createMoves();
        addMovesToElements();
    }

    private void addMovesToElements() {
        door0To1.addMove(goToRoom1);
        door1To2.addMove(goToRoom2);
        cursedObject.addMove(pickObject);
        thief.addMove(talkThief);
    }

    private void createMoves() {
        goToRoom1 = moveWithActionsAndRules("open", changeRoom0ForRoom1, characterHasCursedObject, "There is another room! - Room 1 -");
        goToRoom2 = moveWithActionsAndRules("open", changeRoom1ForRoom2, thiefHaveCursedObject, "There is another room! - Room 2 -");
        talkThief = moveWithActionsAndRules("talk to", stolenObject, conditionsToTalkWithThief, "The thief have robbed you!!!");
        pickObject = moveWithActionsAndRules("pick", pickedObject, conditionToPickObject, "Ohoh, you have picked a cursed object =( ");
    }

    private void createComplexRules() {
        conditionToPickObject = new AndExpression();
        conditionToPickObject.setLeftExpression(objectIsInTheRoom);
        conditionToPickObject.setRightExpression(characterIsInRoom0);

        conditionsToTalkWithThief = new AndExpression();
        conditionsToTalkWithThief.setLeftExpression(characterIsInRoom1);
        conditionsToTalkWithThief.setRightExpression(characterHasCursedObject);

    }

    private void createRules() {
        victoryRule = checkContainerRule(character, room2, "it's a pitty");
        thiefHaveCursedObject = checkContainerRule(cursedObject, thief, "You can't go to the next room");
        characterHasCursedObject = checkContainerRule(cursedObject, character, "You need an object");
        objectIsInTheRoom = checkContainerRule(cursedObject, room0, "You can't take that!!");
        characterIsInRoom1 = checkContainerRule(character, room1, "You are in other room");
        characterIsInRoom0 = checkContainerRule(character, room0, "You are in other room");
        game.setVictoryCondition(victoryRule);
    }

    private void createActions() {
        changeRoom0ForRoom1 = buildChangeContainerAction(character, room1);
        changeRoom1ForRoom2 = buildChangeContainerAction(character, room2);
        stolenObject = buildChangeContainerAction(cursedObject, thief);
        pickedObject = buildChangeContainerAction(cursedObject, character);
    }

    private void createElements() {
        openState = new Element("abierta");
        room0 = createAndAddElement("Room0", null, null);
        room1 = createAndAddElement("Room1", null, null);
        room2 = createAndAddElement("Room2", null, null);
        door0To1 = createAndAddElement("door", room0, openState);
        door1To2 = createAndAddElement("golden_door", room1, openState);
        cursedObject = createAndAddElement("object", room0, null);
        thief = createAndAddElement("thief", room1, null);
        character = createAndAddElement("character", room0, null);
        game.character = character;
    }

    @SuppressWarnings("CPD-END")

    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
        createAndAddSuportedAction(1, "talk to");
    }
}