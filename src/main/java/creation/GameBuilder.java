package creation;

import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.ruleExpressions.rules.*;
import model.ruleExpressions.expressions.*;
import parser.GameParser;
import parser.SupportedAction;

import java.util.*;

public abstract class GameBuilder {

    public static final String winText = "You won the game!";
    protected static final String logicMessage = "Wrong expressed logic";

    protected Game game;
    protected int amountOfRooms;

    protected String gameName;
    private String gameDescription;
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

    private void addElementsToAction(Action action, ComplexElement contained, Element state) {
        action.setElementToUpdate(contained);
        action.addItemToUpdate(state);
    }

    protected Action buildChangeContainerAction(ComplexElement contained, ComplexElement container) {
        Action changeContainer = new ChangeContainerAction();
        addElementsToAction(changeContainer, contained, container);
        return changeContainer;
    }

    protected Action buildAddStatesAction(ComplexElement contained, Element state) {
        Action changeContainer = new AddStatesAction();
        addElementsToAction(changeContainer, contained, state);
        return changeContainer;
    }

    protected Action buildRemoveStatesAction(ComplexElement contained, Element state) {
        Action changeContainer = new RemoveStatesAction();
        addElementsToAction(changeContainer, contained, state);
        return changeContainer;
    }

    private void addElementsToContainerRule(ContainerRule rule, ComplexElement contained, ComplexElement container, String failMessage) {
        rule.setElementToValidate(contained);
        rule.setContainerToValidate(container);
        rule.setFailMessage(failMessage);
    }

    protected HasContainerRule checkContainerRule(ComplexElement contained, ComplexElement container, String failMessage) {
        HasContainerRule rule = new HasContainerRule();
        addElementsToContainerRule(rule, contained, container, failMessage);
        return rule;
    }

    protected DoesNotHaveContainerRule checkDoesntHaveContainerRule(ComplexElement contained, ComplexElement container, String failMessage) {
        DoesNotHaveContainerRule rule = new DoesNotHaveContainerRule();
        addElementsToContainerRule(rule, contained, container, failMessage);
        return rule;
    }

    protected HasStateRule checkStateRule(ComplexElement contained, Element state, String failMessage) {
        HasStateRule rule = new HasStateRule();
        rule.setElementToValidate(contained);
        rule.setStateToValidate(state);
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

    protected ComplexElement createAndAddElement(String name, ComplexElement container, Element state) {
        ComplexElement complexElement = new ComplexElement(name);
        complexElement.setContainerElement(container);
        complexElement.addState(state);
        addElement(complexElement);
        return complexElement;
    }

    protected void createAndAddSuportedAction(int numberOfItemsAffected, String action) {
        actionsList.add(new SupportedAction(numberOfItemsAffected, action));
    }

    protected abstract void setActions();

    void setNameDescription() {
        game.setName(gameName);
    }

    protected GameBuilder() {
    }

    protected void fillParserSupportedActions(SupportedAction supportedAction) {
        actionsList.add(supportedAction);
    }

    private void addElement(Element anElement) {
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