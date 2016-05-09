package games;

import creation.GameBuilder;
import model.actions.*;
import model.elements.*;
import model.ruleExpressions.rules.*;
import model.ruleExpressions.expressions.*;

public final class CursedObject extends GameBuilder {

    public static final String gameDescription = "There is a cursed object on this game. And the thief...";

    public CursedObject() {
        gameName = "CursedObject";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create element's states
        Element openState = new Element("abierta");

        //Creates elements and adds to game
        ComplexElement room0 = createAndAddElement("Room0", null, null);
        ComplexElement room1 = createAndAddElement("Room1", null, null);
        ComplexElement room2 = createAndAddElement("Room2", null, null);
        ComplexElement door0To1 = createAndAddElement("door", room0, openState);
        ComplexElement door1To2 = createAndAddElement("golden_door", room1, openState);
        ComplexElement cursedObject = createAndAddElement("object", room0, null);
        ComplexElement thief = createAndAddElement("thief", room1, null);
        ComplexElement character = createAndAddElement("character", room0, null);
        game.character = character;

        //Create action consequences
        Action changeRoom0ForRoom1 = buildChangeContainerAction(character, room1);
        Action changeRoom1ForRoom2 = buildChangeContainerAction(character, room2);
        Action stolenObject = buildChangeContainerAction(cursedObject, thief);
        Action pickedObject = buildChangeContainerAction(cursedObject, character);

        //Create rules
        HasContainerRule victoryRule = checkContainerRule(character, room2, "it's a pitty");
        HasContainerRule thiefHaveCursedObject = checkContainerRule(cursedObject, thief, "You can't go to the next room");
        HasContainerRule characterHasCursedObject = checkContainerRule(cursedObject, character, "You need an object");
        HasContainerRule objectIsInTheRoom = checkContainerRule(cursedObject, room0, "You can't take that!!");
        HasContainerRule characterIsInRoom1 = checkContainerRule(character, room1, "You are in other room");
        HasContainerRule characterIsInRoom0 = checkContainerRule(character, room0, "You are in other room");

        //Set complex rules
        AndExpression conditionToPickObject = new AndExpression();
        conditionToPickObject.setLeftExpression(objectIsInTheRoom);
        conditionToPickObject.setRightExpression(characterIsInRoom0);

        AndExpression conditionsToTalkWithThief = new AndExpression();
        conditionsToTalkWithThief.setLeftExpression(characterIsInRoom1);
        conditionsToTalkWithThief.setRightExpression(characterHasCursedObject);

        //Create moves with actions and rules
        Move goToRoom1 = moveWithActionsAndRules("open", changeRoom0ForRoom1, characterHasCursedObject, "There is another room! - Room 1 -");
        Move goToRoom2 = moveWithActionsAndRules("open", changeRoom1ForRoom2, thiefHaveCursedObject, "There is another room! - Room 2 -");
        Move talkThief = moveWithActionsAndRules("talk to", stolenObject, conditionsToTalkWithThief, "The thief have robbed you!!!");
        Move pickObject = moveWithActionsAndRules("pick", pickedObject, conditionToPickObject, "Ohoh, you have picked a cursed object =( ");

        door0To1.addMove(goToRoom1);
        door1To2.addMove(goToRoom2);
        cursedObject.addMove(pickObject);
        thief.addMove(talkThief);

        game.setVictoryCondition(victoryRule);
    }

    @SuppressWarnings("CPD-END")

    public void setActions() {
        createAndAddSuportedAction(1, "pick");
        createAndAddSuportedAction(1, "open");
        createAndAddSuportedAction(1, "talk to");
    }

}