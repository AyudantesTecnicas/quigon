package gameFiles;

import Model.actions.*;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.HasContainerRule;
import gameCreation.GameBuilder;

public final class FetchQuest extends GameBuilder {

    public static String gameDescription= "Don't take it!";

    public FetchQuest() {
        gameName = "FetchQuest";
    }
    public void setActions(){}
    public void setAmountOfRooms(){
        amountOfRooms=1;
    }

    public void setRooms() {
        fillGraph(0,0);
    }

    public void setItems(){
        fillVector(0,1);
    }

    public void setElements(){
        ComplexElement character = new ComplexElement();
        game.character=character;

        //Create elements
        Element room = new Element("room");
        ComplexElement stick = new ComplexElement("stick");

        //Add elementos to game
        elementsList.add(room);
        elementsList.add(stick);

        //Set containers for each element
        character.setContainerElement(room);
        stick.setContainerElement(room);

        //Create Moves
        Move pickStick = new Move("pick");

        //Create rules for movements
        HasContainerRule keyIsInRoom = new HasContainerRule();
        HasContainerRule victoryCondition = new HasContainerRule();

        //Set elements to rules
        keyIsInRoom.setElementToValidate(stick);
        keyIsInRoom.setElementOfElementToValidate(room);
        victoryCondition.setElementToValidate(stick);
        victoryCondition.setElementOfElementToValidate(character);

        //Inject rules to moves
        pickStick.setRules(keyIsInRoom);

        //Create actions
        Action addStickToCharacter = new ChangeContainerAction();
        Action removeStickFromRoom = new ChangeContainerAction();


        //Add elements and states to actions
        addStickToCharacter.addItemToUpdate(character);
        addStickToCharacter.setElementToUpdate(stick);

        removeStickFromRoom.addItemToUpdate(room);
        removeStickFromRoom.setElementToUpdate(stick);

        //Inject actions to moves
        pickStick.addAction(addStickToCharacter);
        pickStick.addAction(removeStickFromRoom);

        //Inject moves to elements
        stick.addMove(pickStick);
    }

}
