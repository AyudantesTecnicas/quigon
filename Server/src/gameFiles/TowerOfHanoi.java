package gameFiles;

import GameParser.SupportedAction;
import Model.actions.Move;
import Model.elements.ComplexElement;
import Model.rules.HasContainerRule;
import Model.rules.IExpression;
import Model.rules.RuleExpression;
import gameCreation.GameBuilder;
import logicFactory.ProxyLogicBuilder;
import logicFactory.WrongLogicException;

import java.util.HashMap;

public final class TowerOfHanoi extends GameBuilder {

    public static String gameDescription = "There are tree sticks with plates on this game.";
    public TowerOfHanoi() {
        gameName = "TowerOfHanoi";
    }

    public void setAmountOfRooms(){
        amountOfRooms=3;
    }

    public void setElements(){

        ComplexElement stack1= new ComplexElement("stack 1");
        ComplexElement stack2= new ComplexElement("stack 2");
        ComplexElement stack3= new ComplexElement("stack 3");

        ComplexElement disc1 = new ComplexElement("disc1");
        ComplexElement disc2 = new ComplexElement("disc2");
        ComplexElement disc3 = new ComplexElement("disc3");

        disc1.setSize(1);
        disc2.setSize(2);
        disc3.setSize(3);

        //Add elements to game
        addElement(stack1);
        addElement(stack2);
        addElement(stack3);
        addElement(disc1);
        addElement(disc2);
        addElement(disc3);

        //Set containers for each element
        disc1.setContainerElement(stack1);
        disc2.setContainerElement(stack1);
        disc3.setContainerElement(stack1);

        //Create moves
        Move moveTopStack1 = new Move("move top stack1");
        Move moveTopStack2 = new Move("move top stack2");
        Move moveTopStack3 = new Move("move top stack3");

        Move check = new Move("check top");
        moveTopStack1.setResultMessage("moved!");
        moveTopStack2.setResultMessage("moved!");
        moveTopStack3.setResultMessage("moved!");

        //Create rules
        HasContainerRule disc1InStack1 = new HasContainerRule();
        disc1InStack1.setElementToValidate(disc1);
        disc1InStack1.setElementOfElementToValidate(stack1);

        HasContainerRule disc2InStack1 = new HasContainerRule();
        disc2InStack1.setElementToValidate(disc2);
        disc2InStack1.setElementOfElementToValidate(stack1);

        HasContainerRule disc3InStack1 = new HasContainerRule();
        disc3InStack1.setElementToValidate(disc3);
        disc3InStack1.setElementOfElementToValidate(stack1);

        HasContainerRule disc1InStack2 = new HasContainerRule();
        disc1InStack2.setElementToValidate(disc1);
        disc1InStack2.setElementOfElementToValidate(stack2);

        HasContainerRule disc2InStack2 = new HasContainerRule();
        disc2InStack2.setElementToValidate(disc2);
        disc2InStack2.setElementOfElementToValidate(stack2);

        HasContainerRule disc3InStack2 = new HasContainerRule();
        disc3InStack2.setElementToValidate(disc3);
        disc3InStack2.setElementOfElementToValidate(stack2);

        HasContainerRule disc1InStack3 = new HasContainerRule();
        disc1InStack3.setElementToValidate(disc1);
        disc1InStack3.setElementOfElementToValidate(stack3);

        HasContainerRule disc2InStack3 = new HasContainerRule();
        disc2InStack3.setElementToValidate(disc2);
        disc2InStack3.setElementOfElementToValidate(stack3);

        HasContainerRule disc3InStack3 = new HasContainerRule();
        disc3InStack3.setElementToValidate(disc3);
        disc3InStack3.setElementOfElementToValidate(stack3);

        //Rule for Victory Condition
        //********************************************************************//
        HashMap<Character, RuleExpression> rules = new HashMap<>();
        rules.put('a', disc1InStack2);
        rules.put('b', disc2InStack2);
        rules.put('c', disc3InStack2);
        rules.put('d', disc1InStack3);
        rules.put('e', disc2InStack3);
        rules.put('f', disc3InStack3);

        String logic = "((a)&(b)&(c))|((d)&(e)&(f))";

        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        IExpression victoryRule = null;
        try {
            victoryRule = logicBuilder.parse(rules, logic);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }
        //********************************************************************//
        game.setVictoryCondition(victoryRule);

        //Create movement rules
        HasContainerRule stack1HasDiscs = new HasContainerRule();
        HasContainerRule stack2HasDiscs = new HasContainerRule();
        HasContainerRule stack3HasDiscs = new HasContainerRule();

        //Rule for stacks having discs
        //********************************************************************//
        HashMap<Character, RuleExpression> rules1 = new HashMap<>();
        HashMap<Character, RuleExpression> rules2 = new HashMap<>();
        HashMap<Character, RuleExpression> rules3 = new HashMap<>();

        rules1.put('a', disc1InStack1);
        rules1.put('b', disc2InStack1);
        rules1.put('c', disc3InStack1);

        rules2.put('a', disc1InStack2);
        rules2.put('b', disc2InStack2);
        rules2.put('c', disc3InStack2);

        rules3.put('a', disc1InStack3);
        rules3.put('b', disc2InStack3);
        rules3.put('c', disc3InStack3);

        String logic2 = "(a)|(b)|(c)";

        IExpression anyDiscInStack1 = null;
        IExpression anyDiscInStack2 = null;
        IExpression anyDiscInStack3 = null;

        try {
            anyDiscInStack1 = logicBuilder.parse(rules1, logic2);
            anyDiscInStack2 = logicBuilder.parse(rules2, logic2);
            anyDiscInStack3 = logicBuilder.parse(rules3, logic2);

        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }
        //********************************************************************//

        stack1HasDiscs= (HasContainerRule)anyDiscInStack1;
        stack2HasDiscs= (HasContainerRule)anyDiscInStack2;
        stack3HasDiscs= (HasContainerRule)anyDiscInStack3;

        //Set message
        stack1HasDiscs.setFailMessage("None disc in stack 1.");
        stack2HasDiscs.setFailMessage("None disc in stack 2.");
        stack3HasDiscs.setFailMessage("None disc in stack 3.");






/*
        HasContainerRule firstStackAbleToMove = new HasContainerRule();

        //Set elements to rules
        firstStackAbleToMove.setElementToValidate(stack1);
        firstStackAbleToMove.setElementOfElementToValidate(stack2);

        firstStackAbleToMove.setFailMessage("You can't make that move");
*/

    }
    public void setActions(){
        actionsList.add(new SupportedAction(1,"check top"));
        actionsList.add(new SupportedAction(1,"move"));
    }
}
