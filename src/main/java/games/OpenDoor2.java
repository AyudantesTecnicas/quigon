package games;

import creation.GameBuilder;
import logic.ProxyLogicBuilder;
import logic.WrongLogicException;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rules.HasContainerRule;
import model.rules.HasStateRule;
import model.rules.IExpression;
import model.rules.RuleExpression;
import parser.SupportedAction;

import java.util.HashMap;

public final class OpenDoor2 extends GameBuilder {

    public static String gameDescription = "There is a door on this game. But no key around.";

    public OpenDoor2() {
        gameName = "OpenDoor2";
    }

    public void setElements() {
        ComplexElement character = new ComplexElement();
        game.character = character;

        //Create elements
        ComplexElement room1 = new ComplexElement("room1");
        ComplexElement room2 = new ComplexElement("room2");
        ComplexElement door = new ComplexElement("door");
        ComplexElement box = new ComplexElement("box");
        ComplexElement key = new ComplexElement("key");

        //Add elements to Game
        addElement(key);
        addElement(box);
        addElement(room1);
        addElement(room2);
        addElement(door);

        //Set Containers for each element
        character.setContainerElement(room1);
        box.setContainerElement(room1);
        key.setContainerElement(box);
        door.setContainerElement(room1);

        //crear Estados
        Element estadoCajaCerrada = new Element("Closed");
        Element estadoPuertaCerrada = new Element("Closed");
        Element estadoPuertaAbierta = new Element("Open");
        Element estadoCajaAbierta = new Element("Open");

        //Setear estados iniciales
        box.addState(estadoCajaCerrada);
        door.addState(estadoPuertaCerrada);

        //Crear Moves (son las supported Actions del juego)
        Move abrirCaja = new Move("open");
        Move pickKey = new Move("pick");
        Move abrirPuerta = new Move("open");

        //Crear reglas para movimientos
        HasContainerRule victoryRule = new HasContainerRule();
        HasStateRule reglaCajaCerrada = new HasStateRule();
        HasContainerRule reglaSiContiene = new HasContainerRule();
        HasStateRule reglaPuertaCerrada = new HasStateRule();
        HasContainerRule reglaPuertaLlaveEnPosesion = new HasContainerRule();

        //Setear elementos a las reglas
        reglaCajaCerrada.setElementToValidate(box);
        reglaCajaCerrada.setElementOfElementToValidate(estadoCajaCerrada);
        reglaSiContiene.setElementToValidate(key);
        reglaPuertaLlaveEnPosesion.setElementToValidate(key);
        reglaPuertaLlaveEnPosesion.setElementOfElementToValidate(character);
        victoryRule.setElementToValidate(character);
        reglaSiContiene.setElementOfElementToValidate(room1);
        victoryRule.setElementOfElementToValidate(room2);
        reglaPuertaCerrada.setElementOfElementToValidate(estadoPuertaCerrada);
        reglaPuertaCerrada.setElementToValidate(door);

        //Setear mensajes reglas
        reglaCajaCerrada.setFailMessage("la caja estaba abierta");
        reglaPuertaCerrada.setFailMessage("la puerta estaba abierta");
        abrirCaja.setResultMessage("Abriste una caja, sos groso!!");
        abrirPuerta.setResultMessage("Abriste una puerta, sos groso!!");
        pickKey.setResultMessage("Levantaste la llave");

        //Inyectar reglas a Moves
        abrirCaja.setRules(reglaCajaCerrada);
        pickKey.setRules(reglaSiContiene);
        abrirPuerta.setRules(reglaPuertaCerrada);

        //Crear acciones
        Action accionAgregarEstadoAbiertaACaja = new AddStatesAction();
        Action accionRemoverEstadoCerradoACaja = new RemoveStatesAction();
        Action accionAgregarLLaveARoom1 = new ChangeContainerAction();
        Action accionAgregarLLaveAJugador = new ChangeContainerAction();
        Action accionAgregarEstadoAbiertaAPuerta = new AddStatesAction();
        Action accionRemoverEstadoCerradoAPuerta = new RemoveStatesAction();
        Action accionMoverAlJugadoALaOtraHabitacion = new ChangeContainerAction();

        //Agregar elementos y estados a las acciones
        accionAgregarEstadoAbiertaACaja.addItemToUpdate(estadoCajaAbierta);
        accionAgregarEstadoAbiertaACaja.setElementToUpdate(box);

        accionRemoverEstadoCerradoACaja.addItemToUpdate(estadoCajaCerrada);
        accionRemoverEstadoCerradoACaja.setElementToUpdate(box);

        accionAgregarLLaveARoom1.addItemToUpdate(room1);
        accionAgregarLLaveARoom1.setElementToUpdate(key);

        accionAgregarLLaveAJugador.addItemToUpdate(character);
        accionAgregarLLaveAJugador.setElementToUpdate(key);

        accionAgregarEstadoAbiertaAPuerta.addItemToUpdate(estadoPuertaAbierta);
        accionAgregarEstadoAbiertaAPuerta.setElementToUpdate(door);

        accionRemoverEstadoCerradoAPuerta.addItemToUpdate(estadoPuertaCerrada);
        accionRemoverEstadoCerradoAPuerta.setElementToUpdate(door);

        accionMoverAlJugadoALaOtraHabitacion.addItemToUpdate(room2);
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
        HashMap<Character, RuleExpression> rules = new HashMap<>();
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
        key.addMove(pickKey);
        door.addMove(abrirPuerta);
        box.addMove(abrirCaja);

        game.setVictoryCondition(victoryRule);
    }

    public void setActions() {
        actionsList.add(new SupportedAction(1, "open"));
        actionsList.add(new SupportedAction(1, "pick"));
    }

}
