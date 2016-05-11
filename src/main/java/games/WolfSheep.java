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
    private ComplexElement northShore;
    private ComplexElement southShore;
    private ComplexElement sheep;
    private ComplexElement wolf;
    private ComplexElement cabbage;
    private ComplexElement boat;
    private Action crossSouthNorth;
    private Action crossNorthSouth;
    private Action leaveSheepSouth;
    private Action leaveWolfSouth;
    private Action leaveCabbageSouth;
    private Action leaveSheepNorth;
    private Action leaveWolfNorth;
    private Action leaveCabbageNorth;
    private Action takeSheep;
    private Action takeWolf;
    private Action takeCabbage;
    private HasContainerRule boatHasSheep;
    private DoesNotHaveContainerRule boatHasNoSheep;
    private HasContainerRule boatHasWolf;
    private DoesNotHaveContainerRule boatHasNoWolf;
    private HasContainerRule boatHasCabbage;
    private DoesNotHaveContainerRule boatHasNoCabbage;
    private DoesNotHaveContainerRule southShoreDoesntContainsWolf;
    private HasContainerRule northShoreContainsWolf;
    private DoesNotHaveContainerRule southShoreDoesntContainsSheep;
    private HasContainerRule northShoreContainsSheep;
    private DoesNotHaveContainerRule southShoreDoesntContainsCabbage;
    private HasContainerRule northShoreContainsCabbage;
    private DoesNotHaveContainerRule northShoreDoesntContainsWolf;
    private DoesNotHaveContainerRule northShoreDoesntContainsSheep;
    private DoesNotHaveContainerRule northShoreDoesntContainsCabbage;
    private HasContainerRule boatIsOnSouthShore;
    private HasContainerRule boatIsOnNorthShore;
    private IExpression rulesToCrossNorthShore;
    private IExpression rulesToCrossSouthShore;
    private IExpression ruleToTake;
    private IExpression victoryRule;
    private LogicBuilder logicBuilder;
    private Move crossSouthShore;
    private Move crossNorthShore;
    private Move takeSheepAboard;
    private Move takeWolfAboard;
    private Move takeCabbageAboard;
    private Move leaveSheep;
    private Move leaveWolf;
    private Move leaveCabbage;

    public WolfSheep() {
        gameName = "WolfSheep";
    }

    private void createElements() {
        //Create elements
        northShore = createAndAddElement("north-shore", null, null);
        southShore = createAndAddElement("south-shore", null, null);
        sheep = createAndAddElement("sheep", southShore, null);
        wolf = createAndAddElement("wolf", southShore, null);
        cabbage = createAndAddElement("cabbage", southShore, null);
        boat = createAndAddElement("boat", southShore, null);
        game.character = createAndAddElement("character", boat, null);
    }

    private void createActions() {
        //Create action consequences
        crossSouthNorth = buildChangeContainerAction(boat, northShore);
        crossNorthSouth = buildChangeContainerAction(boat, southShore);
        leaveSheepSouth = buildChangeContainerAction(sheep, southShore);
        leaveWolfSouth = buildChangeContainerAction(wolf, southShore);
        leaveCabbageSouth = buildChangeContainerAction(cabbage, southShore);
        leaveSheepNorth = buildChangeContainerAction(sheep, northShore);
        leaveWolfNorth = buildChangeContainerAction(wolf, northShore);
        leaveCabbageNorth = buildChangeContainerAction(cabbage, northShore);
        takeSheep = buildChangeContainerAction(sheep, boat);
        takeWolf = buildChangeContainerAction(wolf, boat);
        takeCabbage = buildChangeContainerAction(cabbage, boat);
    }

    private void createRules() {
        boatHasSheep = checkContainerRule(sheep, boat, "Sheep is not on board");
        boatHasNoSheep = doesntHaveContainerRule(sheep, boat, "Sheep is on board");
        boatHasWolf = checkContainerRule(wolf, boat, "Wolf is not on board");
        boatHasNoWolf = doesntHaveContainerRule(wolf, boat, "Wolf is on board");
        boatHasCabbage = checkContainerRule(cabbage, boat, "Cabbage is not on board");
        boatHasNoCabbage = doesntHaveContainerRule(cabbage, boat, "Cabbage is on board");
        southShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, southShore, "Wolf is is on the south-shore");
        northShoreContainsWolf = checkContainerRule(wolf, northShore, "Wolf is is not on the north-shore");
        southShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, southShore, "Sheep is on the south-shore");
        northShoreContainsSheep = checkContainerRule(sheep, northShore, "Sheep is is not on the north-shore");
        southShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, southShore, "Cabbage is on the south-shore");
        northShoreContainsCabbage = checkContainerRule(cabbage, northShore, "Cabbage is is not on the north-shore");
        northShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, northShore, "Wolf is on the north-shore");
        northShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, northShore, "Sheep is on the north-shore");
        northShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, northShore, "Cabbage is on the north-shore");
        boatIsOnSouthShore = checkContainerRule(boat, southShore, "Boat is not on the south-shore");
        boatIsOnNorthShore = checkContainerRule(boat, northShore, "Boat is not on the north-shore");
    }

    private void createComplexRules() {
        logicBuilder = new LogicBuilder();
        //Rules to cross
        rulesToCrossNorthShore = null;
        rulesToCrossSouthShore = null;
        ruleToTake = null;
        try {
            rulesToCrossNorthShore = logicBuilder.build(southShoreDoesntContainsWolf,
                    southShoreDoesntContainsCabbage, '&');
            rulesToCrossNorthShore = logicBuilder.build(rulesToCrossNorthShore,
                    southShoreDoesntContainsSheep, '|');
            rulesToCrossSouthShore = logicBuilder.build(northShoreDoesntContainsWolf,
                    northShoreDoesntContainsCabbage, '&');
            rulesToCrossSouthShore = logicBuilder.build(rulesToCrossSouthShore,
                    northShoreDoesntContainsSheep, '|');
            ruleToTake = logicBuilder.build(boatHasNoWolf, boatHasNoSheep, '&');
            ruleToTake = logicBuilder.build(ruleToTake, boatHasNoCabbage, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
    }

    private void createVictoryRule() {
        //Victory Condition
        victoryRule = null;
        try {
            victoryRule = logicBuilder.build(northShoreContainsWolf, northShoreContainsSheep, '&');
            victoryRule = logicBuilder.build(victoryRule, northShoreContainsCabbage, '&');
        } catch (WrongLogicSymbolException e) {
            System.out.print(logicMessage + ".\n");
        }
        game.setVictoryCondition(victoryRule);
    }

    private void createMoves() {
        crossSouthShore = moveWithActionsAndRules("cross", crossNorthSouth, rulesToCrossSouthShore, "you have crossed!");
        crossNorthShore = moveWithActionsAndRules("cross", crossSouthNorth, rulesToCrossNorthShore, "you have crossed!");
        takeSheepAboard = moveWithActionsAndRules("take", takeSheep, ruleToTake, "Ok");
        takeWolfAboard = moveWithActionsAndRules("take", takeWolf, ruleToTake, "Ok");
        takeCabbageAboard = moveWithActionsAndRules("take", takeCabbage, ruleToTake, "Ok");
        leaveSheep = moveWithActionsAndRules("leave", leaveSheepSouth, boatHasSheep, "Ok");
        leaveWolf = moveWithActionsAndRules("leave", leaveWolfSouth, boatHasWolf, "Ok");
        leaveCabbage = moveWithActionsAndRules("leave", leaveCabbageSouth, boatHasCabbage, "Ok");
    }

    private void injectActionsToMoves() {
        leaveSheep.addAction(leaveSheepNorth);
        leaveWolf.addAction(leaveWolfNorth);
        leaveCabbage.addAction(leaveCabbageNorth);
    }

    private void injectRulesToActions() {
        leaveSheepSouth.setRules(boatIsOnSouthShore);
        leaveSheepNorth.setRules(boatIsOnNorthShore);
        leaveCabbageSouth.setRules(boatIsOnSouthShore);
        leaveCabbageNorth.setRules(boatIsOnNorthShore);
        leaveWolfSouth.setRules(boatIsOnSouthShore);
        leaveWolfNorth.setRules(boatIsOnNorthShore);
    }

    private void addMovesToElements() {
        wolf.addMove(leaveWolf);
        wolf.addMove(takeWolfAboard);
        sheep.addMove(leaveSheep);
        sheep.addMove(takeSheepAboard);
        cabbage.addMove(leaveCabbage);
        cabbage.addMove(takeCabbageAboard);
        northShore.addMove(crossNorthShore);
        southShore.addMove(crossSouthShore);
    }

    public void setElements() {
        createElements();
        createActions();
        createRules();
        createComplexRules();
        createVictoryRule();
        createMoves();
        injectActionsToMoves();
        injectRulesToActions();
        addMovesToElements();
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "cross");
        createAndAddSuportedAction(1, "take");
        createAndAddSuportedAction(1, "leave");
    }
}
