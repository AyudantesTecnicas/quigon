package gameFiles;

import GameParser.SupportedAction;
import Model.actions.*;
import Model.elements.*;
import Model.rules.*;
import gameCreation.GameBuilder;
import logicFactory.ProxyLogicBuilder;
import logicFactory.WrongLogicException;

import java.util.HashMap;

public final class CursedObject extends GameBuilder {

    public static String gameDescription= "There is a cursed object on this game. And the thief...";

    public CursedObject() {
        gameName = "CursedObject";
    }

    public void setElements() {
        ComplexElement character = new ComplexElement();
        game.character = character;

        //Create elements
        ComplexElement room0 = new ComplexElement("Room0");
        ComplexElement room1 = new ComplexElement("Room1");
        ComplexElement room2 = new ComplexElement("Room2");
        ComplexElement door0To1 = new ComplexElement("door");
        ComplexElement door1To2 = new ComplexElement("golden_door");
        ComplexElement cursedObject = new ComplexElement("elemento");
        ComplexElement thief = new ComplexElement("ladron");

        //Add elements to the game
        elementsList.add(room0);
        elementsList.add(room1);
        elementsList.add(room2);
        elementsList.add(door0To1);
        elementsList.add(door1To2);
        elementsList.add(cursedObject);
        elementsList.add(thief);

        //Add elements to rooms
        character.setContainerElement(room0);
        cursedObject.setContainerElement(room0);
        door0To1.setContainerElement(room0);
        thief.setContainerElement(room1);
        door1To2.setContainerElement(room1);

        //Create element's states
        Element openState = new Element("abierta");

        //Add state to elemens
        door0To1.addState(openState);
        door1To2.addState(openState);

        //Create element's action
        Move goToRoom1 = new Move("open");
        Move goToRoom2 = new Move("open");
        Move talkThief = new Move("talk to");
        Move pickObject = new Move("pick");

        //Create action consequences
        Action changeRoom0ForRoom1 = new ChangeContainerAction();
        changeRoom0ForRoom1.setElementToUpdate(character);
        changeRoom0ForRoom1.addItemToUpdate(room1);

        Action changeRoom1ForRoom2 = new ChangeContainerAction();
        changeRoom1ForRoom2.setElementToUpdate(character);
        changeRoom1ForRoom2.addItemToUpdate(room2);

        Action stolenObject = new ChangeContainerAction();
        stolenObject.setElementToUpdate(cursedObject);
        stolenObject.addItemToUpdate(thief);

        Action pickedObject = new ChangeContainerAction();
        pickedObject.setElementToUpdate(cursedObject);
        pickedObject.addItemToUpdate(character);

        //Create rules
        HasContainerRule victoryRule = new HasContainerRule();
        victoryRule.setElementToValidate(character);
        victoryRule.setElementOfElementToValidate(room2);

        HasContainerRule thiefHaveCursedObject = new HasContainerRule();
        thiefHaveCursedObject.setElementToValidate(cursedObject);
        thiefHaveCursedObject.setElementOfElementToValidate(thief);

        HasContainerRule characterHasCursedObject = new HasContainerRule();
        characterHasCursedObject.setElementToValidate(cursedObject);
        characterHasCursedObject.setElementOfElementToValidate(character);

        HasContainerRule objectIsInTheRoom = new HasContainerRule();
        objectIsInTheRoom.setElementToValidate(cursedObject);
        objectIsInTheRoom.setElementOfElementToValidate(room0);

        HasContainerRule characterIsInRoom1 = new HasContainerRule();
        characterIsInRoom1.setElementToValidate(character);
        characterIsInRoom1.setElementOfElementToValidate(room1);

        HasContainerRule characterIsInRoom0 = new HasContainerRule();
        characterIsInRoom0.setElementToValidate(character);
        characterIsInRoom0.setElementOfElementToValidate(room0);

        //Set messages
        victoryRule.setFailMessage("it's a pitty");
        thiefHaveCursedObject.setFailMessage("You can't go to the next room");
        characterHasCursedObject.setFailMessage("You need an object");
        objectIsInTheRoom.setFailMessage("You can't take that!!");
        characterIsInRoom0.setFailMessage("You are in other room");
        characterIsInRoom1.setFailMessage("You are in other room");

        //Set actions and rules
        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        HashMap <Character, RuleExpression> rules = new HashMap<>();
        String logic = "(a)&(b)";

        /* Pick curser object */
        pickObject.addAction(pickedObject);
        rules.put('a', objectIsInTheRoom);
        rules.put('b', characterIsInRoom0);
        try {
            IExpression conditionsToPickObject = logicBuilder.parse(rules, logic);
            pickObject.setRules(conditionsToPickObject);
        } catch (WrongLogicException e) {
            System.out.print("La logica esta mal expresada.\n");
        }

        /* Talk with thief */
        talkThief.addAction(stolenObject);
        rules.put('a', characterIsInRoom1);
        rules.put('b', characterHasCursedObject);
        try {
            IExpression conditionsToTalkWithThief = logicBuilder.parse(rules, logic);
            talkThief.setRules(conditionsToTalkWithThief);
        } catch (WrongLogicException e) {
            System.out.print("La logica esta mal expresada.\n");
        }

        /* Go to room 1*/
        goToRoom1.addAction(changeRoom0ForRoom1);
        goToRoom1.setRules(characterHasCursedObject);

        /* Go to room 2*/
        goToRoom2.addAction(changeRoom1ForRoom2);
        goToRoom2.setRules(thiefHaveCursedObject);

        door0To1.addMove(goToRoom1);
        door1To2.addMove(goToRoom2);
        cursedObject.addMove(pickObject);
        thief.addMove(talkThief);

        game.setVictoryCondition(victoryRule);
    }
    public void setActions() {
        actionsList.add(new SupportedAction(1,"pick"));
        actionsList.add(new SupportedAction(1,"open"));
        actionsList.add(new SupportedAction(1,"talk to"));
    }

}