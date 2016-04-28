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

public final class OpenDoor2 extends GameBuilder {

    public static String gameDescription = "There is a door on this game. But no key around.";
    public OpenDoor2() {
        gameName = "OpenDoor2";
    }

    public void setElements(){
        ComplexElement character = new ComplexElement();
        game.character=character;

        //Create elements
        Element Room1= new Element("Room1");
        Element Room2= new Element("Room2");
        ComplexElement Door= new ComplexElement("Door");
        ComplexElement Box = new ComplexElement("Box");
        ComplexElement Key= new ComplexElement("Key");

        //Add elements to Game
        elementsList.add(Key);
        elementsList.add(Box);
        elementsList.add(Room1);
        elementsList.add(Room2);
        elementsList.add(Door);

        //Set Containers for each element
        character.setContainerElement(Room1);
        Box.setContainerElement(Room1);
        Key.setContainerElement(Box);
        Door.setContainerElement(Room1);

        //crear Estados
        Element estadoCajaCerrada = new Element("cerrada");
        Element estadoPuertaCerrada = new Element("cerrada");
        Element estadoPuertaAbierta= new Element("abierta");
        Element estadoCajaAbierta= new Element("abierta");

        //Setear estados iniciales
        Box.addState(estadoCajaCerrada);
        Door.addState(estadoPuertaCerrada);

        //Crear Moves (son las supported Actions del juego)
        Move abrirCaja= new Move("abrir");
        Move pickKey= new Move("take");
        Move abrirPuerta= new Move("abrir");

        //Crear reglas para movimientos
        HasContainerRule victoryRule= new HasContainerRule();
        HasStateRule reglaCajaCerrada= new HasStateRule();
        HasContainerRule reglaSiContiene = new HasContainerRule();
        HasStateRule reglaPuertaCerrada= new HasStateRule();
        HasContainerRule reglaPuertaLlaveEnPosesion = new HasContainerRule();

        //Setear elementos a las reglas
        reglaCajaCerrada.setElementToValidate(Box);
        reglaCajaCerrada.setElementOfElementToValidate(estadoCajaCerrada);
        reglaSiContiene.setElementToValidate(Key);
        reglaPuertaLlaveEnPosesion.setElementToValidate(Key);
        reglaPuertaLlaveEnPosesion.setElementOfElementToValidate(character);
        victoryRule.setElementToValidate(character);
        reglaSiContiene.setElementOfElementToValidate(Room1);
        victoryRule.setElementOfElementToValidate(Room2);
        reglaPuertaCerrada.setElementOfElementToValidate(estadoPuertaCerrada);
        reglaPuertaCerrada.setElementToValidate(Door);

        //Setear mensajes reglas
        reglaCajaCerrada.setFailMessage("la caja estaba abierta");
        reglaPuertaCerrada.setFailMessage("la puerta estaba abierta");
        abrirCaja.setResultMessage("Abriste una caja, sos groso!!");
        abrirPuerta.setResultMessage("Abriste una puerta, sos groso!!");

        //Inyectar reglas a Moves
        abrirCaja.setRules(reglaCajaCerrada);
        pickKey.setRules(reglaSiContiene);
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
        accionAgregarEstadoAbiertaACaja.setElementToUpdate(Box);

        accionRemoverEstadoCerradoACaja.addItemToUpdate(estadoCajaCerrada);
        accionRemoverEstadoCerradoACaja.setElementToUpdate(Box);

        accionAgregarLLaveARoom1.addItemToUpdate(Room1);
        accionAgregarLLaveARoom1.setElementToUpdate(Key);

        accionAgregarLLaveAJugador.addItemToUpdate(character);
        accionAgregarLLaveAJugador.setElementToUpdate(Key);

        accionAgregarEstadoAbiertaAPuerta.addItemToUpdate(estadoPuertaAbierta);
        accionAgregarEstadoAbiertaAPuerta.setElementToUpdate(Door);

        accionRemoverEstadoCerradoAPuerta.addItemToUpdate(estadoPuertaCerrada);
        accionRemoverEstadoCerradoAPuerta.setElementToUpdate(Door);

        accionMoverAlJugadoALaOtraHabitacion.addItemToUpdate(Room2);
        accionMoverAlJugadoALaOtraHabitacion.setElementToUpdate(character);

        //Inyectar Acciones a Moves
        abrirCaja.addAction(accionAgregarEstadoAbiertaACaja);
        abrirCaja.addAction(accionRemoverEstadoCerradoACaja);
        pickKey.addAction(accionAgregarLLaveAJugador);
        abrirCaja.addAction(accionAgregarLLaveARoom1);
        abrirPuerta.addAction(accionAgregarEstadoAbiertaAPuerta);
        abrirPuerta.addAction(accionRemoverEstadoCerradoAPuerta);
        abrirPuerta.addAction(accionMoverAlJugadoALaOtraHabitacion);


        //Regla para abrir la puerta.
        HashMap <Character, RuleExpression> rules = new HashMap<>();
        rules.put('a', reglaPuertaCerrada);
        rules.put('b', reglaPuertaLlaveEnPosesion);
        String logic = "(a)&(b)";

        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        IExpression condicionesParaAbrir;
        try {
            condicionesParaAbrir = logicBuilder.parse(rules, logic);
            abrirPuerta.setRules(condicionesParaAbrir);
        } catch (WrongLogicException e) {
            System.out.print("La logica esta mal expresada.\n");
        }

        //Inyectar Moves a Elements
        Key.addMove(pickKey);
        Door.addMove(abrirPuerta);
        Box.addMove(abrirCaja);

        game.setVictoryCondition(victoryRule);
    }

    public void setActions(){
        actionsList.add(new SupportedAction(1,"abrir"));
        actionsList.add(new SupportedAction(1,"agarrar"));
    }

}
