import creation.GameBuilderImp;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.Action;
import model.actions.Move;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.DoesNotHaveContainerRule;
import model.rulesexpressions.rules.HasContainerRule;

@SuppressWarnings("CPD-START")
public class WolfSheepBuilder extends GameBuilderImp {

    private WolfSheepConstants constants = new WolfSheepConstants();
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

    public WolfSheepBuilder() {
        gameName = "WolfSheep";
        gameDescription = "There is a wolf, a sheep and a cabbage... For what?";
    }

    private void createElements() {
        //Create elements
        northShore = createAndAddElement(constants.northShore, null, null);
        southShore = createAndAddElement(constants.southShore, null, null);
        sheep = createAndAddElement(constants.sheep, southShore, null);
        wolf = createAndAddElement(constants.wolf, southShore, null);
        cabbage = createAndAddElement(constants.cabbage, southShore, null);
        boat = createAndAddElement(constants.boat, southShore, null);
        game.character = createAndAddElement(constants.character, boat, null);
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
        boatHasSheep = checkContainerRule(sheep, boat, constants.sheepNotOnBoard);
        boatHasNoSheep = doesntHaveContainerRule(sheep, boat, constants.sheepOnBoard);
        boatHasWolf = checkContainerRule(wolf, boat, constants.wolfNotOnBoard);
        boatHasNoWolf = doesntHaveContainerRule(wolf, boat, constants.wolfOnBoard);
        boatHasCabbage = checkContainerRule(cabbage, boat, constants.cabbageNotOnBoard);
        boatHasNoCabbage = doesntHaveContainerRule(cabbage, boat, constants.cabbageOnBoard);
        southShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, southShore, constants.wolfOnSouth);
        northShoreContainsWolf = checkContainerRule(wolf, northShore, constants.wolfNotOnNorth);
        southShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, southShore, constants.sheepOnSouth);
        northShoreContainsSheep = checkContainerRule(sheep, northShore, constants.sheepNotOnNorth);
        southShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, southShore, constants.cabbageOnSouth);
        northShoreContainsCabbage = checkContainerRule(cabbage, northShore, constants.cabbageNotOnNorth);
        northShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, northShore, constants.wolfOnNorth);
        northShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, northShore, constants.sheepOnNorth);
        northShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, northShore, constants.cabbageOnNorth);
        boatIsOnSouthShore = checkContainerRule(boat, southShore, constants.boatNotOnSouth);
        boatIsOnNorthShore = checkContainerRule(boat, northShore, constants.boatNotOnNorth);
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
        crossSouthShore = moveWithActionsAndRules(constants.cross, crossNorthSouth, rulesToCrossSouthShore, constants.youCrossed);
        crossNorthShore = moveWithActionsAndRules(constants.cross, crossSouthNorth, rulesToCrossNorthShore, constants.youCrossed);
        takeSheepAboard = moveWithActionsAndRules(constants.take, takeSheep, ruleToTake, constants.ok);
        takeWolfAboard = moveWithActionsAndRules(constants.take, takeWolf, ruleToTake, constants.ok);
        takeCabbageAboard = moveWithActionsAndRules(constants.take, takeCabbage, ruleToTake, constants.ok);
        leaveSheep = moveWithActionsAndRules(constants.leave, leaveSheepSouth, boatHasSheep, constants.ok);
        leaveWolf = moveWithActionsAndRules(constants.leave, leaveWolfSouth, boatHasWolf, constants.ok);
        leaveCabbage = moveWithActionsAndRules(constants.leave, leaveCabbageSouth, boatHasCabbage, constants.ok);
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
        createAndAddSuportedAction(1, constants.cross);
        createAndAddSuportedAction(1, constants.take);
        createAndAddSuportedAction(1, constants.leave);
    }
}
