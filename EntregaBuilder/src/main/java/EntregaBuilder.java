import creation.GameBuilderImp;
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
    private TimedMove wakeUpLibrarian;
    private TimedMove changeRoomLibrarian;

    private Element stateAsleep;
    private Action actionWakeUp;
    private Action actionLibrerianToLibraryAccess;
    private Action actionLibrerianToRoom1;
    private Action actionLibrerianToRoom2;
    private Action actionLibrerianToRoom3;

    private DoesNotHaveContainerRule ruleLibrerianIsInLibraryAccess;
    private DoesNotHaveContainerRule ruleLibrerianIsInRoom1;
    private DoesNotHaveContainerRule ruleLibrerianIsInRoom2;
    private DoesNotHaveContainerRule ruleLibrerianIsInRoom3;

    private ComplexElement elementoVacio;

    private void setTimeConditions() {
        elementoVacio = createAndAddElement(EntregaConstants.elementoVacio, null,null);

        oneTimeTwoMinutes = new TimeCondition(10,1);
        manyTimesFourMinutes = new TimeCondition(15,99999999);
        wakeUpLibrarian = new TimedMove(EntregaConstants.librerianWakeUp);

        changeRoomLibrarian = new TimedMove(EntregaConstants.librarianRandom);

        wakeUpLibrarian.setResultMessage(EntregaConstants.LibrarianHasWoken);
        changeRoomLibrarian.setResultMessage(EntregaConstants.librarianRandom);

        stateAsleep = new Element(EntregaConstants.sleeping);
        actionWakeUp = buildRemoveStatesAction(itemBibliotecario, stateAsleep);

        wakeUpLibrarian.addAction(actionWakeUp);

        wakeUpLibrarian.addObserver(game);
        changeRoomLibrarian.addObserver(game);

        actionLibrerianToLibraryAccess = buildChangeContainerAction(itemBibliotecario, roomPasillo);
        actionLibrerianToRoom1 = buildChangeContainerAction(itemBibliotecario, roomSalon1);
        actionLibrerianToRoom2 = buildChangeContainerAction(itemBibliotecario, roomSalon2);
        actionLibrerianToRoom3 = buildChangeContainerAction(itemBibliotecario, roomSalon3);

        changeRoomLibrarian.addAction(actionLibrerianToLibraryAccess);
        changeRoomLibrarian.addAction(actionLibrerianToRoom1);
        changeRoomLibrarian.addAction(actionLibrerianToRoom2);
        changeRoomLibrarian.addAction(actionLibrerianToRoom3);
        changeRoomLibrarian.setRandom(true);

        ruleLibrerianIsInLibraryAccess = doesntHaveContainerRule(itemBibliotecario, roomPasillo, EntregaConstants.noEsta);
        ruleLibrerianIsInRoom1 = doesntHaveContainerRule(itemBibliotecario, roomSalon1, EntregaConstants.noEsta);
        ruleLibrerianIsInRoom2 = doesntHaveContainerRule(itemBibliotecario, roomSalon2, EntregaConstants.noEsta);
        ruleLibrerianIsInRoom3 = doesntHaveContainerRule(itemBibliotecario, roomSalon3, EntregaConstants.noEsta);

        actionLibrerianToLibraryAccess.setRules(ruleLibrerianIsInLibraryAccess);
        actionLibrerianToRoom1.setRules(ruleLibrerianIsInRoom1);
        actionLibrerianToRoom2.setRules(ruleLibrerianIsInRoom2);
        actionLibrerianToRoom3.setRules(ruleLibrerianIsInRoom3);

        game.setTimeObserver(oneTimeTwoMinutes);
        game.setTimeObserver(manyTimesFourMinutes);
        oneTimeTwoMinutes.addObserver(wakeUpLibrarian);
        manyTimesFourMinutes.addObserver(changeRoomLibrarian);

        elementoVacio.addMove(wakeUpLibrarian);
        elementoVacio.addMove(changeRoomLibrarian);

        oneTimeTwoMinutes.initialize();
        manyTimesFourMinutes.initialize();
    }

    //Characters
    private ArrayList<Player> characters;

    //Rooms
    private ComplexElement roomPasillo;
    private ComplexElement roomSalon1;
    private ComplexElement roomSalon2;
    private ComplexElement roomSalon3;
    private ComplexElement roomSotano;
    private ComplexElement roomSubSotano;
    private ComplexElement roomBiblioteca;
    private ComplexElement roomAccesoBiblioteca;
    private ComplexElement roomPatio;

    //Items
    private ComplexElement itemFoto;
    //Items salon 1
    private ComplexElement itemBotella;
    private ComplexElement itemVaso1;
    private ComplexElement itemVaso2;
    private ComplexElement itemCuadroBarco;
    private ComplexElement itemCajaFuerte;
    private ComplexElement itemCredencial;
    //Items salon 2
    private ComplexElement itemMartillo;
    private ComplexElement itemDestornillador1;
    private ComplexElement itemDestornillador2;
    //Items salon 3
    private ComplexElement itemLlave;
    //Items Acceso Bilioteca
    private ComplexElement itemBibliotecario;
    //Items Bilioteca
    private ComplexElement itemLibroViejo;
    private ComplexElement itemLibro1;
    private ComplexElement itemLibro2;
    private ComplexElement itemLibro3;
    private ComplexElement itemLibro4;
    private ComplexElement itemLibro5;
    private ComplexElement itemLibro6;
    private ComplexElement itemLibro7;
    private ComplexElement itemLibro8;
    private ComplexElement itemLibro9;

    //Items Sotano
    private ComplexElement itemEscalera;
    private ComplexElement itemBaranda;

    //Items SubSotano
    private ComplexElement itemVentana;
    private ComplexElement itemEscaleraSubSotano;

    //Rules
    private HasContainerRule ruleTenerLlave;
    private HasContainerRule ruleCharacterInSalon1;
    private HasContainerRule ruleCharacterInPasillo;
    private HasContainerRule ruleTenerMartillo;
    private HasStateRule ruleCredencialValida;
    private HasContainerRule ruleCredencialInvalida;
    private HasContainerRule ruleBajaAlSubSotanoSinElMartillo;
    private HasStateRule ruleVentanaRota;
    private IExpression ruleParaEmborracharAlBibliotecario;
    private IExpression ruleParaIngresarALaBiblioteca;

    //Item actions
    private Action actionSetVisibleCajaFuerte;
    private Action actionSetVisibleCredencial;
    private Action actionSetVisiblePasajeSecreto;
    private Action actionChangeToPasillo;
    private Action actionChangeToSalon1;
    private Action actionChangeToSalon2;
    private Action actionChangeToSalon3;
    private Action actionChangeToAccesoBiblioteca;
    private Action actionChangeToBiblioteca;
    private Action actionChangeToSubSotano;
    private Action actionChangeToSotano;
    private Action actionChangeToPatio;
    private Action actionPutFotoEnCredencial;
    private Action actionSetCredencialToValida;
    private Action actionSetCredencialToInvalida;
    private Action actionMakeBibliotecarioFeliz;
    private Action actionMakeBibliotecarioBorracho;
    private Action actionKillCharacter;
    private Action actionKillCharacterNoMartillo;
    private Action actionRomperVentana;

    //Moves
    private Move moveMoverCuadro;
    private Move moveAbrirCajaFuerte;
    private Move moveTomarCredencial;
    private Move moveIrAPasillo;
    private Move moveIrASalon1;
    private Move moveIrASalon2;
    private Move moveIrASalon3;
    private Move moveIrAAccesoBiblioteca;
    private Move moveIrABiblioteca;
    private Move moveIrASotano;
    private Move moveIrASubSotano;
    private Move moveIrAPatio;

    private Move movePonerFotoEnCredencial;
    private Move moveMostrarCredencialAlBibliotecario;
    private Move moveEmborracharAlBibliotecario;
    private Move moveUsarEscalera;
    private Move moveTomarBotella;
    private Move moveTomarLlave;
    private Move moveTomarMartillo;
    private Move moveTomarDestornillador1;
    private Move moveTomarDestornillador2;
    private Move moveTomarVaso1;
    private Move moveTomarVaso2;
    private Move moveMoverLibroViejo;
    private Move moveMoverLibro;
    private Move moveRomperVentana;

    //Doors
    private ComplexElement doorPasilloToSalon1;
    private ComplexElement doorPasilloToSalon2;
    private ComplexElement doorPasilloToSalon3;
    private ComplexElement doorPasilloToAccesoBiblioteca;
    private ComplexElement doorSalon1ToPasillo;
    private ComplexElement doorSalon2ToPasillo;
    private ComplexElement doorSalon3ToPasillo;
    private ComplexElement doorAccesoBibliotecaToPasillo;
    private ComplexElement doorAccesoBibliotecaToBiblioteca;
    private ComplexElement doorBibliotecaToAccesoBiblioteca;
    private ComplexElement doorBibliotecaToSotano;
    private ComplexElement doorSubSotanoToPatio;

    //States
    private Element stateValido;
    private Element stateInvalido;
    private Element stateOpen;
    private Element stateFeliz;
    private Element stateBorracho;
    private Element stateMuerto;
    private Element stateRoto;

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

    public void setElements() {
        createRooms();
        createStates();
        createDoors();
        createItems();
        defineCharacter();
        createRules();
        createItemActions();
        defineVictoryRule();
        createMoves();
        addMoves();
        setTimeConditions();
    }

    private void defineVictoryRule() {}

    private void defineCharacter() {
        characters = new ArrayList<>();
        for (int i = 0; i < EntregaConstants.numberOfPlayers; i++) {
            Player character = createAndAddPlayer("character" + i, roomPasillo, null);
            itemFoto = createAndAddElement(EntregaConstants.photo,character,null);
            createAndAddElement(EntregaConstants.pen,character,null);
            characters.add(character);
        }
        game.playerManager.characters = characters;
    }

    private void createRooms() {
        roomPasillo = createAndAddElement(EntregaConstants.roomPasillo, null, null);
        roomSalon1 = createAndAddElement(EntregaConstants.roomSalon1, null, null);
        roomSalon2 = createAndAddElement(EntregaConstants.roomSalon2, null, null);
        roomSalon3 = createAndAddElement(EntregaConstants.roomSalon3, null, null);
        roomSotano = createAndAddElement(EntregaConstants.roomSotano, null, null);
        roomSubSotano = createAndAddElement(EntregaConstants.roomSubSotano, null, null);
        roomBiblioteca = createAndAddElement(EntregaConstants.roomBiblioteca, null, null);
        roomAccesoBiblioteca = createAndAddElement(EntregaConstants.roomAccesoBiblioteca, null, null);
        roomPatio = createAndAddElement(EntregaConstants.roomPatio, null, null);
    }

    private void createStates() {
        stateOpen = new Element(EntregaConstants.abierta);
        stateInvalido = new Element(EntregaConstants.invalido);
        stateValido = new Element(EntregaConstants.valido);
        stateFeliz = new Element(EntregaConstants.feliz);
        stateBorracho = new Element(EntregaConstants.borracho);
        stateMuerto = new Element(EntregaConstants.muerto);
        stateRoto = new Element(EntregaConstants.roto);
    }

    private void createDoors() {
        //Doors from Pasillo to another one
        doorPasilloToSalon1 = createAndAddElement(EntregaConstants.doorPasilloToSalon1, roomPasillo, stateOpen);
        doorPasilloToSalon2 = createAndAddElement(EntregaConstants.doorPasilloToSalon2, roomPasillo, stateOpen);
        doorPasilloToSalon3 = createAndAddElement(EntregaConstants.doorPasilloToSalon3, roomPasillo, stateOpen);
        doorPasilloToAccesoBiblioteca = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomPasillo, stateOpen);

        //Doors to Pasillo from another one
        doorSalon1ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon1, stateOpen);
        doorSalon2ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon2, stateOpen);
        doorSalon3ToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomSalon3, stateOpen);
        doorAccesoBibliotecaToPasillo = createAndAddElement(EntregaConstants.doorPasillo, roomAccesoBiblioteca, stateOpen);

        //Others doors
        doorAccesoBibliotecaToBiblioteca = createAndAddElement(EntregaConstants.doorBiblioteca, roomAccesoBiblioteca, stateOpen);
        doorBibliotecaToAccesoBiblioteca = createAndAddElement(EntregaConstants.doorAccesoBiblioteca, roomBiblioteca, stateOpen);
        doorBibliotecaToSotano = createAndAddElement(EntregaConstants.doorBibliotecaToSotano, itemLibroViejo, stateOpen);
        doorSubSotanoToPatio = createAndAddElement(EntregaConstants.doorSubSotanoToPatio, roomSubSotano,stateOpen);
    }

    private void createChangeRoomItemsAction() {
        actionChangeToPasillo = buildChangeContainerAction(game.playerManager, roomPasillo);
        actionChangeToSalon1 = buildChangeContainerAction(game.playerManager,roomSalon1);
        actionChangeToSalon2 = buildChangeContainerAction(game.playerManager,roomSalon2);
        actionChangeToSalon3 = buildChangeContainerAction(game.playerManager,roomSalon3);
        actionChangeToAccesoBiblioteca = buildChangeContainerAction(game.playerManager, roomAccesoBiblioteca);
        actionChangeToBiblioteca = buildChangeContainerAction(game.playerManager, roomBiblioteca);
        actionChangeToSubSotano = buildChangeContainerAction(game.playerManager, roomSubSotano);
        actionChangeToSotano = buildChangeContainerAction(game.playerManager, roomSotano);
        actionChangeToPatio = buildChangeContainerAction(game.playerManager, roomPatio);
    }

    private void createItemActions() {
        createChangeRoomItemsAction();

        actionSetVisibleCajaFuerte = buildChangeContainerAction(itemCajaFuerte,roomSalon1);
        actionSetVisibleCredencial = buildChangeContainerAction(itemCredencial,roomSalon1);
        actionSetVisiblePasajeSecreto = buildChangeContainerAction(doorBibliotecaToSotano,roomBiblioteca);

        actionPutFotoEnCredencial = buildChangeContainerAction(itemFoto, itemCredencial);
        actionSetCredencialToValida = buildAddStatesAction(itemCredencial, stateValido);
        actionSetCredencialToInvalida = buildAddStatesAction(itemCredencial, stateInvalido);
        actionSetCredencialToInvalida.setRules(ruleCredencialInvalida);

        actionMakeBibliotecarioFeliz = buildAddStatesAction(itemBibliotecario, stateFeliz);
        actionMakeBibliotecarioFeliz.setRules(ruleCredencialValida);
        actionMakeBibliotecarioBorracho = buildAddStatesAction(itemBibliotecario, stateBorracho);
        actionKillCharacter = buildAddStatesAction(game.playerManager, stateMuerto);
        actionKillCharacterNoMartillo = buildAddStatesAction(game.playerManager, stateMuerto);
        actionKillCharacterNoMartillo.setRules(ruleBajaAlSubSotanoSinElMartillo);
        actionRomperVentana = buildAddStatesAction(itemVentana,stateRoto);
    }

    private void createRulesCharacterInRooms() {
        ruleCharacterInSalon1 = checkContainerRule(game.playerManager,roomSalon1,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomSalon2,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomSalon3,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomAccesoBiblioteca,EntregaConstants.noEstaEnLaRoom);
        ruleCharacterInPasillo = checkContainerRule(game.playerManager,roomPasillo,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomBiblioteca,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomSotano,EntregaConstants.noEstaEnLaRoom);
        checkContainerRule(game.playerManager,roomSubSotano,EntregaConstants.noEstaEnLaRoom);
    }


    private void createRulesForAccessToLibrary() {
        OrExpression orExpressionParaPasarABiblioteca = new OrExpression();
        HasStateRule ruleBibliotecarioFeliz = checkStateRule(itemBibliotecario, stateFeliz,EntregaConstants.noEstaFeliz);
        HasStateRule ruleBibliotecarioBorracho = checkStateRule(itemBibliotecario, stateBorracho,EntregaConstants.noEstaBorracho);
        orExpressionParaPasarABiblioteca.setLeftExpression(ruleBibliotecarioFeliz);
        orExpressionParaPasarABiblioteca.setRightExpression(ruleBibliotecarioBorracho);
        orExpressionParaPasarABiblioteca.setFailMessage(EntregaConstants.noSePuedePasarALaBiblioteca);
        ruleParaIngresarALaBiblioteca = orExpressionParaPasarABiblioteca;
    }

    private void createRulesForGetDrunkToLibrarian() {
        HasContainerRule ruleTieneVaso1 = checkContainerRule(itemVaso1, game.playerManager, EntregaConstants.necesitaElVaso);
        HasContainerRule ruleTieneVaso2 = checkContainerRule(itemVaso2, game.playerManager, EntregaConstants.necesitaElVaso);

        OrExpression orExpressionParaVasos = new OrExpression();
        orExpressionParaVasos.setLeftExpression(ruleTieneVaso1);
        orExpressionParaVasos.setRightExpression(ruleTieneVaso2);
        orExpressionParaVasos.setFailMessage(EntregaConstants.noTieneVasos);

        HasContainerRule ruleTieneBotella = checkContainerRule(itemBotella, game.playerManager, EntregaConstants.necesitaLaBotella);
        AndExpression andExpressionParaEmborrachar = new AndExpression();
        andExpressionParaEmborrachar.setLeftExpression(ruleTieneBotella);
        andExpressionParaEmborrachar.setRightExpression(orExpressionParaVasos);
        andExpressionParaEmborrachar.setFailMessage(EntregaConstants.noSePuedeEmborrachar);
        ruleParaEmborracharAlBibliotecario = andExpressionParaEmborrachar;
    }

    private void createRules() {
        createRulesCharacterInRooms();
        ruleTenerLlave = checkContainerRule(itemLlave,game.playerManager,EntregaConstants.necesitaTenerLlaveSalon3);
        ruleTenerMartillo = checkContainerRule(itemMartillo,game.playerManager,EntregaConstants.necesitaTenerMartillo);
        ruleVentanaRota = checkStateRule(itemVentana,stateRoto,EntregaConstants.necesitaEstarRotaLaVentana);
        ruleCredencialValida = checkStateRule(itemCredencial, stateValido, EntregaConstants.necesitaSerValida);
        ruleCredencialInvalida = checkContainerRule(itemFoto,game.playerManager,EntregaConstants.fotoNoPegada);
        ruleBajaAlSubSotanoSinElMartillo = checkContainerRule(itemMartillo,roomSalon2,EntregaConstants.noTieneElMartillo);

        //Reglas para poder emborrachar al bibliotecario
        this.createRulesForGetDrunkToLibrarian();

        //Reglas para poder pasar a la biblioteca
        this.createRulesForAccessToLibrary();

        //Regla para perder
        //Ganancia
        for (Player character:game.playerManager.characters) {
            Move resetCharacter = new Move("reset");
            resetCharacter.addAction(buildChangeContainerAction(character,roomPasillo));
            resetCharacter.addAction(buildRemoveStatesAction(character,stateMuerto));
            character.setGameOverCondition(checkStateRule(character,stateMuerto,EntregaConstants.estasMuerto),
                    resetCharacter);
            character.setVictoryCondition(checkContainerRule(character, this.roomPatio, ""));
        }
    }

    private void addCharacterPickItemAction(Move move, Player character, ComplexElement item) {
        Action pickAction = buildChangeContainerAction(item, character);
        pickAction.setRules(checkEqualRule(game.playerManager,character,"not current character"));
        move.addAction(pickAction);
    }



    private void createMovesToPickElements() {
        moveTomarCredencial = new Move(EntregaConstants.movePick);
        moveTomarCredencial.setResultMessage(EntregaConstants.tomadoCredencial);
        moveTomarCredencial.setRules(ruleCharacterInSalon1);

        moveTomarBotella = new Move(EntregaConstants.movePick);
        moveTomarBotella.setResultMessage(EntregaConstants.tomadaBotella);

        moveTomarLlave = new Move(EntregaConstants.movePick);
        moveTomarLlave.setResultMessage(EntregaConstants.tomadaLlave);

        moveTomarMartillo = new Move(EntregaConstants.movePick);
        moveTomarMartillo.setResultMessage(EntregaConstants.tomadoMartillo);

        moveTomarDestornillador1 = new Move(EntregaConstants.movePick);
        moveTomarDestornillador1.setResultMessage(EntregaConstants.tomadoDestornillador);
        moveTomarDestornillador2 = new Move(EntregaConstants.movePick);
        moveTomarDestornillador2.setResultMessage(EntregaConstants.tomadoDestornillador);

        moveTomarVaso1 = new Move(EntregaConstants.movePick);
        moveTomarVaso1.setResultMessage(EntregaConstants.tomadoVaso);
        moveTomarVaso2 = new Move(EntregaConstants.movePick);
        moveTomarVaso2.setResultMessage(EntregaConstants.tomadoVaso);
        for (Player character: game.playerManager.characters) {
            addCharacterPickItemAction(moveTomarCredencial,character,itemCredencial);
            addCharacterPickItemAction(moveTomarBotella,character,itemBotella);
            addCharacterPickItemAction(moveTomarLlave,character,itemLlave);
            addCharacterPickItemAction(moveTomarMartillo,character,itemMartillo);
            addCharacterPickItemAction(moveTomarDestornillador1,character,itemDestornillador1);
            addCharacterPickItemAction(moveTomarDestornillador2,character,itemDestornillador2);
            addCharacterPickItemAction(moveTomarVaso1,character,itemVaso1);
            addCharacterPickItemAction(moveTomarVaso2,character,itemVaso2);
        }
    }

    private void createAccessMoves() {
        moveIrAPasillo = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToPasillo,
                null, EntregaConstants.cambiadoAPasillo);
        moveIrASalon1 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon1,
                ruleCharacterInPasillo, EntregaConstants.cambiadoASalon1);
        moveIrASalon2 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon2,
                null, EntregaConstants.cambiadoASalon2);
        moveIrASalon3 = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSalon3,
                null, EntregaConstants.cambiadoASalon3);
        moveIrAAccesoBiblioteca = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToAccesoBiblioteca,
                null, EntregaConstants.cambiadoAAccesoBiblioteca);
        moveIrABiblioteca = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToBiblioteca,
                ruleParaIngresarALaBiblioteca, EntregaConstants.cambiadoABiblioteca);
        moveIrASotano = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToSotano,
                null, EntregaConstants.cambiadoASotano);
        moveIrAPatio = moveWithActionsAndRules(EntregaConstants.moveIrA, actionChangeToPatio, ruleVentanaRota,
                EntregaConstants.cambiadoAPatio);

        moveIrASubSotano = moveWithActionsAndRules(EntregaConstants.moveUse, actionChangeToSubSotano,
                null, EntregaConstants.cambiadoASubSotano);
        moveIrASubSotano.addAction(actionKillCharacterNoMartillo);
    }

    private void createMoves() {
        moveMoverCuadro = moveWithActionsAndRules(EntregaConstants.moveMover, actionSetVisibleCajaFuerte,
                ruleCharacterInSalon1, EntregaConstants.movedCuadroBarco);
        moveAbrirCajaFuerte = moveWithActionsAndRules(EntregaConstants.moveAbrirCajaFuerte, actionSetVisibleCredencial,
                ruleTenerLlave, EntregaConstants.abiertaCajaFuerte);

        moveMoverLibroViejo = moveWithActionsAndRules(EntregaConstants.moveMover, actionSetVisiblePasajeSecreto,
                null, EntregaConstants.movedLibroViejo);
        moveMoverLibro = moveWithActionsAndRules(EntregaConstants.moveMover, null, null,
                EntregaConstants.movedLibro);

        //Moves para acceder a lugares
        this.createAccessMoves();

        movePonerFotoEnCredencial = moveWithActionsAndRules(EntregaConstants.movePutFoto, actionPutFotoEnCredencial,
                null, EntregaConstants.cambiadoFotoDeCredencial);
        movePonerFotoEnCredencial.addAction(actionSetCredencialToValida);

        moveMostrarCredencialAlBibliotecario = moveWithActionsAndRules(EntregaConstants.moveMostrarCredencial, actionMakeBibliotecarioFeliz,
                ruleCredencialValida, EntregaConstants.bibliotecarioFeliz);
        moveMostrarCredencialAlBibliotecario.addAction(actionSetCredencialToInvalida);
        moveEmborracharAlBibliotecario = moveWithActionsAndRules(EntregaConstants.moveEmborrachar, actionMakeBibliotecarioBorracho,
                ruleParaEmborracharAlBibliotecario, EntregaConstants.bibliotecarioBorracho);

        moveUsarEscalera = moveWithActionsAndRules(EntregaConstants.moveUse, actionKillCharacter,
                null, EntregaConstants.escaleraEnMalasCondiciones);

        moveRomperVentana = moveWithActionsAndRules(EntregaConstants.moveRomperVentana, actionRomperVentana, ruleTenerMartillo,
                EntregaConstants.seRompioVentana);

        //Moves for pick items
        this.createMovesToPickElements();

    }

    private void addMovesItemsInSalon1() {
        itemCuadroBarco.addMove(moveMoverCuadro);
        itemLlave.addMove(moveAbrirCajaFuerte);

        itemCredencial.addMove(moveTomarCredencial);
        itemCredencial.addMove(movePonerFotoEnCredencial);

        itemBotella.addMove(moveTomarBotella);
        itemVaso1.addMove(moveTomarVaso1);
        itemVaso2.addMove(moveTomarVaso2);
    }

    private void addMovesItemsInSalon2() {
        itemMartillo.addMove(moveTomarMartillo);
        itemMartillo.addMove(moveRomperVentana);
        itemDestornillador1.addMove(moveTomarDestornillador1);
        itemDestornillador2.addMove(moveTomarDestornillador2);
    }

    private void addMovesItemsInSalon3() {
        itemLlave.addMove(moveTomarLlave);
    }

    private void addMovesItemsInAccesoBiblioteca() {
        itemBibliotecario.addMove(moveEmborracharAlBibliotecario);
        itemBibliotecario.addMove(moveMostrarCredencialAlBibliotecario);
    }

    private void addMovesItemsInBiblioteca() {
        itemLibroViejo.addMove(moveMoverLibroViejo);
        itemLibro1.addMove(moveMoverLibro);
        itemLibro2.addMove(moveMoverLibro);
        itemLibro3.addMove(moveMoverLibro);
        itemLibro4.addMove(moveMoverLibro);
        itemLibro5.addMove(moveMoverLibro);
        itemLibro6.addMove(moveMoverLibro);
        itemLibro7.addMove(moveMoverLibro);
        itemLibro8.addMove(moveMoverLibro);
        itemLibro9.addMove(moveMoverLibro);
    }

    private void addMovesItemsInSotano() {
        itemBaranda.addMove(moveIrASubSotano);
        itemEscalera.addMove(moveUsarEscalera);
    }

    private void addMovesItemsInSubSotano() {
        itemEscaleraSubSotano.addMove(moveUsarEscalera);
        roomPatio.addMove(moveIrAPatio);
    }

    private void addMoves() {
        addMovesItemsInSalon1();
        addMovesItemsInSalon2();
        addMovesItemsInSalon3();
        addMovesItemsInAccesoBiblioteca();
        addMovesItemsInBiblioteca();
        addMovesItemsInSotano();
        addMovesItemsInSubSotano();

        doorAccesoBibliotecaToPasillo.addMove(moveIrAPasillo);
        doorSalon1ToPasillo.addMove(moveIrAPasillo);
        doorSalon2ToPasillo.addMove(moveIrAPasillo);
        doorSalon3ToPasillo.addMove(moveIrAPasillo);

        doorPasilloToAccesoBiblioteca.addMove(moveIrAAccesoBiblioteca);
        doorBibliotecaToAccesoBiblioteca.addMove(moveIrAAccesoBiblioteca);
        doorBibliotecaToSotano.addMove(moveIrASotano);
        doorPasilloToSalon1.addMove(moveIrASalon1);
        doorPasilloToSalon2.addMove(moveIrASalon2);
        doorPasilloToSalon3.addMove(moveIrASalon3);
        doorAccesoBibliotecaToBiblioteca.addMove(moveIrABiblioteca);
        doorSubSotanoToPatio.addMove(moveIrAPatio);
    }

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
        createAndAddElement(EntregaConstants.tableSalon1, roomSalon1,null);
        itemBotella = createAndAddElement(EntregaConstants.bottleSalon1, roomSalon1,null);
        itemVaso1 = createAndAddElement(EntregaConstants.glass1Salon1, roomSalon1,null);
        itemVaso2 = createAndAddElement(EntregaConstants.glass2Salon1, roomSalon1,null);
        createAndAddElement(EntregaConstants.chair1Salon1, roomSalon1,null);
        createAndAddElement(EntregaConstants.chair2Salon1, roomSalon1,null);
        createAndAddElement(EntregaConstants.cuadroTrenSalon1, roomSalon1,null);

        itemCuadroBarco = createAndAddElement(EntregaConstants.cuadroBarcoSalon1, roomSalon1,null);
        itemCajaFuerte = createAndAddElement(EntregaConstants.cajaFuerteSalon1, itemCuadroBarco,null);
        itemCredencial = createAndAddElement(EntregaConstants.credencialSalon1, itemCajaFuerte,null);
    }

    private void createItemsSalon2() {
        itemMartillo = createAndAddElement(EntregaConstants.martilloSalon2, roomSalon2,null);
        itemDestornillador1 = createAndAddElement(EntregaConstants.destornillador1Salon2, roomSalon2,null);
        itemDestornillador2 = createAndAddElement(EntregaConstants.destornillador2Salon2, roomSalon2,null);
    }

    private void createItemsSalon3() {
        itemLlave = createAndAddElement(EntregaConstants.keySalon3, roomSalon3,null);
    }

    private void createItemsAccesoBiblioteca() {
        itemBibliotecario = createAndAddElement(EntregaConstants.bibliotecario, roomAccesoBiblioteca,null);
    }

    private void createItemsBiblioteca() {
        itemLibroViejo = createAndAddElement(EntregaConstants.libroViejo, roomBiblioteca,null);
        createAndAddElement(EntregaConstants.estante, roomBiblioteca,null);
        itemLibro1 = createAndAddElement(EntregaConstants.libro1, roomBiblioteca,null);
        itemLibro2 = createAndAddElement(EntregaConstants.libro2, roomBiblioteca,null);
        itemLibro3 = createAndAddElement(EntregaConstants.libro3, roomBiblioteca,null);
        itemLibro4 = createAndAddElement(EntregaConstants.libro4, roomBiblioteca,null);
        itemLibro5 = createAndAddElement(EntregaConstants.libro5, roomBiblioteca,null);
        itemLibro6 = createAndAddElement(EntregaConstants.libro6, roomBiblioteca,null);
        itemLibro7 = createAndAddElement(EntregaConstants.libro7, roomBiblioteca,null);
        itemLibro8 = createAndAddElement(EntregaConstants.libro8, roomBiblioteca,null);
        itemLibro9 = createAndAddElement(EntregaConstants.libro9, roomBiblioteca,null);
    }

    private void createItemsSotano() {
        itemBaranda = createAndAddElement(EntregaConstants.baranda, roomSotano, null);
        itemEscalera = createAndAddElement(EntregaConstants.escaleraOxidada, roomSotano, null);
    }

    private void createItemsSubSotano() {
        itemVentana = createAndAddElement(EntregaConstants.ventana, roomSubSotano, null);
        itemEscaleraSubSotano = createAndAddElement(EntregaConstants.escaleraOxidada, roomSubSotano, null);
    }
}
