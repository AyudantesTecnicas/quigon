import Model.actions.*;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.ruleExpressions.expressions.AndExpression;
import Model.ruleExpressions.expressions.RuleExpression;
import Model.ruleExpressions.rules.DoesNotHaveState;
import Model.ruleExpressions.rules.HasContainerRule;
import Model.ruleExpressions.rules.HasStateRule;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by metro on 28/04/16.
 */
public class MoveTests {

    /*@Test
    public void testgsdgsdg() {
        ComplexElement puerta = new ComplexElement("Puerta");

        ComplexElement personaje = new ComplexElement("Personaje");

        Element cerrada = new Element("Cerrada");

        Move abrir = new Move("Abrir");
        abrir.setResultMessage("Puerta abierta");

        puerta.addMove(abrir);
        puerta.setContainerElement(new ComplexElement("Room 1"));
        puerta.addState(cerrada);

        personaje.setContainerElement(new ComplexElement("Room 1"));

        Action abrirPuerta = new AddStatesAction();
        abrirPuerta.setElementToUpdate(puerta);
        abrirPuerta.addItemToUpdate(new Element("Abierta"));

        Action quitarCerrada = new RemoveStatesAction();
        quitarCerrada.setElementToUpdate(puerta);
        quitarCerrada.addItemToUpdate(new Element("Cerrada"));

        Action cambiarRoom = new ChangeContainerAction();
        cambiarRoom.setElementToUpdate(personaje);
        cambiarRoom.addItemToUpdate(new Element("Room 2"));

        HasStateRule rules = new HasStateRule();
        rules.setFailMessage("No estas cerrada");
        rules.setStateToValidate(new Element("Cerrada"));
        rules.setElementToValidate(puerta);

        DoesNotHaveState noAbierta = new DoesNotHaveState();
        noAbierta.setFailMessage("Estas abierta");
        noAbierta.setStateToValidate(new Element("Abierta"));
        noAbierta.setElementToValidate(puerta);


        AndExpression rule = new AndExpression();
        rule.setLeftExpression(rules);
        rule.setRightExpression(noAbierta);

        abrir.setRules(rule);
        abrir.addAction(abrirPuerta);
        abrir.addAction(cambiarRoom);
        abrir.addAction(quitarCerrada);

        puerta.execute("Abrir");

        assertTrue(personaje.getContainerElement().equals(new Element("Room 2")));

    }*/

    @Test
    public void testRules() {

        ComplexElement key = new ComplexElement("Vella");
        Element poisoned = new Element("hecho mierda");

        ComplexElement character = new ComplexElement("Puto");
        character.addState(poisoned);

        key.setContainerElement(character);

        HasStateRule isPoisoned = new HasStateRule();
        isPoisoned.setFailMessage("No estas hecho mierda payaso");
        isPoisoned.setElementToValidate(character);
        isPoisoned.setStateToValidate(poisoned);

        HasContainerRule hasKey = new HasContainerRule();
        hasKey.setFailMessage("No tenes la llave hijo de mil puta");
        hasKey.setElementToValidate(key);
        hasKey.setContainerToValidate(character);

        AndExpression andExpression = new AndExpression();
        andExpression.setLeftExpression(isPoisoned);
        andExpression.setRightExpression(hasKey);

        assertTrue(andExpression.interpret());

    }

}
