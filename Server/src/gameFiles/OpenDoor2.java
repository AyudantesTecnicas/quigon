package gameFiles;

import GameParser.SupportedAction;
import Model.actions.*;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.HasContainerRule;
import Model.rules.HasStateRule;
import gameCreation.GameBuilder;

public final class OpenDoor2 extends GameBuilder {

    public static String gameDescription = "There is a door on this game. But no key around.";
    public OpenDoor2() {
        gameName = "OpenDoor2";
    }

    public void setAmountOfRooms(){
        amountOfRooms=2;
    }
    public void setRooms() {
        fillGraph(0,1);
    }

    public void setItems(){
        fillVector(0,2);
        fillVector(1,1);
    }

    public void setElements(){
        ComplexElement character = new ComplexElement();
        game.character=character;
        //Crear elements
        Element Room1= new Element("Room1");
        Element Room2= new Element("Room2");
        ComplexElement puerta= new ComplexElement("puerta");
        ComplexElement caja = new ComplexElement("caja");
        ComplexElement llave= new ComplexElement("llave");

        //Agregar elementos al juego
        elementsList.add(llave);
        elementsList.add(caja);
        elementsList.add(Room1);
        elementsList.add(Room2);
        elementsList.add(puerta);

        //setear contenedores para cada element
        character.setContainerElement(Room1);
        caja.setContainerElement(Room1);
        llave.setContainerElement(caja);
        puerta.setContainerElement(Room1);

        //crear Estados
        Element estadoCajaCerrada = new Element("cerrada");
        Element estadoPuertaCerrada = new Element("cerrada");
        Element estadoPuertaAbierta= new Element("abierta");
        Element estadoCajaAbierta= new Element("abierta");

        //Setear estados iniciales
        caja.addState(estadoCajaCerrada);
        puerta.addState(estadoPuertaCerrada);

        //Crear Moves (son las supported Actions del juego)
        Move abrirCaja= new Move("abrir");
        Move agarrarLlave= new Move("agarrar");
        Move abrirPuerta= new Move("abrir");

        //Crear reglas para movimientos
        HasContainerRule fuckingRule= new HasContainerRule();
        HasStateRule reglaCajaCerrada= new HasStateRule();
        HasContainerRule reglaSiContiene = new HasContainerRule();
        HasStateRule reglaPuertaCerrada= new HasStateRule();
        HasContainerRule reglaPuertaLlaveEnPosesion = new HasContainerRule();

        //Setear elementos a las reglas
        reglaCajaCerrada.setElementToValidate(caja);
        reglaCajaCerrada.setElementOfElementToValidate(estadoCajaCerrada);
        reglaSiContiene.setElementToValidate(llave);
        reglaPuertaLlaveEnPosesion.setElementToValidate(llave);
        reglaPuertaLlaveEnPosesion.setElementOfElementToValidate(character);
        fuckingRule.setElementToValidate(character);
        reglaSiContiene.setElementOfElementToValidate(Room1);
        fuckingRule.setElementOfElementToValidate(Room2);
        reglaPuertaCerrada.setElementOfElementToValidate(estadoPuertaCerrada);
        reglaPuertaCerrada.setElementToValidate(puerta);

        //Setear mensajes reglas
        reglaCajaCerrada.setFailMessage("la caja estaba abierta");
        reglaPuertaCerrada.setFailMessage("la puerta estaba abierta");
        abrirCaja.setResultMessage("Abriste una caja, sos groso!!");
        abrirPuerta.setResultMessage("Abriste una puerta, sos groso!!");

        //Inyectar reglas a Moves
        abrirCaja.setRules(reglaCajaCerrada);
        agarrarLlave.setRules(reglaSiContiene);
        abrirPuerta.setRules(reglaPuertaCerrada);

        //Crear acciones
        Action accionAgregarEstadoAbiertaACaja= new AddStatesAction();
        Action accionRemoverEstadoCerradoACaja= new RemoveStatesAction();
        Action accionAgregarLLaveARoom1= new ChangeContainerAction();
        Action accionAgregarLLaveAJugador= new ChangeContainerAction();
        Action accionAgregarEstadoAbiertaAPuerta= new AddStatesAction();
        Action accionRemoverEstadoCerradoAPuerta= new RemoveStatesAction();
        Action accionMoverAlJugadoALaOtraHabitacion= new ChangeContainerAction();

        //Agregar elementos y estados a las acciones
        accionAgregarEstadoAbiertaACaja.addItemToUpdate(estadoCajaAbierta);
        accionAgregarEstadoAbiertaACaja.setElementToUpdate(caja);

        accionRemoverEstadoCerradoACaja.addItemToUpdate(estadoCajaCerrada);
        accionRemoverEstadoCerradoACaja.setElementToUpdate(caja);

        accionAgregarLLaveARoom1.addItemToUpdate(Room1);
        accionAgregarLLaveARoom1.setElementToUpdate(llave);

        accionAgregarLLaveAJugador.addItemToUpdate(character);
        accionAgregarLLaveAJugador.setElementToUpdate(llave);

        accionAgregarEstadoAbiertaAPuerta.addItemToUpdate(estadoPuertaAbierta);
        accionAgregarEstadoAbiertaAPuerta.setElementToUpdate(puerta);

        accionRemoverEstadoCerradoAPuerta.addItemToUpdate(estadoPuertaCerrada);
        accionRemoverEstadoCerradoAPuerta.setElementToUpdate(puerta);

        accionMoverAlJugadoALaOtraHabitacion.addItemToUpdate(Room2);
        accionMoverAlJugadoALaOtraHabitacion.setElementToUpdate(character);

        //Inyectar Acciones a Moves
        abrirCaja.addAction(accionAgregarEstadoAbiertaACaja);
        abrirCaja.addAction(accionRemoverEstadoCerradoACaja);
        agarrarLlave.addAction(accionAgregarLLaveAJugador);
        abrirCaja.addAction(accionAgregarLLaveARoom1);
        abrirPuerta.addAction(accionAgregarEstadoAbiertaAPuerta);
        abrirPuerta.addAction(accionRemoverEstadoCerradoAPuerta);
        abrirPuerta.addAction(accionMoverAlJugadoALaOtraHabitacion);

        //Inyectar Moves a Elements
        llave.addMove(agarrarLlave);
        puerta.addMove(abrirPuerta);
        caja.addMove(abrirCaja);
    }

    public void setActions(){
        actionsList.add(new SupportedAction(1,"abrir"));
        actionsList.add(new SupportedAction(1,"agarrar"));
    }

}
