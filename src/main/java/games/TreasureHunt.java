package games;

import creation.GameBuilder;
import model.actions.*;
import model.elements.*;
import model.rulesexpressions.rules.HasContainerRule;

public class TreasureHunt extends GameBuilder {
    @SuppressWarnings("CPD-START")
    public static final String gameDescription = "Search for the treasure. But be careful.";

    public TreasureHunt() {
        gameName = "TreasureHunt";
    }

    //Character
    private ComplexElement character;

    //Rooms
    private ComplexElement roomCenter;
    private ComplexElement roomWest;
    private ComplexElement roomEast;
    private ComplexElement roomSouth;
    private ComplexElement roomNorth;

    //Doors
    private ComplexElement doorWtoC;
    private ComplexElement doorEtoC;
    private ComplexElement doorStoC;
    private ComplexElement doorNtoC;

    private ComplexElement doorCtoW;
    private ComplexElement doorCtoE;
    private ComplexElement doorCtoS;
    private ComplexElement doorCtoN;

    //Items - boxes
//    private ComplexElement box;

    //Items - keys
//    private ComplexElement key;

    //Element's states
//    private ComplexElement closedBoxState;
//    private ComplexElement openBoxState;
//    private ComplexElement closedDoorState;
//    private ComplexElement openDoorState;

    //Room transition actions
    private Action passFromWestToCenter;
    private Action passFromEastToCenter;
    private Action passFromSouthToCenter;
    private Action passFromNorthToCenter;

    private Action passFromCenterToWest;
    private Action passFromCenterToEast;
    private Action passFromCenterToSouth;
    private Action passFromCenterToNorth;

    //Other actions
//    private Action addOpenedStateToBox;
//    private Action addKeyToWestRoom;
//    private Action addKeyToCharacter;
//    private Action throwKeyAtFloor;

    //Rules
    private HasContainerRule characterIsInCenterRoom;
    private HasContainerRule characterIsInWestRoom;
    private HasContainerRule characterIsInEastRoom;
    private HasContainerRule characterIsInSouthRoom;
    private HasContainerRule characterIsInNorthRoom;

    //Moves
    private Move goFromWestToCenter;
    private Move goFromEastToCenter;
    private Move goFromSouthToCenter;
    private Move goFromNorthToCenter;

    private Move goFromCenterToWest;
    private Move goFromCenterToEast;
    private Move goFromCenterToSouth;
    private Move goFromCenterToNorth;

    //Supported moves
    private static final String OPEN_MOVE = "open";


    public void setElements() {
        createRooms();

        defineCharacter();

        createStates();

        createDoors();

        createRoomTransitionActions();

        createOtherActions();

        createItems();

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

    private void createStates() {
//        closedBoxState = new ComplexElement("Closed");
//        openBoxState = new ComplexElement("Open");
//        closedDoorState = new ComplexElement("Closed");
//        openDoorState = new ComplexElement("Open");
    }

    private void createDoors() {
        ComplexElement openDoorState = new ComplexElement("Open");

        doorWtoC = createAndAddElement("west_room_door", roomWest, openDoorState);
        doorEtoC = createAndAddElement("east_room_door", roomEast, openDoorState);
        doorStoC = createAndAddElement("south_room_door", roomSouth, openDoorState);
        doorNtoC = createAndAddElement("north_room_door", roomNorth, openDoorState);

        doorCtoW = createAndAddElement("door_to_west", roomCenter, openDoorState);
        doorCtoE = createAndAddElement("door_to_east", roomCenter, openDoorState);
        doorCtoS = createAndAddElement("door_to_south", roomCenter, openDoorState);
        doorCtoN = createAndAddElement("door_to_north", roomCenter, openDoorState);
    }

    private void createRoomTransitionActions() {
        passFromWestToCenter = buildChangeContainerAction(character, roomCenter);
        passFromEastToCenter = buildChangeContainerAction(character, roomCenter);
        passFromSouthToCenter = buildChangeContainerAction(character, roomCenter);
        passFromNorthToCenter = buildChangeContainerAction(character, roomCenter);

        passFromCenterToWest = buildChangeContainerAction(character, roomWest);
        passFromCenterToEast = buildChangeContainerAction(character, roomEast);
        passFromCenterToSouth = buildChangeContainerAction(character, roomSouth);
        passFromCenterToNorth = buildChangeContainerAction(character, roomNorth);
    }

    private void createOtherActions() {
//        addOpenedStateToBox = buildAddStatesAction(box, openBoxState);
//        addKeyToWestRoom = buildChangeContainerAction(key, roomWest);
//        addKeyToCharacter = buildChangeContainerAction(key, character);
    }

    private void createItems() {
//        box = createAndAddElement("box", roomWest, closedBoxState);
//        key = createAndAddElement("key", box, null);
    }

    private void createRules() {
        characterIsInCenterRoom = checkContainerRule(character, roomCenter, "You are in other room");
        characterIsInWestRoom = checkContainerRule(character, roomWest, "You are in other room");
        characterIsInEastRoom = checkContainerRule(character, roomEast, "You are in other room");
        characterIsInSouthRoom = checkContainerRule(character, roomSouth, "You are in other room");
        characterIsInNorthRoom = checkContainerRule(character, roomNorth, "You are in other room");

//        closedBoxRule = checkStateRule(box, closedBoxState, "the box was open");
    }

    private void defineVictoryRule() {
        HasContainerRule victoryRule = checkContainerRule(character, roomEast, "it's a pitty");
        game.setVictoryCondition(victoryRule);
    }

    private void createMoves() {
        goFromWestToCenter = moveWithActionsAndRules(OPEN_MOVE, passFromWestToCenter, characterIsInWestRoom, "You are in center room.");
        goFromEastToCenter = moveWithActionsAndRules(OPEN_MOVE, passFromEastToCenter, characterIsInEastRoom, "You are in center room.");
        goFromSouthToCenter = moveWithActionsAndRules(OPEN_MOVE, passFromSouthToCenter, characterIsInSouthRoom, "You are in center room.");
        goFromNorthToCenter = moveWithActionsAndRules(OPEN_MOVE, passFromNorthToCenter, characterIsInNorthRoom, "You are in center room.");

        goFromCenterToWest = moveWithActionsAndRules(OPEN_MOVE, passFromCenterToWest, characterIsInCenterRoom, "You are in west room.");
        goFromCenterToEast = moveWithActionsAndRules(OPEN_MOVE, passFromCenterToEast, characterIsInCenterRoom, "You are in east room.");
        goFromCenterToSouth = moveWithActionsAndRules(OPEN_MOVE, passFromCenterToSouth, characterIsInCenterRoom, "You are in south room.");
        goFromCenterToNorth = moveWithActionsAndRules(OPEN_MOVE, passFromCenterToNorth, characterIsInCenterRoom, "You are in north room.");
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
        createAndAddSuportedAction(1, OPEN_MOVE);
    }
}
