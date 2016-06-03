import creation.GameBuilderImp;
import logic.LogicBuilder;
import logic.WrongLogicSymbolException;
import model.actions.Action;
import model.actions.Move;
import model.elements.ComplexElement;
import model.elements.Player;
import model.rulesexpressions.expressions.IExpression;
import model.rulesexpressions.rules.DoesNotHaveContainerRule;
import model.rulesexpressions.rules.HasContainerRule;

import java.util.ArrayList;

@SuppressWarnings("CPD-START")
public class WolfSheepBuilder extends GameBuilderImp {

    private WolfSheepConstants constants = new WolfSheepConstants();
    private ArrayList<Player> characters;
    private ComplexElement northShore;
    private ComplexElement southShore;
    private ComplexElement goToNorthShore;
    private ComplexElement goToSouthShore;
    private ComplexElement sheep;
    private ComplexElement wolf;
    private ComplexElement cabbage;
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
        characters = new ArrayList<>();
    }

    private void createElements() {
        //Create elements
        northShore = createAndAddElement("costaNorte", null, null);
        southShore = createAndAddElement("costaSur", null, null);
        goToNorthShore = createAndAddElement(constants.northShore, southShore, null);
        goToSouthShore = createAndAddElement(constants.southShore, northShore, null);
        sheep = createAndAddElement(constants.sheep, southShore, null);
        wolf = createAndAddElement(constants.wolf, southShore, null);
        cabbage = createAndAddElement(constants.cabbage, southShore, null);

        characters.add(createAndAddPlayer(constants.character, southShore, null));
        game.characters = characters;
        game.currentPlayer = characters.get(0);
    }

    private void createActions() {
        //Create action consequences
        crossSouthNorth = buildChangeContainerAction(game.currentPlayer, northShore);
        crossNorthSouth = buildChangeContainerAction(game.currentPlayer, southShore);
        leaveSheepSouth = buildChangeContainerAction(sheep, southShore);
        leaveWolfSouth = buildChangeContainerAction(wolf, southShore);
        leaveCabbageSouth = buildChangeContainerAction(cabbage, southShore);
        leaveSheepNorth = buildChangeContainerAction(sheep, northShore);
        leaveWolfNorth = buildChangeContainerAction(wolf, northShore);
        leaveCabbageNorth = buildChangeContainerAction(cabbage, northShore);
        takeSheep = buildChangeContainerAction(sheep, game.currentPlayer);
        takeWolf = buildChangeContainerAction(wolf, game.currentPlayer);
        takeCabbage = buildChangeContainerAction(cabbage, game.currentPlayer);
    }

    private void createRules() {
        boatHasSheep = checkContainerRule(sheep, game.currentPlayer, constants.sheepNotOnBoard);
        boatHasNoSheep = doesntHaveContainerRule(sheep, game.currentPlayer, constants.sheepOnBoard);
        boatHasWolf = checkContainerRule(wolf, game.currentPlayer, constants.wolfNotOnBoard);
        boatHasNoWolf = doesntHaveContainerRule(wolf, game.currentPlayer, constants.wolfOnBoard);
        boatHasCabbage = checkContainerRule(cabbage, game.currentPlayer, constants.cabbageNotOnBoard);
        boatHasNoCabbage = doesntHaveContainerRule(cabbage, game.currentPlayer, constants.cabbageOnBoard);
        southShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, southShore, constants.wolfOnSouth);
        northShoreContainsWolf = checkContainerRule(wolf, northShore, constants.wolfNotOnNorth);
        southShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, southShore, constants.sheepOnSouth);
        northShoreContainsSheep = checkContainerRule(sheep, northShore, constants.sheepNotOnNorth);
        southShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, southShore, constants.cabbageOnSouth);
        northShoreContainsCabbage = checkContainerRule(cabbage, northShore, constants.cabbageNotOnNorth);
        northShoreDoesntContainsWolf = doesntHaveContainerRule(wolf, northShore, constants.wolfOnNorth);
        northShoreDoesntContainsSheep = doesntHaveContainerRule(sheep, northShore, constants.sheepOnNorth);
        northShoreDoesntContainsCabbage = doesntHaveContainerRule(cabbage, northShore, constants.cabbageOnNorth);
        boatIsOnSouthShore = checkContainerRule(game.currentPlayer, southShore, constants.boatNotOnSouth);
        boatIsOnNorthShore = checkContainerRule(game.currentPlayer, northShore, constants.boatNotOnNorth);
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
        game.currentPlayer.setVictoryCondition(victoryRule);
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
        goToNorthShore.addMove(crossNorthShore);
        goToSouthShore.addMove(crossSouthShore);
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
