package games;

import creation.GameBuilder;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.Action;
import model.actions.Move;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.DoesNotHaveContainerRule;
import model.rulesexpressions.rules.HasContainerRule;


@SuppressWarnings("CPD-START")
public class WolfSheep extends GameBuilder {

    public static final String gameDescription = "There is a wolf, a sheep and a cabbage... For what?";

    public WolfSheep() {
        gameName = "WolfSheep";

    }

    @SuppressWarnings("CPD-START")
    public void setElements() {

        //Create elements
        ComplexElement northShore = createAndAddElement("north-shore", null, null);
        ComplexElement southShore = createAndAddElement("south-shore", null, null);
        ComplexElement sheep = createAndAddElement("sheep", southShore, null);
        ComplexElement wolf = createAndAddElement("wolf", southShore, null);
        ComplexElement cabbage = createAndAddElement("cabbage", southShore, null);
        ComplexElement boat = createAndAddElement("boat", southShore, null);
        game.character = createAndAddElement("character", boat, null);

        //Create action consequences
        Action crossSouthNorth = buildChangeContainerAction(boat, northShore);
        Action crossNorthSouth = buildChangeContainerAction(boat, southShore);
        Action leaveSheepSouth = buildChangeContainerAction(sheep, southShore);
        Action leaveWolfSouth = buildChangeContainerAction(wolf, southShore);
        Action leaveCabbageSouth = buildChangeContainerAction(cabbage, southShore);
        Action leaveSheepNorth = buildChangeContainerAction(sheep, northShore);
        Action leaveWolfNorth = buildChangeContainerAction(wolf, northShore);
        Action leaveCabbageNorth = buildChangeContainerAction(cabbage, northShore);
        Action takeSheep = buildChangeContainerAction(sheep, boat);
        Action takeWolf = buildChangeContainerAction(wolf, boat);
        Action takeCabbage = buildChangeContainerAction(cabbage, boat);

        //Create rules
        HasContainerRule boatHasSheep = checkContainerRule(sheep, boat, "Sheep is not on board");
        DoesNotHaveContainerRule boatHasNoSheep = doesntHaveContainerRule(sheep, boat, "Sheep is on board");
        HasContainerRule boatHasWolf = checkContainerRule(wolf, boat, "Wolf is not on board");
        DoesNotHaveContainerRule boatHasNoWolf = doesntHaveContainerRule(wolf, boat, "Wolf is on board");
        HasContainerRule boatHasCabbage = checkContainerRule(cabbage, boat, "Cabbage is not on board");
        DoesNotHaveContainerRule boatHasNoCabbage = doesntHaveContainerRule(cabbage, boat, "Cabbage is on board");
        DoesNotHaveContainerRule southShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, southShore, "Wolf is is on the south-shore");
        HasContainerRule northShoreContainsWolf = checkContainerRule(wolf, northShore, "Wolf is is not on the north-shore");
        DoesNotHaveContainerRule southShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, southShore, "Sheep is on the south-shore");
        HasContainerRule northShoreContainsSheep = checkContainerRule(sheep, northShore, "Sheep is is not on the north-shore");
        DoesNotHaveContainerRule southShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, southShore, "Cabbage is on the south-shore");
        HasContainerRule northShoreContainsCabbage = checkContainerRule(cabbage, northShore, "Cabbage is is not on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, northShore, "Wolf is on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, northShore, "Sheep is on the north-shore");
        DoesNotHaveContainerRule northShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, northShore, "Cabbage is on the north-shore");

        //Rule to cross north shore
        LogicBuilder logicBuilder = new LogicBuilder();

        IExpression rulesToCrossNorthShore = null;
        try {
            rulesToCrossNorthShore = logicBuilder.build(southShoreDoesntContainsWolf,
                    southShoreDoesntContainsCabbage, '&');
            rulesToCrossNorthShore = logicBuilder.build(rulesToCrossNorthShore,
                    southShoreDoesntContainsSheep, '|');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Rule of rules to cross south shore
        IExpression rulesToCrossSouthShore = null;
        try {
            rulesToCrossSouthShore = logicBuilder.build(northShoreDoesntContainsWolf,
                    northShoreDoesntContainsCabbage, '&');
            rulesToCrossSouthShore = logicBuilder.build(rulesToCrossSouthShore,
                    northShoreDoesntContainsSheep, '|');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Rule to take
        IExpression ruleToTake = null;
        try {
            ruleToTake = logicBuilder.build(boatHasNoWolf, boatHasNoSheep, '&');
            ruleToTake = logicBuilder.build(ruleToTake, boatHasNoCabbage, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }

        //Victory Condition
        IExpression victoryRule = null;
        try {
            victoryRule = logicBuilder.build(northShoreContainsWolf, northShoreContainsSheep, '&');
            victoryRule = logicBuilder.build(victoryRule, northShoreContainsCabbage, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
        game.setVictoryCondition(victoryRule);

        //Create moves
        Move crossSouthShore = moveWithActionsAndRules("cross", crossNorthSouth, rulesToCrossSouthShore, "you have crossed!");
        Move crossNorthShore = moveWithActionsAndRules("cross",crossSouthNorth, rulesToCrossNorthShore, "you have crossed!");
        Move takeSheepAboard = moveWithActionsAndRules("take", takeSheep, ruleToTake, "Ok");
        Move takeWolfAboard = moveWithActionsAndRules("take", takeWolf, ruleToTake, "Ok");
        Move takeCabbageAboard = moveWithActionsAndRules("take", takeCabbage, ruleToTake, "Ok");

        Move leaveSheep = moveWithActionsAndRules("leave",leaveSheepSouth,boatHasSheep,"Ok");
        Move leaveWolf = moveWithActionsAndRules("leave",leaveWolfSouth,boatHasWolf,"Ok");
        Move leaveCabbage = moveWithActionsAndRules("leave",leaveCabbageSouth,boatHasCabbage,"Ok");

        leaveSheep.addAction(leaveSheepNorth);
        leaveWolf.addAction(leaveWolfNorth);
        leaveCabbage.addAction(leaveCabbageNorth);

        HasContainerRule boatIsOnSouthShore = checkContainerRule(boat,southShore,"Boat is not on the south-shore");
        HasContainerRule boatIsOnNorthShore = checkContainerRule(boat,northShore,"Boat is not on the north-shore");

        leaveSheepSouth.setRules(boatIsOnSouthShore);
        leaveSheepNorth.setRules(boatIsOnNorthShore);
        leaveCabbageSouth.setRules(boatIsOnSouthShore);
        leaveCabbageNorth.setRules(boatIsOnNorthShore);
        leaveWolfSouth.setRules(boatIsOnSouthShore);
        leaveWolfNorth.setRules(boatIsOnNorthShore);

        //Add moves to Elements
        wolf.addMove(leaveWolf);
        wolf.addMove(takeWolfAboard);

        sheep.addMove(leaveSheep);
        sheep.addMove(takeSheepAboard);

        cabbage.addMove(leaveCabbage);
        cabbage.addMove(takeCabbageAboard);

        northShore.addMove(crossNorthShore);
        southShore.addMove(crossSouthShore);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "cross");
        createAndAddSuportedAction(1, "take");
        createAndAddSuportedAction(1, "leave");
    }

}
