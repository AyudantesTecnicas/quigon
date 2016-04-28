package gameFiles;

import GameParser.SupportedAction;
import Model.actions.*;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.HasContainerRule;
import Model.rules.HasStateRule;
import Model.rules.IExpression;
import Model.rules.RuleExpression;
import gameCreation.GameBuilder;
import logicFactory.ProxyLogicBuilder;
import logicFactory.WrongLogicException;

import java.util.HashMap;

public final class OpenDoor extends GameBuilder {

    public static String gameDescription= "There is a door on this game. Also, it is locked.";

    public OpenDoor() {
        gameName = "OpenDoor";
    }

    public void setElements(){
        ComplexElement character = new ComplexElement();
        game.character=character;

        //Create elements
        Element room1= new Element("room1");
        Element room2= new Element("room2");
        ComplexElement door = new ComplexElement("door");
        ComplexElement key = new ComplexElement("key");

        //Add elementos to game
        addElement(key);
        addElement(room1);
        addElement(room2);
        addElement(door);

        //Set containers for each element
        character.setContainerElement(room1);
        key.setContainerElement(room1);
        door.setContainerElement(room1);

        //Create states
        Element closedDoor = new Element("close");
        Element openedDoor = new Element("open");

        //Set initial states
        door.addState(closedDoor);

        //Create moves
        Move pickKey = new Move("pick");
        Move openDoor = new Move("open");

        //Create movement rules
        HasContainerRule roomHasKey = new HasContainerRule();
        HasContainerRule characterHasKey = new HasContainerRule();
        HasStateRule doorIsClosed = new HasStateRule();
        HasContainerRule victoryCondition = new HasContainerRule();

        //Set elements to rules
        roomHasKey.setElementToValidate(key);
        roomHasKey.setElementOfElementToValidate(room1);
        characterHasKey.setElementToValidate(key);
        characterHasKey.setElementOfElementToValidate(character);
        doorIsClosed.setElementOfElementToValidate(closedDoor);
        doorIsClosed.setElementToValidate(door);
        victoryCondition.setElementToValidate(character);
        victoryCondition.setElementOfElementToValidate(room2);


        //Set messages
        roomHasKey.setFailMessage("Key is't in room 1.");
        characterHasKey.setFailMessage("Ey! Where do you go?! Room 2 is locked.");
        pickKey.setResultMessage("There you go!");
        openDoor.setResultMessage("You enter room 2. You won the game!");

        //Rules to open door
        HashMap <Character, RuleExpression> rules = new HashMap<>();
        rules.put('a', doorIsClosed);
        rules.put('b', characterHasKey);
        String logic = "(a)&(b)";

        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        IExpression conditionsToOpenDoor;
        try {
            conditionsToOpenDoor = logicBuilder.parse(rules, logic);
            openDoor.setRules(conditionsToOpenDoor);
        } catch (WrongLogicException e) {
            System.out.print("La logica esta mal expresada.\n");
        }

        //Rules to pick key
        pickKey.setRules(roomHasKey);

        //Create actions
        Action addOpenedDoorState = new AddStatesAction();
        Action removeClosedDoorState = new RemoveStatesAction();
        Action addKeyToCharacter = new ChangeContainerAction();
        Action addCharacterToRoom2 = new ChangeContainerAction();

        //Agregar elementos y estados a las acciones
        addOpenedDoorState.addItemToUpdate(openedDoor);
        addOpenedDoorState.setElementToUpdate(door);

        removeClosedDoorState.addItemToUpdate(closedDoor);
        removeClosedDoorState.setElementToUpdate(door);

        addKeyToCharacter.addItemToUpdate(character);
        addKeyToCharacter.setElementToUpdate(key);

        addCharacterToRoom2.addItemToUpdate(room2);
        addCharacterToRoom2.setElementToUpdate(key);

        //Inject actions to moves
        pickKey.addAction(addKeyToCharacter);
        openDoor.addAction(addOpenedDoorState);
        openDoor.addAction(removeClosedDoorState);
        openDoor.addAction(addCharacterToRoom2);

        //Inyectar Moves a Elements
        key.addMove(pickKey);
        door.addMove(openDoor);

        game.setVictoryCondition(victoryCondition);
    }

    public void setActions(){
        actionsList.add(new SupportedAction(1,"pick"));
        actionsList.add(new SupportedAction(1,"open"));
    }

}
