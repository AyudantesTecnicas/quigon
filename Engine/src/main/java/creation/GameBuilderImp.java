package creation;

import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.Player;
import model.rulesexpressions.expressions.*;
import model.rulesexpressions.rules.*;
import parser.GameParser;
import parser.SupportedAction;

import java.util.*;

public abstract class GameBuilderImp implements GameBuilder {

    public static final String winText = "You won the game!";
    public static final String loseText = "You lost the game!";
    protected static final String logicMessage = "Wrong expressed logic";

    protected Game game;

    protected String gameName = "[default name]";
    protected String gameDescription = "[default description]";
    private ArrayList<SupportedAction> actionsList;
    private List<Element> elementsList;

    public String getName() {
        return gameName;
    }

    public String getDescription() {
        return gameDescription;
    }

    public void setDescription(String description) {
        this.gameDescription = description;
    }

    public Game getGame() {
        return game;
    }

    void createNewGame() {
        game = new Game();
    }

    void createActionsList() {
        actionsList = new ArrayList<>();
    }

    void createElementList() {
        elementsList = new ArrayList<>();
    }

    void createParser() {
        game.setParser(new GameParser());
    }

    public Game build() {
        createNewGame();
        setNameDescription();
        createParser();
        createElementList();
        setElements();
        setElementsToGame();
        createActionsList();
        setActions();
        addActionsToParser();
        setElementsToGame();
        game.startClock();
        return game;
    }

    protected void addElementsToAction(Action action, ComplexElement contained, Element state) {
        action.setElementToUpdate(contained);
        action.addItemToUpdate(state);
    }

    protected Action buildChangeContainerAction(ComplexElement contained, ComplexElement container) {
        Action changeContainer = new ChangeContainerAction();
        addElementsToAction(changeContainer, contained, container);
        return changeContainer;
    }

    protected Action buildChangeContainerAction(ComplexElement contained, String index, ComplexElement container) {
        Action changeContainer = this.buildChangeContainerAction(contained, container);
        changeContainer.setIndex(index);
        return changeContainer;
    }

    protected Action buildAddStatesAction(ComplexElement contained, Element state) {
        Action addState = new AddStatesAction();
        addElementsToAction(addState, contained, state);
        return addState;
    }

    protected Action buildRemoveStatesAction(ComplexElement contained, Element state) {
        Action changeContainer = new RemoveStatesAction();
        addElementsToAction(changeContainer, contained, state);
        return changeContainer;
    }

    protected void addElementsToContainerRule(ContainerRule rule, ComplexElement contained, ComplexElement container,
                                            String failMessage) {
        rule.setElementToValidate(contained);
        rule.setContainerToValidate(container);
        rule.setFailMessage(failMessage);
    }

    protected HasContainerRule checkContainerRule(ComplexElement contained, ComplexElement container,
                                                  String failMessage) {
        HasContainerRule rule = new HasContainerRule();
        addElementsToContainerRule(rule, contained, container, failMessage);
        return rule;
    }

    protected DoesNotHaveContainerRule doesntHaveContainerRule(ComplexElement contained, ComplexElement container,
                                                               String failMessage) {
        DoesNotHaveContainerRule rule = new DoesNotHaveContainerRule();
        addElementsToContainerRule(rule, contained, container, failMessage);
        return rule;
    }

    protected IsEqualRule checkEqualRule(ComplexElement elementToValidate, ComplexElement elementToCompare, String failMessage) {
        IsEqualRule rule = new IsEqualRule();
        rule.setElementToValidate(elementToValidate);
        rule.setElementToCompare(elementToCompare);
        rule.setFailMessage(failMessage);
        return rule;
    }

    protected HasStateRule checkStateRule(ComplexElement contained, Element state, String failMessage) {
        HasStateRule rule = new HasStateRule();
        rule.setElementToValidate(contained);
        rule.setStateToValidate(state);
        rule.setFailMessage(failMessage);
        return rule;
    }

    protected IsEmptyRule checkIsEmptyRule(ComplexElement element, String failMessage) {
        IsEmptyRule rule = new IsEmptyRule();
        rule.setElementToValidate(element);
        rule.setFailMessage(failMessage);
        return rule;
    }

    protected IsNotEmptyRule checkIsNotEmptyRule(ComplexElement element, String failMessage) {
        IsNotEmptyRule rule = new IsNotEmptyRule();
        rule.setElementToValidate(element);
        rule.setFailMessage(failMessage);
        return rule;
    }

    protected SizeComparisonLesserRule checkSizeComparisonLesserRule(ComplexElement element, String indexToValidate,
                                                                     ComplexElement elementToCompare,
                                                                     String indexToCompare, String failMessage) {
        SizeComparisonLesserRule rule = new SizeComparisonLesserRule();
        rule.setElementToValidate(element);
        rule.setIndexToValidate(indexToValidate);
        rule.setElementToCompare(elementToCompare);
        rule.setIndexToCompare(indexToCompare);
        rule.setFailMessage(failMessage);
        return rule;
    }

    protected Move moveWithActionsAndRules(String name, Action action, IExpression rule, String message) {
        Move move = new Move(name);
        move.addAction(action);
        move.setRules(rule);
        move.setResultMessage(message);
        return move;
    }

    protected Player createAndAddPlayer(String name, ComplexElement container, Element state) {
        Player player = new Player(name);
        player.setContainerElement(container);
        player.addState(state);
        addElement(player);
        return player;
    }

    protected ComplexElement createAndAddElement(String name, ComplexElement container, Element state) {
        ComplexElement complexElement = new ComplexElement(name);
        complexElement.setContainerElement(container);
        complexElement.addState(state);
        addElement(complexElement);
        return complexElement;
    }

    protected ComplexElement createAndAddElement(String name, ComplexElement container, Element state, Integer size) {
        ComplexElement complexElement = this.createAndAddElement(name, container, state);
        complexElement.setSize(size);
        return complexElement;
    }

    protected void createAndAddSuportedAction(int numberOfItemsAffected, String action) {
        actionsList.add(new SupportedAction(numberOfItemsAffected, action));
    }

    protected abstract void setActions();

    void setNameDescription() {
        game.setName(gameName);
        game.setGameDescription(gameDescription);
    }

    protected GameBuilderImp() {
    }

    protected void fillParserSupportedActions(SupportedAction supportedAction) {
        actionsList.add(supportedAction);
    }

    protected void addElement(Element anElement) {
        elementsList.add(anElement);
    }

    void setElementsToGame() {
        game.elementList = elementsList;
    }

    void addActionsToParser() {
        game.parser.setSupportedActions(actionsList);
    }

    protected abstract void setElements();
}