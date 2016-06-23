import creation.GameBuilderImp;
import creation.JavaRandomAdapter;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.Action;
import model.actions.Move;
import model.actions.TimeCondition;
import model.actions.TimedMove;
import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.Player;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;

import java.util.ArrayList;

public final class EntregaBuilder extends GameBuilderImp {

    @SuppressWarnings("CPD-START")

    public EntregaBuilder() {
        gameName = "EntregaBuilder";
        gameDescription = "EjercicioEntrega";
    }

    //TimeConditions
    private TimeCondition oneTimeTwoMinutes;
    private TimeCondition manyTimesFourMinutes;

    //Characters
    private ArrayList<Player> characters;

    //Rooms
    private ComplexElement roomHallway;
    private ComplexElement roomRoom1;
    private ComplexElement roomRoom2;
    private ComplexElement roomRoom3;
    private ComplexElement roomBasement;
    private ComplexElement roomSubBasement;
    private ComplexElement roomLibrary;
    private ComplexElement roomLibraryAccess;
    private ComplexElement roomYard;

    //Items
    private ComplexElement itemPicture;
    //Items salon 1
    private ComplexElement itemBottle;
    private ComplexElement itemGlass1;
    private ComplexElement itemGlass2;
    private ComplexElement itemPaintingShip;
    private ComplexElement itemSafe;
    private ComplexElement itemCredential;
    //Items salon 2
    private ComplexElement itemHammer;
    private ComplexElement itemScrewdriver1;
    private ComplexElement itemScrewdriver2;
    //Items salon 3
    private ComplexElement itemKey;
    //Items Acceso Bilioteca
    private ComplexElement itemLibrarian;
    //Items Bilioteca
    private ComplexElement itemOldBook;
    private ComplexElement itemBook1;
    private ComplexElement itemBook2;
    private ComplexElement itemBook3;
    private ComplexElement itemBook4;
    private ComplexElement itemBook5;
    private ComplexElement itemBook6;
    private ComplexElement itemBook7;
    private ComplexElement itemBook8;
    private ComplexElement itemBook9;

    //Items Sotano
    private ComplexElement itemStairs;
    private ComplexElement itemRailing;

    //Items SubSotano
    private ComplexElement itemWindow;
    private ComplexElement itemStairsSubBasement;

    //Item Contenedor de TimedMoves
    private ComplexElement emptyElement;

    //Rules
    private HasContainerRule ruleHoldsKey;
    private HasContainerRule ruleCharacterInSalon1;
    private HasContainerRule ruleCharacterInHallway;
    private HasContainerRule ruleHoldsHammer;
    private HasStateRule ruleCredentialValida;
    private HasContainerRule ruleCredentialInvalid;
    private HasContainerRule ruleDescendsToSubBasementWithoutHammer;
    private HasStateRule ruleBrokenWindow;
    private IExpression ruleToGetLibrarianDrunk;
    private IExpression ruleToAccessLibrary;

    private DoesNotHaveContainerRule ruleLibrarianIsNotInLibraryAccess;
    private HasContainerRule ruleLibrarianIsInLibraryAccess;
    private HasContainerRule ruleLibrarianIsInLibrary;
    private HasContainerRule ruleLibrarianIsInRoom1;
    private HasContainerRule ruleLibrarianIsInRoom2;
    private HasContainerRule ruleLibrarianIsInRoom3;
    private HasContainerRule ruleLibrarianIsInHallway;
    private DoesNotHaveState ruleIllegalEntry;

    //Item actions
    private Action actionSetVisibleSafe;
    private Action actionSetVisibleCredential;
    private Action actionSetVisibleSecretPassage;
    private Action actionChangeToHallway;
    private Action actionChangeToRoom1;
    private Action actionChangeToRoom2;
    private Action actionChangeToRoom3;
    private Action actionChangeToLibraryAccess;
    private Action actionChangeToLibrary;
    private Action actionChangeToSubBasement;
    private Action actionChangeToBasement;
    private Action actionChangeToPatio;
    private Action actionPutPictureOnCredential;
    private Action actionSetCredentialToValida;
    private Action actionSetCredentialToInvalid;
    private Action actionMakeLibrarianHappy;
    private Action actionGetLibrarianDrunk;
    private Action actionKillCharacter;
    private Action actionKillCharacterNoHammer;
    private Action actionShatterWindow;
    private Action actionAddIllegalState;

    private Action actionWakeUp;
    private Action actionLibrarianToLibraryAccess;
    private Action actionLibrarianToRoom1;
    private Action actionLibrarianToRoom2;
    private Action actionLibrarianToRoom3;
    private Action actionLibrarianToLibrary;
    private Action actionLibrarianToHallway;

    //Moves
    private Move moveMovePainting;
    private Move moveOpenSafe;
    private Move moveTakeCredential;
    private Move moveGoToHallway;
    private Move moveGoToRoom1;
    private Move moveGoToRoom2;
    private Move moveGoToRoom3;
    private Move moveGoToLibraryAccess;
    private Move moveGoToLibrary;
    private Move moveGoToBasement;
    private Move moveGoToSubBasement;
    private Move moveIrAPatio;

    private Move movePutPictureOnCredential;
    private Move moveShowCredentialToLibrarian;
    private Move moveGetLibrarianDrunk;
    private Move moveUseStairs;
    private Move movePickBottle;
    private Move movePickKey;
    private Move movePickHammer;
    private Move movePickScrewdriver1;
    private Move movePickScrewdriver2;
    private Move movePickGlass1;
    private Move movePickGlass2;
    private Move moveMoveOldBook;
    private Move moveMoveBook;
    private Move moveShatterWindow;

    private TimedMove wakeUpLibrarian;
    private TimedMove changeRoomLibrarianFromLibraryAccess;
    private Move changeRoomLibrarianFromLibrary;
    private Move changeRoomLibrarianFromRoom1;
    private Move changeRoomLibrarianFromRoom2;
    private Move changeRoomLibrarianFromRoom3;
    private Move changeRoomLibrarianFromHallway;

    //Doors
    private ComplexElement doorHallwayToRoom1;
    private ComplexElement doorHallwayToRoom2;
    private ComplexElement doorHallwayToRoom3;
    private ComplexElement doorHallwayToLibraryAccess;
    private ComplexElement doorRoom1ToHallway;
    private ComplexElement doorRoom2ToHallway;
    private ComplexElement doorRoom3ToHallway;
    private ComplexElement doorLibraryAccessToHallway;
    private ComplexElement doorLibraryAccessToLibrary;
    private ComplexElement doorLibraryToLibraryAccess;
    private ComplexElement doorLibraryToBasement;
    private ComplexElement doorSubBasementToYard;

    //States
    private Element stateValid;
    private Element stateInvalid;
    private Element stateOpen;
    private Element stateHappy;
    private Element stateDrunk;
    private Element stateDead;
    private Element stateBroken;
    private Element stateIllegal;

    private LogicBuilder logicBuilder = new LogicBuilder();

    private IExpression makeComplexRule(IExpression rule1, IExpression rule2, char op) {
        IExpression complexRule = null;
        try {
            complexRule = logicBuilder.build(rule1, rule2, op);
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
        return complexRule;
    }

    protected void setActions() {
        createAndAddSuportedAction(1, EntregaConstants.movePick);
        createAndAddSuportedAction(1, EntregaConstants.moveIrA);
        createAndAddSuportedAction(1, EntregaConstants.moveUse);
        createAndAddSuportedAction(1, EntregaConstants.movePutFoto);
        createAndAddSuportedAction(1, EntregaConstants.moveMover);
        createAndAddSuportedAction(1, EntregaConstants.moveAbrirCajaFuerte);
        createAndAddSuportedAction(1, EntregaConstants.moveEmborrachar);
        createAndAddSuportedAction(1, EntregaConstants.moveMostrarCredencial);
        createAndAddSuportedAction(1, EntregaConstants.moveRomperVentana);
    }

    //-----------------------------------------ELEMENTS---------------------------------------------

    public void setElements() {
        createRooms();
        createStates();
        createDoors();
        createItems();
        defineCharacter();
        createTimedConditions();
        createRules();
        createActions();
        createMoves();
        addMoves();
    }

    private void defineCharacter() {
        characters = new ArrayList<>();
        for (int i = 0; i < EntregaConstants.numberOfPlayers; i++) {
            Player character = createAndAddPlayer("character" + i, roomHallway, null);
            itemPicture = createAndAddElement(EntregaConstants.photo,character,null);
            createAndAddElement(EntregaConstants.pen,character,null);
            characters.add(character);
        }
        game.playerManager.characters = characters;
    }

    private void createRooms() {
        roomHallway = createAndAddElement(EntregaConstants.roomPasillo, null, null);
        roomRoom1 = createAndAddElement(EntregaConstants.roomSalon1, null, null);
        roomRoom2 = createAndAddElement(EntregaConstants.roomSalon2, null, null);
        roomRoom3 = createAndAddElement(EntregaConstants.roomSalon3, null, null);
        roomBasement = createAndAddElement(EntregaConstants.roomSotano, null, null);
        roomSubBasement = createAndAddElement(EntregaConstants.roomSubSotano, null, null);
        roomLibrary = createAndAddElement(EntregaConstants.roomBiblioteca, null, null);
        roomLibraryAccess = createAndAddElement(EntregaConstants.roomAccesoBiblioteca, null, null);
        roomYard = createAndAddElement(EntregaConstants.roomPatio, null, null);
    }

    private void createStates() {
        stateOpen = new Element(EntregaConstants.abierta);
        stateInvalid = new Element(EntregaConstants.invalido);
        stateValid = new Element(EntregaConstants.valido);
        stateHappy = new Element(EntregaConstants.feliz);
        stateDrunk = new Element(EntregaConstants.borracho);
        stateDead = new Element(EntregaConstants.muerto);
        stateBroken = new Element(EntregaConstants.roto);
        stateIllegal = new Element(EntregaConstants.illegal);
    }

    private void createDoors() {
        //Doors from Pasillo to another one
        doorHallwayToRoom1 = createAndAddElement(EntregaConstants.doorPasilloToSalon1, roomHallway, stateOpen);
        doorHallwayToRoom2 = createAndAddElement(EntregaConstants.doorPasilloToSalon2, roomHallway, stateOpen);
        doorHallwayToRoom3 = createAndAddElement(EntregaConstants.doorPasilloToSalon3, roomHallway, stateOpen);
        doorHallwayToLibraryAccess = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomHallway, stateOpen);

        //Doors to Pasillo from another one
        doorRoom1ToHallway = createAndAddElement(EntregaConstants.doorPasillo, roomRoom1, stateOpen);
        doorRoom2ToHallway = createAndAddElement(EntregaConstants.doorPasillo, roomRoom2, stateOpen);
        doorRoom3ToHallway = createAndAddElement(EntregaConstants.doorPasillo, roomRoom3, stateOpen);
        doorLibraryAccessToHallway = createAndAddElement(EntregaConstants.doorPasillo, roomLibraryAccess, stateOpen);

        //Others doors
        doorLibraryAccessToLibrary = createAndAddElement(EntregaConstants.doorBiblioteca, roomLibraryAccess, stateOpen);
        doorLibraryToLibraryAccess = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomLibrary, stateOpen);
        doorLibraryToBasement = createAndAddElement(EntregaConstants.doorBibliotecaToSotano, itemOldBook, stateOpen);
        doorSubBasementToYard = createAndAddElement(EntregaConstants.doorSubSotanoToPatio, roomSubBasement,stateOpen);
    }

    //-------------------------------------------ACTIONS---------------------------------------------

    private void createChangeRoomItemsAction() {
        actionChangeToHallway = buildChangeContainerAction(game.playerManager, roomHallway);
        actionChangeToRoom1 = buildChangeContainerAction(game.playerManager, roomRoom1);
        actionChangeToRoom2 = buildChangeContainerAction(game.playerManager, roomRoom2);
        actionChangeToRoom3 = buildChangeContainerAction(game.playerManager, roomRoom3);
        actionChangeToLibraryAccess = buildChangeContainerAction(game.playerManager, roomLibraryAccess);
        actionChangeToLibrary = buildChangeContainerAction(game.playerManager, roomLibrary);
        actionChangeToSubBasement = buildChangeContainerAction(game.playerManager, roomSubBasement);
        actionChangeToBasement = buildChangeContainerAction(game.playerManager, roomBasement);
        actionChangeToPatio = buildChangeContainerAction(game.playerManager, roomYard);
    }

    private void createActions() {
        createChangeRoomItemsAction();
        createTimedActions();

        actionSetVisibleSafe = buildChangeContainerAction(itemSafe, roomRoom1);
        actionSetVisibleCredential = buildChangeContainerAction(itemCredential, roomRoom1);
        actionSetVisibleSecretPassage = buildChangeContainerAction(doorLibraryToBasement, roomLibrary);

        actionPutPictureOnCredential = buildChangeContainerAction(itemPicture, itemCredential);
        actionSetCredentialToValida = buildAddStatesAction(itemCredential, stateValid);
        actionSetCredentialToInvalid = buildAddStatesAction(itemCredential, stateInvalid);
        actionSetCredentialToInvalid.setRules(ruleCredentialInvalid);

        actionMakeLibrarianHappy = buildAddStatesAction(game.playerManager, stateHappy);
        actionMakeLibrarianHappy.setRules(ruleCredentialValida);
        actionGetLibrarianDrunk = buildAddStatesAction(itemLibrarian, stateDrunk);
        actionGetLibrarianDrunk.addObserver(oneTimeTwoMinutes);
        actionKillCharacter = buildAddStatesAction(game.playerManager, stateDead);
        actionKillCharacterNoHammer = buildAddStatesAction(game.playerManager, stateDead);
        actionKillCharacterNoHammer.setRules(ruleDescendsToSubBasementWithoutHammer);
        actionShatterWindow = buildAddStatesAction(itemWindow, stateBroken);
        actionAddIllegalState = buildAddStatesAction(game.playerManager, stateIllegal);
        actionAddIllegalState.setRules(ruleIllegalEntry);
    }

    private void createTimedActions() {
        actionLibrarianToLibraryAccess = buildChangeContainerAction(itemLibrarian, roomLibraryAccess);
        actionLibrarianToHallway = buildChangeContainerAction(itemLibrarian, roomHallway);
        actionLibrarianToLibrary = buildChangeContainerAction(itemLibrarian, roomLibrary);
        actionLibrarianToRoom1 = buildChangeContainerAction(itemLibrarian, roomRoom1);
        actionLibrarianToRoom2 = buildChangeContainerAction(itemLibrarian, roomRoom2);
        actionLibrarianToRoom3 = buildChangeContainerAction(itemLibrarian, roomRoom3);

        actionWakeUp = buildRemoveStatesAction(itemLibrarian, stateDrunk);
        actionWakeUp.addObserver(manyTimesFourMinutes);
    }

    //---------------------------------------TIMED-CONDITIONS-----------------------------------------------
    
    private void createTimedConditions() {
        oneTimeTwoMinutes = new TimeCondition(5,false);
        manyTimesFourMinutes = new TimeCondition(8,true);
        game.setTimeObserver(oneTimeTwoMinutes);
        game.setTimeObserver(manyTimesFourMinutes);
    }

    //-------------------------------------------RULES-----------------------------------------------

    private void createRulesCharacterInRooms() {
        ruleCharacterInSalon1 = checkContainerRule(game.playerManager, roomRoom1,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomRoom2,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomRoom3,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomLibraryAccess,EntregaConstants.noEstaEnLaRoom);
        ruleCharacterInHallway = checkContainerRule(game.playerManager, roomHallway,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomLibrary,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomBasement,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager, roomSubBasement,EntregaConstants.noEstaEnLaRoom);
    }

    private void createRulesToAccessLibrary() {
        HasStateRule ruleLibrarianHappy = checkStateRule(game.playerManager, stateHappy,
                EntregaConstants.noEstaFeliz);
        HasStateRule ruleLibrarianDrunk = checkStateRule(itemLibrarian, stateDrunk,
                EntregaConstants.noEstaBorracho);

        IExpression orExpressionToAccessLibrary = makeComplexRule(ruleLibrarianHappy,
                ruleLibrarianDrunk, '|');

        ruleToAccessLibrary = makeComplexRule(orExpressionToAccessLibrary,
                ruleLibrarianIsNotInLibraryAccess, '|');
        ruleToAccessLibrary.setFailMessage(EntregaConstants.noSePuedePasarALaBiblioteca);
    }

    private void createRulesToGetLibrarianDrunk() {
        HasContainerRule ruleContainsGlass1 = checkContainerRule(itemGlass1, game.playerManager, EntregaConstants.necesitaElVaso);
        HasContainerRule ruleContainsGlass2 = checkContainerRule(itemGlass2, game.playerManager, EntregaConstants.necesitaElVaso);

        IExpression orExpressionForGlasses = makeComplexRule(ruleContainsGlass1, ruleContainsGlass2,
                '|');
        orExpressionForGlasses.setFailMessage(EntregaConstants.noTieneVasos);

        HasContainerRule ruleHasBottle = checkContainerRule(itemBottle, game.playerManager, EntregaConstants.necesitaLaBotella);
        ruleToGetLibrarianDrunk = makeComplexRule(ruleHasBottle, orExpressionForGlasses,
                '&');
        ruleToGetLibrarianDrunk.setFailMessage(EntregaConstants.noSePuedeEmborrachar);
    }

    private void createRules() {
        createRulesCharacterInRooms();
        ruleHoldsKey = checkContainerRule(itemKey,game.playerManager,EntregaConstants.necesitaTenerLlaveSalon3);
        ruleHoldsHammer = checkContainerRule(itemHammer,game.playerManager,EntregaConstants.necesitaTenerMartillo);
        ruleBrokenWindow = checkStateRule(itemWindow, stateBroken,EntregaConstants.necesitaEstarRotaLaVentana);
        ruleCredentialValida = checkStateRule(itemCredential, stateValid, EntregaConstants.necesitaSerValida);
        ruleCredentialInvalid = checkContainerRule(itemPicture,game.playerManager,EntregaConstants.fotoNoPegada);
        ruleDescendsToSubBasementWithoutHammer = checkContainerRule(itemHammer, roomRoom2,EntregaConstants.noTieneElMartillo);

        //Reglas para las acciones
        this.createActionRules();

        //Reglas para poder emborrachar al bibliotecario
        this.createRulesToGetLibrarianDrunk();

        //Reglas para poder pasar a la biblioteca
        this.createRulesToAccessLibrary();

        //Regla para perder
        //Ganancia
        for (Player character:game.playerManager.characters) {
            HasContainerRule librarianIsInLibraryAccess = checkContainerRule(itemLibrarian, roomLibraryAccess,
                    EntregaConstants.failed);
            HasContainerRule librarianIsInRoom1 = checkContainerRule(itemLibrarian, roomRoom1,
                    EntregaConstants.failed);
            HasContainerRule librarianIsInRoom2 = checkContainerRule(itemLibrarian, roomRoom2,
                    EntregaConstants.failed);
            HasContainerRule librarianIsInRoom3 = checkContainerRule(itemLibrarian, roomRoom3,
                    EntregaConstants.failed);
            HasContainerRule librarianIsInHallway = checkContainerRule(itemLibrarian, roomHallway,
                    EntregaConstants.failed);
            HasContainerRule librarianIsInLibrary = checkContainerRule(itemLibrarian, roomLibrary,
                    EntregaConstants.failed);

            HasContainerRule playerIsInLibraryAccess = checkContainerRule(character, roomLibraryAccess,
                    EntregaConstants.failed);
            HasContainerRule playerIsInRoom1 = checkContainerRule(character, roomRoom1,
                    EntregaConstants.failed);
            HasContainerRule playerIsInRoom2 = checkContainerRule(character, roomRoom2,
                    EntregaConstants.failed);
            HasContainerRule playerIsInRoom3 = checkContainerRule(character, roomRoom3,
                    EntregaConstants.failed);
            HasContainerRule playerIsInHallway = checkContainerRule(character, roomHallway,
                    EntregaConstants.failed);
            HasContainerRule playerIsInLibrary = checkContainerRule(character, roomLibrary,
                    EntregaConstants.failed);

            IExpression and1 = makeComplexRule(librarianIsInLibraryAccess, playerIsInLibraryAccess, '&');
            IExpression and2 = makeComplexRule(librarianIsInRoom1, playerIsInRoom1, '&');
            IExpression and3 = makeComplexRule(librarianIsInRoom2, playerIsInRoom2, '&');
            IExpression and4 = makeComplexRule(librarianIsInRoom3, playerIsInRoom3, '&');
            IExpression and5 = makeComplexRule(librarianIsInHallway, playerIsInHallway, '&');
            IExpression and6 = makeComplexRule(librarianIsInLibrary, playerIsInLibrary, '&');

            IExpression or1 = makeComplexRule(and1, and2, '|');
            IExpression or2 = makeComplexRule(and3, and4, '|');
            IExpression or3 = makeComplexRule(and5, and6, '|');
            IExpression or4 = makeComplexRule(or1, or2, '|');
            IExpression or5 = makeComplexRule(or3, or4, '|');

            DoesNotHaveState librarianNotDrunk = new DoesNotHaveState();
            librarianNotDrunk.setElementToValidate(itemLibrarian);
            librarianNotDrunk.setStateToValidate(stateDrunk);
            librarianNotDrunk.setFailMessage(EntregaConstants.bibliotecarioBorracho);
            HasStateRule hasIllegalState = checkStateRule(character, stateIllegal, EntregaConstants.failed);

            IExpression and = makeComplexRule(librarianNotDrunk, hasIllegalState, '&');

            IExpression caughtIllegal = makeComplexRule(and, or5, '&');
            HasStateRule isDead = checkStateRule(character, stateDead,EntregaConstants.estasMuerto);

            IExpression gameOverCondition = makeComplexRule(caughtIllegal, isDead, '|');
            Move resetCharacter = new Move("reset");
            resetCharacter.addAction(buildChangeContainerAction(character, roomHallway));
            resetCharacter.addAction(buildRemoveStatesAction(character, stateDead));
            character.setGameOverCondition(gameOverCondition, resetCharacter);

            character.setVictoryCondition(checkContainerRule(character, this.roomYard, ""));
        }
    }

    private void createActionRules() {
        ruleLibrarianIsNotInLibraryAccess = doesntHaveContainerRule(itemLibrarian, roomLibraryAccess,
                EntregaConstants.noEsta);

        ruleLibrarianIsInLibraryAccess = checkContainerRule(itemLibrarian, roomLibraryAccess, EntregaConstants.noEsta);
        ruleLibrarianIsInLibrary = checkContainerRule(itemLibrarian, roomLibrary, EntregaConstants.noEsta);
        ruleLibrarianIsInHallway = checkContainerRule(itemLibrarian, roomHallway, EntregaConstants.noEsta);
        ruleLibrarianIsInRoom1 = checkContainerRule(itemLibrarian, roomRoom1, EntregaConstants.noEsta);
        ruleLibrarianIsInRoom2 = checkContainerRule(itemLibrarian, roomRoom2, EntregaConstants.noEsta);
        ruleLibrarianIsInRoom3 = checkContainerRule(itemLibrarian, roomRoom3, EntregaConstants.noEsta);

        ruleIllegalEntry = new DoesNotHaveState();
        ruleIllegalEntry.setElementToValidate(game.playerManager);
        ruleIllegalEntry.setStateToValidate(stateIllegal);
        ruleIllegalEntry.setFailMessage(EntregaConstants.bibliotecarioFeliz);
    }

    //---------------------------------------------MOVES-----------------------------------------------

    private void addCharacterPickItemAction(Move move, Player character, ComplexElement item) {
        Action pickAction = buildChangeContainerAction(item, character);
        pickAction.setRules(checkEqualRule(game.playerManager,character,"not current character"));
        move.addAction(pickAction);
    }

    private void createMovesToPickElements() {
        moveTakeCredential = new Move(EntregaConstants.movePick);
        moveTakeCredential.setResultMessage(EntregaConstants.tomadoCredencial);
        moveTakeCredential.setRules(ruleCharacterInSalon1);

        movePickBottle = new Move(EntregaConstants.movePick);
        movePickBottle.setResultMessage(EntregaConstants.tomadaBotella);

        movePickKey = new Move(EntregaConstants.movePick);
        movePickKey.setResultMessage(EntregaConstants.tomadaLlave);

        movePickHammer = new Move(EntregaConstants.movePick);
        movePickHammer.setResultMessage(EntregaConstants.tomadoMartillo);

        movePickScrewdriver1 = new Move(EntregaConstants.movePick);
        movePickScrewdriver1.setResultMessage(EntregaConstants.tomadoDestornillador);
        movePickScrewdriver2 = new Move(EntregaConstants.movePick);
        movePickScrewdriver2.setResultMessage(EntregaConstants.tomadoDestornillador);

        movePickGlass1 = new Move(EntregaConstants.movePick);
        movePickGlass1.setResultMessage(EntregaConstants.tomadoVaso);
        movePickGlass2 = new Move(EntregaConstants.movePick);
        movePickGlass2.setResultMessage(EntregaConstants.tomadoVaso);
        for (Player character: game.playerManager.characters) {
            addCharacterPickItemAction(moveTakeCredential,character, itemCredential);
            addCharacterPickItemAction(movePickBottle,character, itemBottle);
            addCharacterPickItemAction(movePickKey,character, itemKey);
            addCharacterPickItemAction(movePickHammer,character, itemHammer);
            addCharacterPickItemAction(movePickScrewdriver1,character, itemScrewdriver1);
            addCharacterPickItemAction(movePickScrewdriver2,character, itemScrewdriver2);
            addCharacterPickItemAction(movePickGlass1,character, itemGlass1);
            addCharacterPickItemAction(movePickGlass2,character, itemGlass2);
        }
    }

    private void createAccessMoves() {
        moveGoToHallway = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToHallway,
                null, EntregaConstants.cambiadoAPasillo);
        moveGoToRoom1 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToRoom1,
                ruleCharacterInHallway, EntregaConstants.cambiadoASalon1);
        moveGoToRoom2 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToRoom2,
                null, EntregaConstants.cambiadoASalon2);
        moveGoToRoom3 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToRoom3,
                null, EntregaConstants.cambiadoASalon3);
        moveGoToLibraryAccess = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToLibraryAccess,
                null, EntregaConstants.cambiadoAAccesoBiblioteca);
        moveGoToLibrary = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToLibrary,
                ruleToAccessLibrary, EntregaConstants.cambiadoABiblioteca);
        moveGoToLibrary.addAction(actionAddIllegalState);
        moveGoToBasement = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToBasement,
                null, EntregaConstants.cambiadoASotano);
        moveIrAPatio = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToPatio, ruleBrokenWindow,
                EntregaConstants.cambiadoAPatio);

        moveGoToSubBasement = moveWithActionsAndRules(EntregaConstants.moveUse, actionChangeToSubBasement,
                null, EntregaConstants.cambiadoASubSotano);
        moveGoToSubBasement.addAction(actionKillCharacterNoHammer);
    }

    private void createMoves() {
        moveMovePainting = moveWithActionsAndRules(EntregaConstants.moveMover, actionSetVisibleSafe,
                ruleCharacterInSalon1, EntregaConstants.movedCuadroBarco);
        moveOpenSafe = moveWithActionsAndRules(EntregaConstants.moveAbrirCajaFuerte, actionSetVisibleCredential,
                ruleHoldsKey, EntregaConstants.abiertaCajaFuerte);

        moveMoveOldBook = moveWithActionsAndRules(EntregaConstants.moveMover, actionSetVisibleSecretPassage,
                null, EntregaConstants.movedLibroViejo);
        moveMoveBook = moveWithActionsAndRules(EntregaConstants.moveMover, null, null,
                EntregaConstants.movedLibro);

        //Moves para acceder a lugares
        this.createAccessMoves();

        movePutPictureOnCredential = moveWithActionsAndRules(EntregaConstants.movePutFoto, actionPutPictureOnCredential,
                null, EntregaConstants.cambiadoFotoDeCredencial);
        movePutPictureOnCredential.addAction(actionSetCredentialToValida);

        moveShowCredentialToLibrarian = moveWithActionsAndRules(EntregaConstants.moveMostrarCredencial, actionMakeLibrarianHappy,
                ruleCredentialValida, EntregaConstants.bibliotecarioFeliz);
        moveShowCredentialToLibrarian.addAction(actionSetCredentialToInvalid);
        moveGetLibrarianDrunk = moveWithActionsAndRules(EntregaConstants.moveEmborrachar, actionGetLibrarianDrunk,
                ruleToGetLibrarianDrunk, EntregaConstants.bibliotecarioBorracho);

        moveUseStairs = moveWithActionsAndRules(EntregaConstants.moveUse, actionKillCharacter,
                null, EntregaConstants.escaleraEnMalasCondiciones);

        moveShatterWindow = moveWithActionsAndRules(EntregaConstants.moveRomperVentana, actionShatterWindow, ruleHoldsHammer,
                EntregaConstants.seRompioVentana);

        //Moves for pick items
        this.createMovesToPickElements();

        this.createTimedMoves();
    }

    private void createTimedMoves() {
        emptyElement = createAndAddElement(EntregaConstants.emptyElement, null,null);

        wakeUpLibrarian = new TimedMove(EntregaConstants.librarianWakeUp);
        wakeUpLibrarian.setResultMessage(EntregaConstants.librarianHasWoken);
        wakeUpLibrarian.addAction(actionWakeUp);
        wakeUpLibrarian.addObserver(game);

        changeRoomLibrarianFromRoom3 = new Move(EntregaConstants.moveLibrarianFromRoom3);
        changeRoomLibrarianFromRoom3.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromRoom3.addObserver(game);
        changeRoomLibrarianFromRoom3.addAction(actionLibrarianToHallway);
        changeRoomLibrarianFromRoom3.setRules(ruleLibrarianIsInRoom3);

        changeRoomLibrarianFromRoom2 = new Move(EntregaConstants.moveLibrarianFromRoom2);
        changeRoomLibrarianFromRoom2.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromRoom2.addObserver(game);
        changeRoomLibrarianFromRoom2.addAction(actionLibrarianToHallway);
        changeRoomLibrarianFromRoom2.setChainedMove(changeRoomLibrarianFromRoom3);
        changeRoomLibrarianFromRoom2.setRules(ruleLibrarianIsInRoom2);

        changeRoomLibrarianFromRoom1 = new Move(EntregaConstants.moveLibrarianFromRoom1);
        changeRoomLibrarianFromRoom1.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromRoom1.addObserver(game);
        changeRoomLibrarianFromRoom1.addAction(actionLibrarianToHallway);
        changeRoomLibrarianFromRoom1.setChainedMove(changeRoomLibrarianFromRoom2);
        changeRoomLibrarianFromRoom1.setRules(ruleLibrarianIsInRoom1);

        changeRoomLibrarianFromLibrary = new Move(EntregaConstants.moveLibrarianFromLibrary);
        changeRoomLibrarianFromLibrary.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromLibrary.addObserver(game);
        changeRoomLibrarianFromLibrary.addAction(actionLibrarianToLibraryAccess);
        changeRoomLibrarianFromLibrary.setChainedMove(changeRoomLibrarianFromRoom1);
        changeRoomLibrarianFromLibrary.setRules(ruleLibrarianIsInLibrary);

        changeRoomLibrarianFromHallway = new Move(EntregaConstants.moveLibrarianFromHallway);
        changeRoomLibrarianFromHallway.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromHallway.addObserver(game);
        changeRoomLibrarianFromHallway.addAction(actionLibrarianToRoom1);
        changeRoomLibrarianFromHallway.addAction(actionLibrarianToRoom2);
        changeRoomLibrarianFromHallway.addAction(actionLibrarianToRoom3);
        changeRoomLibrarianFromHallway.addAction(actionLibrarianToLibraryAccess);
        changeRoomLibrarianFromHallway.setRandom(true);
        changeRoomLibrarianFromHallway.setChainedMove(changeRoomLibrarianFromLibrary);
        changeRoomLibrarianFromHallway.setRules(ruleLibrarianIsInHallway);

        changeRoomLibrarianFromLibraryAccess = new TimedMove(EntregaConstants.librarianRandom);
        changeRoomLibrarianFromLibraryAccess.setResultMessage(EntregaConstants.librarianChangedToRoom);
        changeRoomLibrarianFromLibraryAccess.addObserver(game);
        changeRoomLibrarianFromLibraryAccess.addAction(actionLibrarianToLibrary);
        changeRoomLibrarianFromLibraryAccess.addAction(actionLibrarianToHallway);
        changeRoomLibrarianFromLibraryAccess.setRandom(true);
        changeRoomLibrarianFromLibraryAccess.setChainedMove(changeRoomLibrarianFromHallway);
        changeRoomLibrarianFromLibraryAccess.setRules(ruleLibrarianIsInLibraryAccess);

        oneTimeTwoMinutes.addObserver(wakeUpLibrarian);
        manyTimesFourMinutes.addObserver(changeRoomLibrarianFromLibraryAccess);

        emptyElement.addMove(wakeUpLibrarian);
        emptyElement.addMove(changeRoomLibrarianFromLibraryAccess);
        emptyElement.addMove(changeRoomLibrarianFromLibrary);
        emptyElement.addMove(changeRoomLibrarianFromHallway);
        emptyElement.addMove(changeRoomLibrarianFromRoom1);
        emptyElement.addMove(changeRoomLibrarianFromRoom2);
        emptyElement.addMove(changeRoomLibrarianFromRoom3);
    }

    private void addMovesItemsInSalon1() {
        itemPaintingShip.addMove(moveMovePainting);
        itemKey.addMove(moveOpenSafe);

        itemCredential.addMove(moveTakeCredential);
        itemCredential.addMove(movePutPictureOnCredential);

        itemBottle.addMove(movePickBottle);
        itemGlass1.addMove(movePickGlass1);
        itemGlass2.addMove(movePickGlass2);
    }

    private void addMovesItemsInSalon2() {
        itemHammer.addMove(movePickHammer);
        itemHammer.addMove(moveShatterWindow);
        itemScrewdriver1.addMove(movePickScrewdriver1);
        itemScrewdriver2.addMove(movePickScrewdriver2);
    }

    private void addMovesItemsInSalon3() {
        itemKey.addMove(movePickKey);
    }

    private void addMovesItemsInAccesoBiblioteca() {
        itemLibrarian.addMove(moveGetLibrarianDrunk);
        itemLibrarian.addMove(moveShowCredentialToLibrarian);
    }

    private void addMovesItemsInBiblioteca() {
        itemOldBook.addMove(moveMoveOldBook);
        itemBook1.addMove(moveMoveBook);
        itemBook2.addMove(moveMoveBook);
        itemBook3.addMove(moveMoveBook);
        itemBook4.addMove(moveMoveBook);
        itemBook5.addMove(moveMoveBook);
        itemBook6.addMove(moveMoveBook);
        itemBook7.addMove(moveMoveBook);
        itemBook8.addMove(moveMoveBook);
        itemBook9.addMove(moveMoveBook);
    }

    private void addMovesItemsInSotano() {
        itemRailing.addMove(moveGoToSubBasement);
        itemStairs.addMove(moveUseStairs);
    }

    private void addMovesItemsInSubSotano() {
        itemStairsSubBasement.addMove(moveUseStairs);
        roomYard.addMove(moveIrAPatio);
    }

    private void addMoves() {
        addMovesItemsInSalon1();
        addMovesItemsInSalon2();
        addMovesItemsInSalon3();
        addMovesItemsInAccesoBiblioteca();
        addMovesItemsInBiblioteca();
        addMovesItemsInSotano();
        addMovesItemsInSubSotano();

        doorLibraryAccessToHallway.addMove(moveGoToHallway);
        doorRoom1ToHallway.addMove(moveGoToHallway);
        doorRoom2ToHallway.addMove(moveGoToHallway);
        doorRoom3ToHallway.addMove(moveGoToHallway);

        doorHallwayToLibraryAccess.addMove(moveGoToLibraryAccess);
        doorLibraryToLibraryAccess.addMove(moveGoToLibraryAccess);
        doorLibraryToBasement.addMove(moveGoToBasement);
        doorHallwayToRoom1.addMove(moveGoToRoom1);
        doorHallwayToRoom2.addMove(moveGoToRoom2);
        doorHallwayToRoom3.addMove(moveGoToRoom3);
        doorLibraryAccessToLibrary.addMove(moveGoToLibrary);
        doorSubBasementToYard.addMove(moveIrAPatio);
    }

    //-------------------------------------------ITEMS---------------------------------------------

    private void createItems() {
        createItemsSalon1();
        createItemsSalon2();
        createItemsSalon3();
        createItemsAccesoBiblioteca();
        createItemsBiblioteca();
        createItemsSotano();
        createItemsSubSotano();
    }

    private void createItemsSalon1() {
        createAndAddElement(EntregaConstants.tableSalon1, roomRoom1,null);
        itemBottle = createAndAddElement(EntregaConstants.bottleSalon1, roomRoom1,null);
        itemGlass1 = createAndAddElement(EntregaConstants.glass1Salon1, roomRoom1,null);
        itemGlass2 = createAndAddElement(EntregaConstants.glass2Salon1, roomRoom1,null);
        createAndAddElement(EntregaConstants.chair1Salon1, roomRoom1,null);
        createAndAddElement(EntregaConstants.chair2Salon1, roomRoom1,null);
        createAndAddElement(EntregaConstants.cuadroTrenSalon1, roomRoom1,null);

        itemPaintingShip = createAndAddElement(EntregaConstants.cuadroBarcoSalon1, roomRoom1,null);
        itemSafe = createAndAddElement(EntregaConstants.cajaFuerteSalon1, itemPaintingShip,null);
        itemCredential = createAndAddElement(EntregaConstants.credencialSalon1, itemSafe,null);
    }

    private void createItemsSalon2() {
        itemHammer = createAndAddElement(EntregaConstants.martilloSalon2, roomRoom2,null);
        itemScrewdriver1 = createAndAddElement(EntregaConstants.destornillador1Salon2, roomRoom2,null);
        itemScrewdriver2 = createAndAddElement(EntregaConstants.destornillador2Salon2, roomRoom2,null);
    }

    private void createItemsSalon3() {
        itemKey = createAndAddElement(EntregaConstants.keySalon3, roomRoom3,null);
    }

    private void createItemsAccesoBiblioteca() {
        itemLibrarian = createAndAddElement(EntregaConstants.bibliotecario, roomLibraryAccess,null);
    }

    private void createItemsBiblioteca() {
        itemOldBook = createAndAddElement(EntregaConstants.libroViejo, roomLibrary,null);
        createAndAddElement(EntregaConstants.estante, roomLibrary,null);
        itemBook1 = createAndAddElement(EntregaConstants.libro1, roomLibrary,null);
        itemBook2 = createAndAddElement(EntregaConstants.libro2, roomLibrary,null);
        itemBook3 = createAndAddElement(EntregaConstants.libro3, roomLibrary,null);
        itemBook4 = createAndAddElement(EntregaConstants.libro4, roomLibrary,null);
        itemBook5 = createAndAddElement(EntregaConstants.libro5, roomLibrary,null);
        itemBook6 = createAndAddElement(EntregaConstants.libro6, roomLibrary,null);
        itemBook7 = createAndAddElement(EntregaConstants.libro7, roomLibrary,null);
        itemBook8 = createAndAddElement(EntregaConstants.libro8, roomLibrary,null);
        itemBook9 = createAndAddElement(EntregaConstants.libro9, roomLibrary,null);
    }

    private void createItemsSotano() {
        itemRailing = createAndAddElement(EntregaConstants.baranda, roomBasement, null);
        itemStairs = createAndAddElement(EntregaConstants.escaleraOxidada, roomBasement, null);
    }

    private void createItemsSubSotano() {
        itemWindow = createAndAddElement(EntregaConstants.ventana, roomSubBasement, null);
        itemStairsSubBasement = createAndAddElement(EntregaConstants.escaleraOxidada, roomSubBasement, null);
    }
}