package games;

import creation.GameBuilder;
import model.actions.*;
import model.elements.*;
import model.rulesexpressions.rules.HasContainerRule;

public class TreasureHunt extends GameBuilder {

    public static final String gameDescription = "Search for the treasure. But be careful.";

    public TreasureHunt() {
        gameName = "TreasureHunt";

    }

    //Character
    ComplexElement character;

    //Rooms
    ComplexElement roomCenter;
    ComplexElement roomWest;
    ComplexElement roomEast;
    ComplexElement roomSouth;
    ComplexElement roomNorth;

    //Doors
    ComplexElement doorWtoC;
    ComplexElement doorEtoC;
    ComplexElement doorStoC;
    ComplexElement doorNtoC;

    ComplexElement doorCtoW;
    ComplexElement doorCtoE;
    ComplexElement doorCtoS;
    ComplexElement doorCtoN;

    //Element's states
    Element openState;

    //Room transition actions
    Action changeRoomWestForRoomCenter;
    Action changeRoomEastForRoomCenter;
    Action changeRoomSouthForRoomCenter;
    Action changeRoomNorthForRoomCenter;

    Action changeRoomCenterForRoomWest;
    Action changeRoomCenterForRoomEast;
    Action changeRoomCenterForRoomSouth;
    Action changeRoomCenterForRoomNorth;

    //Rules
    HasContainerRule victoryRule;

    HasContainerRule characterIsInCenterRoom;
    HasContainerRule characterIsInWestRoom;
    HasContainerRule characterIsInEastRoom;
    HasContainerRule characterIsInSouthRoom;
    HasContainerRule characterIsInNorthRoom;

    //Moves
    Move goFromWestToCenter;
    Move goFromEastToCenter;
    Move goFromSouthToCenter;
    Move goFromNorthToCenter;

    Move goFromCenterToWest;
    Move goFromCenterToEast;
    Move goFromCenterToSouth;
    Move goFromCenterToNorth;


    @SuppressWarnings("CPD-START")
    public void setElements() {
        createRooms();

        defineCharacter();

        createDoors();

        createRoomTransitionActions();

        createRules();

        defineVictoryRule();

        createMoves();

        addMoves();
    }

    private void createRooms() {
        roomCenter = createAndAddElement("Center_room", null, null);
        roomWest = createAndAddElement("West_room_(start)", null, null);
        roomEast = createAndAddElement("East_room", null, null);
        roomSouth = createAndAddElement("South_room", null, null);
        roomNorth = createAndAddElement("North_room", null, null);
    }

    private void defineCharacter() {
        character = createAndAddElement("character", roomWest, null);
        game.character = character;
    }

    private void createDoors() {
        openState = new Element("abierta");

        doorWtoC = createAndAddElement("west_room_door", roomWest, openState);
        doorEtoC = createAndAddElement("east_room_door", roomEast, openState);
        doorStoC = createAndAddElement("south_room_door", roomSouth, openState);
        doorNtoC = createAndAddElement("north_room_door", roomNorth, openState);

        doorCtoW = createAndAddElement("door_to_west", roomCenter, openState);
        doorCtoE = createAndAddElement("door_to_east", roomCenter, openState);
        doorCtoS = createAndAddElement("door_to_south", roomCenter, openState);
        doorCtoN = createAndAddElement("door_to_north", roomCenter, openState);
    }

    private void createRoomTransitionActions() {
        changeRoomWestForRoomCenter = buildChangeContainerAction(character, roomCenter);
        changeRoomEastForRoomCenter = buildChangeContainerAction(character, roomCenter);
        changeRoomSouthForRoomCenter = buildChangeContainerAction(character, roomCenter);
        changeRoomNorthForRoomCenter = buildChangeContainerAction(character, roomCenter);

        changeRoomCenterForRoomWest = buildChangeContainerAction(character, roomWest);
        changeRoomCenterForRoomEast = buildChangeContainerAction(character, roomEast);
        changeRoomCenterForRoomSouth = buildChangeContainerAction(character, roomSouth);
        changeRoomCenterForRoomNorth = buildChangeContainerAction(character, roomNorth);
    }

    private void createRules() {
        characterIsInCenterRoom = checkContainerRule(character, roomCenter, "You are in other room");
        characterIsInWestRoom = checkContainerRule(character, roomWest, "You are in other room");
        characterIsInEastRoom = checkContainerRule(character, roomEast, "You are in other room");
        characterIsInSouthRoom = checkContainerRule(character, roomSouth, "You are in other room");
        characterIsInNorthRoom = checkContainerRule(character, roomNorth, "You are in other room");
    }

    private void defineVictoryRule() {
        victoryRule = checkContainerRule(character, roomEast, "it's a pitty");
        game.setVictoryCondition(victoryRule);
    }

    private void createMoves() {
        goFromWestToCenter = moveWithActionsAndRules("open", changeRoomWestForRoomCenter, characterIsInWestRoom, "You are in center room now.");
        goFromEastToCenter = moveWithActionsAndRules("open", changeRoomEastForRoomCenter, characterIsInEastRoom, "You are in center room now.");
        goFromSouthToCenter = moveWithActionsAndRules("open", changeRoomSouthForRoomCenter, characterIsInSouthRoom, "You are in center room now.");
        goFromNorthToCenter = moveWithActionsAndRules("open", changeRoomNorthForRoomCenter, characterIsInNorthRoom, "You are in center room now.");

        goFromCenterToWest = moveWithActionsAndRules("open", changeRoomCenterForRoomWest, characterIsInCenterRoom, "You are in west room now.");
        goFromCenterToEast = moveWithActionsAndRules("open", changeRoomCenterForRoomEast, characterIsInCenterRoom, "You are in east room now.");
        goFromCenterToSouth = moveWithActionsAndRules("open", changeRoomCenterForRoomSouth, characterIsInCenterRoom, "You are in south room now.");
        goFromCenterToNorth = moveWithActionsAndRules("open", changeRoomCenterForRoomNorth, characterIsInCenterRoom, "You are in north room now.");
    }

    private void addMoves() {
        doorWtoC.addMove(goFromWestToCenter);
        doorEtoC.addMove(goFromEastToCenter);
        doorStoC.addMove(goFromSouthToCenter);
        doorNtoC.addMove(goFromNorthToCenter);

        doorCtoW.addMove(goFromCenterToWest);
        doorCtoE.addMove(goFromCenterToEast);
        doorCtoS.addMove(goFromCenterToSouth);
        doorCtoN.addMove(goFromCenterToNorth);
    }

    @SuppressWarnings("CPD-END")

    public void setActions() {
        createAndAddSuportedAction(1, "open");
    }
}
