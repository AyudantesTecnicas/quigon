import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class ActionTests {

    @Test
    public void testRuleToOpenDoor() {
        Item item = new Item("personaje");
        Item llave = new Item("llave");


        State poissoned = new State();
        poissoned.setName("envenendao");

        PossessionState withKey = new PossessionState();
        withKey.setName("Tiene llave");
        withKey.setItem(llave);

        Rule estadoNoEnvenenado = new Rule();
        estadoNoEnvenenado.setName("estado no envenenado");
        estadoNoEnvenenado.setStateNotNeeded(poissoned);
        estadoNoEnvenenado.setItemToValidate(item);

        Rule tieneLlave = new Rule();
        tieneLlave.setName("tiene llave");
        tieneLlave.setStateNeeded(withKey);
        tieneLlave.setItemToValidate(item);

        LogicExpression andExpression = new AndExpression();
        andExpression.setLeftExpression(estadoNoEnvenenado);
        andExpression.setRightExpression(tieneLlave);

        Action open = new Action("abrir", andExpression);

        assertFalse(open.execute());
        item.addState(poissoned);
        assertFalse(open.execute());
        item.addState(withKey);
        assertFalse(open.execute());
        item.removeState(poissoned);
        assertTrue(open.execute());
    }

    @Test
    public void testRuleTakeSheepAndWolf() {
        State vacio = new State();
        vacio.setName("vacio");

        State lleno = new State();
        lleno.setName("lleno");

        Item item = new Item("barco");
        item.addState(vacio);

        Rule barcoEstaVacio = new Rule();
        barcoEstaVacio.setName("barco esta vacio");
        barcoEstaVacio.setStateNeeded(vacio);
        barcoEstaVacio.setItemToValidate(item);


        ArrayList<State> remove = new ArrayList<State>();
        remove.add(vacio);

        ArrayList<State> add = new ArrayList<State>();
        add.add(lleno);

        HashMap<String, ArrayList<State>> map = new HashMap<>();
        map.put("add", add);
        map.put("remove", remove);

        Action agarrar = new Action("agarrar", barcoEstaVacio, item, map);

        assertTrue(agarrar.execute());
        assertFalse(agarrar.execute());
    }

}
