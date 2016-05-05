package gameFiles;

import gameCreation.GameBuilder;
import Model.actions.Action;
import Model.actions.ChangeContainerAction;
import Model.actions.Move;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.HasContainerRule;
import Model.rules.IExpression;
import Model.rules.RuleExpression;
import logicFactory.ProxyLogicBuilder;
import logicFactory.WrongLogicException;

import java.util.HashMap;

public class WolfSheep extends GameBuilder {

    public static String gameDescription= "There is a wolf, a sheep and a cabbage... For what?";
    public WolfSheep() {
        gameName = "WolfSheep";

    }
    public void setElements(){
        ComplexElement character = new ComplexElement();
        game.character=character;

        //Create elements
        ComplexElement northshore= new ComplexElement("north-shore");
        ComplexElement southshore= new ComplexElement("south-shore");
        ComplexElement sheep= new ComplexElement("sheep");
        ComplexElement wolf= new ComplexElement("wolf");
        ComplexElement cabbage = new ComplexElement("cabbage");
        ComplexElement boat = new ComplexElement("boat");

        //Add elements to game
        addElement(northshore);
        addElement(southshore);
        addElement(sheep);
        addElement(wolf);
        addElement(cabbage);

        //Set containers for each element
        sheep.setContainerElement(southshore);
        wolf.setContainerElement(southshore);
        cabbage.setContainerElement(southshore);
        boat.setContainerElement(southshore);
        character.setContainerElement(boat);

        //Create states
        Element empty = new Element("empty");
        Element full = new Element("full");

        //Add state to elements
        boat.addState(empty);

        //Create moves
        Move crossSouthShore = new Move("cross");
        Move crossNorthShore = new Move("cross");
        Move takeSheepAboard = new Move("take");
        Move takeWolfAboard = new Move("take");
        Move takeCabbageAboard = new Move("take");
        Move leaveSheepSouthShore = new Move("leave");
        Move leaveWolfSouthShore = new Move("leave");
        Move leaveCabbageSouthShore = new Move("leave");
        Move leaveSheepNorthShore = new Move("leave");
        Move leaveWolfNorthShore = new Move("leave");
        Move leaveCabbageNorthShore = new Move("leave");

        //Create action consequences
        Action crossSouthNorth = new ChangeContainerAction();
        crossSouthNorth.setElementToUpdate(boat);
        crossSouthNorth.addItemToUpdate(northshore);

        Action crossNorthSouth = new ChangeContainerAction();
        crossNorthSouth.setElementToUpdate(boat);
        crossNorthSouth.addItemToUpdate(southshore);

        Action leaveSheepSouth = new ChangeContainerAction();
        leaveSheepSouth.setElementToUpdate(sheep);
        leaveSheepSouth.addItemToUpdate(southshore);

        Action leaveWolfSouth = new ChangeContainerAction();
        leaveWolfSouth.setElementToUpdate(wolf);
        leaveWolfSouth.addItemToUpdate(southshore);

        Action leaveCabbageSouth = new ChangeContainerAction();
        leaveCabbageSouth.setElementToUpdate(cabbage);
        leaveCabbageSouth.addItemToUpdate(southshore);

        Action leaveSheepNorth = new ChangeContainerAction();
        leaveSheepNorth.setElementToUpdate(sheep);
        leaveSheepNorth.addItemToUpdate(northshore);

        Action leaveWolfNorth = new ChangeContainerAction();
        leaveWolfNorth.setElementToUpdate(wolf);
        leaveWolfNorth.addItemToUpdate(northshore);

        Action leaveCabbageNorth = new ChangeContainerAction();
        leaveCabbageNorth.setElementToUpdate(cabbage);
        leaveCabbageNorth.addItemToUpdate(northshore);

        Action takeSheep = new ChangeContainerAction();
        takeSheep.setElementToUpdate(sheep);
        takeSheep.addItemToUpdate(boat);

        Action takeWolf = new ChangeContainerAction();
        takeWolf.setElementToUpdate(wolf);
        takeWolf.addItemToUpdate(boat);

        Action takeCabbage = new ChangeContainerAction();
        takeCabbage.setElementToUpdate(cabbage);
        takeCabbage.addItemToUpdate(boat);

        //Create rules
        HasContainerRule boatHasSheep = new HasContainerRule();
        boatHasSheep.setElementToValidate(sheep);
        boatHasSheep.setElementOfElementToValidate(boat);

        HasContainerRule boatHasWolf = new HasContainerRule();
        boatHasWolf.setElementToValidate(wolf);
        boatHasWolf.setElementOfElementToValidate(boat);

        HasContainerRule boatHasCabbage = new HasContainerRule();
        boatHasCabbage.setElementToValidate(cabbage);
        boatHasCabbage.setElementOfElementToValidate(boat);

        HasContainerRule southShoreContainsWolf = new HasContainerRule();
        southShoreContainsWolf.setElementToValidate(wolf);
        southShoreContainsWolf.setElementOfElementToValidate(southshore);

        HasContainerRule northShoreContainsWolf = new HasContainerRule();
        northShoreContainsWolf.setElementToValidate(wolf);
        northShoreContainsWolf.setElementOfElementToValidate(northshore);

        HasContainerRule southShoreContainsSheep = new HasContainerRule();
        southShoreContainsSheep.setElementToValidate(sheep);
        southShoreContainsSheep.setElementOfElementToValidate(southshore);

        HasContainerRule northShoreContainsSheep = new HasContainerRule();
        northShoreContainsSheep.setElementToValidate(sheep);
        northShoreContainsSheep.setElementOfElementToValidate(northshore);

        HasContainerRule southShoreContainsCabbage = new HasContainerRule();
        southShoreContainsCabbage.setElementToValidate(cabbage);
        southShoreContainsCabbage.setElementOfElementToValidate(southshore);

        HasContainerRule northShoreContainsCabbage = new HasContainerRule();
        northShoreContainsCabbage.setElementToValidate(cabbage);
        northShoreContainsCabbage.setElementOfElementToValidate(northshore);

        //set messages
        crossSouthShore.setResultMessage("you have crossed!");
        crossNorthShore.setResultMessage("you have crossed!");

        takeSheepAboard.setResultMessage("Ok");
        takeWolfAboard.setResultMessage("Ok");
        takeCabbageAboard.setResultMessage("Ok");

        leaveCabbageNorthShore.setResultMessage("Ok");
        leaveWolfNorthShore.setResultMessage("Ok");
        leaveWolfNorthShore.setResultMessage("Ok");

        leaveWolfSouthShore.setResultMessage("Ok");
        leaveCabbageSouthShore.setResultMessage("Ok");
        leaveSheepSouthShore.setResultMessage("Ok");

        boatHasSheep.setFailMessage("the sheep is not on board");
        boatHasWolf.setFailMessage("the wolf is not on board");
        boatHasCabbage.setFailMessage("the cabbage is not on board");
        southShoreContainsWolf.setFailMessage("the wolf is on the south-shore");
        southShoreContainsSheep.setFailMessage("the sheep is on the south-shore");
        southShoreContainsCabbage.setFailMessage("the cabbage is on the south-shore");

        northShoreContainsWolf.setFailMessage("the wolf is on the north-shore");
        northShoreContainsSheep.setFailMessage("the sheep is on the north-shore");
        northShoreContainsCabbage.setFailMessage("the cabbage is on the north-shore");

        //Rule to cross north shore
        HashMap<Character, RuleExpression> rules = new HashMap<>();
        rules.put('a', southShoreContainsWolf);
        rules.put('b', southShoreContainsSheep);
        rules.put('c', southShoreContainsCabbage);

        rules.put('d', northShoreContainsWolf);
        rules.put('e', northShoreContainsSheep);
        rules.put('f', northShoreContainsCabbage);

        rules.put('g', boatHasWolf);
        rules.put('h', boatHasSheep);
        rules.put('i', boatHasCabbage);

        String logicToNorth = "!((a)&(b))&!((b)&(c))";

        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        IExpression rulesToCrossNorthShore;
        try {
            rulesToCrossNorthShore = logicBuilder.parse(rules, logicToNorth);
            crossNorthShore.setRules(rulesToCrossNorthShore);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }

        //Rule of rules to cross south shore
        String logicToSouth = "!((d)&(e))&!((e)&(f))";

        IExpression rulesToCrossSouthShore;
        try {
            rulesToCrossSouthShore = logicBuilder.parse(rules, logicToSouth);
            crossSouthShore.setRules(rulesToCrossSouthShore);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }

        //Rule to take
        String takeLogic = "!(g)&!(h)&!(i)";
        IExpression ruleToTake;
        try {
            ruleToTake = logicBuilder.parse(rules, takeLogic);
            takeSheepAboard.setRules(ruleToTake);
            takeWolfAboard.setRules(ruleToTake);
            takeCabbageAboard.setRules(ruleToTake);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }

        //Victory Condition
        String victoryLogic = "(f)&(e)&(f)";
        IExpression victoryRule = null;
        try {
            victoryRule = logicBuilder.parse(rules, victoryLogic);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage+".\n");
        }
        game.setVictoryCondition(victoryRule);

        /* Add actions to Moves */
        crossNorthShore.addAction(crossSouthNorth);
        crossSouthShore.addAction(crossNorthSouth);

        takeWolfAboard.addAction(takeWolf);
        takeSheepAboard.addAction(takeSheep);
        takeCabbageAboard.addAction(takeCabbage);

        leaveWolfSouthShore.addAction(leaveWolfSouth);
        leaveSheepSouthShore.addAction(leaveSheepSouth);
        leaveCabbageSouthShore.addAction(leaveCabbageSouth);

        leaveWolfNorthShore.addAction(leaveWolfNorth);
        leaveSheepNorthShore.addAction(leaveSheepNorth);
        leaveCabbageNorthShore.addAction(leaveCabbageNorth);

        wolf.addMove(leaveWolfNorthShore);
        wolf.addMove(leaveWolfSouthShore);
        wolf.addMove(takeWolfAboard);

        sheep.addMove(leaveSheepNorthShore);
        sheep.addMove(leaveSheepSouthShore);
        sheep.addMove(takeSheepAboard);

        cabbage.addMove(leaveCabbageNorthShore);
        cabbage.addMove(leaveCabbageSouthShore);
        cabbage.addMove(takeCabbageAboard);

        northshore.addMove(crossNorthShore);
        southshore.addMove(crossSouthShore);

    }
    public void setActions(){}

}
