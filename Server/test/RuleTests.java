import Model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 24/04/16.
 */
public class RuleTests {

    @Test
    public void testRuleWithNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anItem = new Item("Un item");
        anItem.addState(aState);

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNeeded(aState);

        assertTrue(aRule.doesTheRuleMet());
    }

    @Test
    public void testRuleWithNotNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anItem = new Item("Un item");

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNotNeeded(aState);

        assertTrue(aRule.doesTheRuleMet());
    }

    @Test
    public void testRuleWithNotNeededStateAndNeededStateMet() {
        State aState = new State();
        aState.setName("Un estado");

        Item anotherItem = new Item("Otro item");

        PossessionState anotherState = new PossessionState();
        anotherState.setName("Otro estado");
        anotherState.setItem(anotherItem);

        State otherStateMore = new State();
        otherStateMore.setName("Otro estado mas");

        Item anItem = new Item("Un item");
        anItem.addState(anotherState);
        anItem.addState(otherStateMore);

        Rule aRule = new Rule();
        aRule.setItemToValidate(anItem);
        aRule.setStateNotNeeded(aState);
        aRule.setStateNeeded(anotherState);

        assertTrue(aRule.doesTheRuleMet());
    }

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


        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(estadoNoEnvenenado);
        rules.add(tieneLlave);

        Action open = new Action("abrir", rules);

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

        ArrayList<Rule> rules = new ArrayList<Rule>();
        rules.add(barcoEstaVacio);

        ArrayList<State> remove = new ArrayList<State>();
        remove.add(vacio);

        ArrayList<State> add = new ArrayList<State>();
        add.add(lleno);

        HashMap<String, ArrayList<State>> map = new HashMap<>();
        map.put("add", add);
        map.put("remove", remove);

        Action agarrar = new Action("agarrar", rules, item, map);

        assertTrue(agarrar.execute());
        assertFalse(agarrar.execute());
    }
}
