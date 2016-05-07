package games;

import creation.GameBuilder;
import logic.ProxyLogicBuilder;
import logic.WrongLogicException;
import model.actions.Action;
import model.actions.ChangeContainerAction;
import model.actions.Move;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rules.DoesNotHaveContainerRule;
import model.rules.HasContainerRule;
import model.rules.IExpression;
import model.rules.RuleExpression;
import parser.SupportedAction;

import java.util.HashMap;
@SuppressWarnings("CPD-START")
public class WolfSheep extends GameBuilder {

    public static String gameDescription = "There is a wolf, a sheep and a cabbage... For what?";

    public WolfSheep() {
        gameName = "WolfSheep";

    }
    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create states
        //Element empty = new Element("empty");
        //Element full = new Element("full");

        //Create elements
        ComplexElement northShore = createAndAddElement("north-shore",null,null);
        ComplexElement southShore = createAndAddElement("south-shore",null,null);
        ComplexElement sheep = createAndAddElement("sheep",southShore,null);
        ComplexElement wolf = createAndAddElement("wolf",southShore,null);
        ComplexElement cabbage = createAndAddElement("cabbage",southShore,null);
        ComplexElement boat = createAndAddElement("boat",southShore,null);
        game.character = createAndAddElement("character",boat,null);

        //Add state to elements
        //boat.addState(empty);


        //Create action consequences
        Action crossSouthNorth = buildChangeContainerAction(boat,northShore);
        Action crossNorthSouth = buildChangeContainerAction(boat,southShore);
        Action leaveSheepSouth = buildChangeContainerAction(sheep,southShore);
        Action leaveWolfSouth = buildChangeContainerAction(wolf,southShore);
        Action leaveCabbageSouth = buildChangeContainerAction(cabbage,southShore);
        Action leaveSheepNorth = buildChangeContainerAction(sheep,northShore);
        Action leaveWolfNorth = buildChangeContainerAction(wolf,northShore);
        Action leaveCabbageNorth = buildChangeContainerAction(cabbage,northShore);
        Action takeSheep = buildChangeContainerAction(sheep,boat);
        Action takeWolf = buildChangeContainerAction(wolf,boat);
        Action takeCabbage = buildChangeContainerAction(cabbage,boat);

        //Create rules
        HasContainerRule boatHasSheep = checkContainerRule(sheep,boat,"Sheep is not on board");
        DoesNotHaveContainerRule boatHasNoSheep = checkDoesntHaveContainerRule(sheep,boat,"Sheep is on board");
        HasContainerRule boatHasWolf = checkContainerRule(wolf,boat,"Wolf is not on board");
        DoesNotHaveContainerRule boatHasNoWolf = checkDoesntHaveContainerRule(wolf,boat,"Wolf is on board");
        HasContainerRule boatHasCabbage = checkContainerRule(cabbage,boat,"Cabbage is not on board");
        DoesNotHaveContainerRule boatHasNoCabbage = checkDoesntHaveContainerRule(cabbage,boat,"Cabbage is on board");
        DoesNotHaveContainerRule southShoreDoesntContainsWolf = checkDoesntHaveContainerRule(wolf,southShore,"Wolf is is on the south-shore");
        HasContainerRule northShoreContainsWolf = checkContainerRule(wolf,northShore,"Wolf is is not on the north-shore");
        DoesNotHaveContainerRule southShoreDoesntContainsSheep = checkDoesntHaveContainerRule(sheep,southShore,"Sheep is on the south-shore");
        HasContainerRule northShoreContainsSheep = checkContainerRule(sheep,northShore,"Sheep is is not on the north-shore");
        DoesNotHaveContainerRule southShoreDoesntContainsCabbage = checkDoesntHaveContainerRule(cabbage,southShore,"Cabbage is on the south-shore");
        HasContainerRule northShoreContainsCabbage = checkContainerRule(cabbage,northShore,"Cabbage is is not on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsWolf = checkDoesntHaveContainerRule(wolf,northShore,"Wolf is on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsSheep = checkDoesntHaveContainerRule(sheep,northShore,"Sheep is on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsCabbage = checkDoesntHaveContainerRule(cabbage,northShore,"Cabbage is on the north-shore");

        //Rule to cross north shore
        HashMap<Character, RuleExpression> rules = new HashMap<>();
        rules.put('a', southShoreDoesntContainsWolf);
        rules.put('b', southShoreDoesntContainsSheep);
        rules.put('c', southShoreDoesntContainsCabbage);

        rules.put('d', northShoreContainsWolf);
        rules.put('e', northShoreContainsSheep);
        rules.put('f', northShoreContainsCabbage);

        rules.put('g', boatHasWolf);
        rules.put('h', boatHasSheep);
        rules.put('i', boatHasCabbage);

        rules.put('j', northShoreDoesntContainsWolf);
        rules.put('k', northShoreDoesntContainsSheep);
        rules.put('l', northShoreDoesntContainsCabbage);

        rules.put('m', boatHasNoWolf);
        rules.put('n', boatHasNoSheep);
        rules.put('o', boatHasNoCabbage);

        String logicToNorth = "(b)|((a)&(c))";

        ProxyLogicBuilder logicBuilder = new ProxyLogicBuilder();
        IExpression rulesToCrossNorthShore = null;
        try {
            rulesToCrossNorthShore = logicBuilder.parse(rules, logicToNorth);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Rule of rules to cross south shore
        String logicToSouth = "(k)|((j)&(l))";

        IExpression rulesToCrossSouthShore = null;
        try {
            rulesToCrossSouthShore = logicBuilder.parse(rules, logicToSouth);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Rule to take
        String takeLogic = "((m)&(n))&(o)";
        IExpression ruleToTake = null;
        try {
            ruleToTake = logicBuilder.parse(rules, takeLogic);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Victory Condition
        String victoryLogic = "((d)&(e))&(f)";
        IExpression victoryRule = null;
        try {
            victoryRule = logicBuilder.parse(rules, victoryLogic);
        } catch (WrongLogicException e) {
            System.out.print(logicMessage + ".\n");
        }
        game.setVictoryCondition(victoryRule);

        //Create moves
        Move crossSouthShore = moveWithActionsAndRules("cross",crossSouthNorth,rulesToCrossSouthShore,"you have crossed!");
        Move crossNorthShore = moveWithActionsAndRules("cross",crossNorthSouth,rulesToCrossNorthShore,"you have crossed!");
        Move takeSheepAboard = moveWithActionsAndRules("take",takeSheep,ruleToTake,"Ok");
        Move takeWolfAboard = moveWithActionsAndRules("take",takeWolf,ruleToTake,"Ok");
        Move takeCabbageAboard = moveWithActionsAndRules("take",takeCabbage,ruleToTake,"Ok");
        /*
        Move leaveSheepSouthShore = moveWithActionsAndRules("leave",leaveSheepSouth,roomHasKey,"Ok");
        Move leaveWolfSouthShore = moveWithActionsAndRules("leave",leaveWolfSouth,roomHasKey,"Ok");
        Move leaveCabbageSouthShore = moveWithActionsAndRules("leave",leaveCabbageSouth,roomHasKey,"Ok");
        Move leaveSheepNorthShore = moveWithActionsAndRules("leave",leaveSheepNorth,roomHasKey,"Ok");
        Move leaveWolfNorthShore = moveWithActionsAndRules("leave",leaveWolfNorth,roomHasKey,"Ok");
        Move leaveCabbageNorthShore = moveWithActionsAndRules("leave",leaveCabbageNorth,roomHasKey,"Ok");

        //Add moves to Elements
        wolf.addMove(leaveWolfNorthShore);
        wolf.addMove(leaveWolfSouthShore);
        wolf.addMove(takeWolfAboard);

        sheep.addMove(leaveSheepNorthShore);
        sheep.addMove(leaveSheepSouthShore);
        sheep.addMove(takeSheepAboard);

        cabbage.addMove(leaveCabbageNorthShore);
        cabbage.addMove(leaveCabbageSouthShore);
        cabbage.addMove(takeCabbageAboard);
*/
        northShore.addMove(crossNorthShore);
        southShore.addMove(crossSouthShore);
    }
    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1,"cross");
        createAndAddSuportedAction(1,"take");
        createAndAddSuportedAction(1,"leave");
    }

}
