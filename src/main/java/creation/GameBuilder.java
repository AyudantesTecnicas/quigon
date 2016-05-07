package creation;

import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rules.*;
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

    private void addElementsToAction(Action action,ComplexElement contained, Element container){
        action.setElementToUpdate(contained);
        action.addItemToUpdate(container);
    }

    protected Action buildChangeContainerAction(ComplexElement contained, Element container) {
        Action changeContainer = new ChangeContainerAction();
        addElementsToAction(changeContainer,contained,container);
        return changeContainer;
    }

    protected Action buildAddStatesAction(ComplexElement contained, Element container) {
        Action changeContainer = new AddStatesAction();
        addElementsToAction(changeContainer,contained,container);
        return changeContainer;
    }

    protected Action buildRemoveStatesAction(ComplexElement contained, Element container) {
        Action changeContainer = new RemoveStatesAction();
        addElementsToAction(changeContainer,contained,container);
        return changeContainer;
    }

    private void addElementsToRule(RuleExpression rule, ComplexElement contained, Element container, String failMessage){
        rule.setElementToValidate(contained);
        rule.setElementOfElementToValidate(container);
        rule.setFailMessage(failMessage);
    }

    protected HasContainerRule checkContainerRule(ComplexElement contained, Element container, String failMessage) {
        HasContainerRule rule = new HasContainerRule();
        addElementsToRule(rule,contained,container,failMessage);
        return rule;
    }

    protected DoesNotHaveContainerRule checkDoesntHaveContainerRule(ComplexElement contained, Element container, String failMessage) {
        DoesNotHaveContainerRule rule = new DoesNotHaveContainerRule();
        addElementsToRule(rule,contained,container,failMessage);
        return rule;
    }


    protected HasStateRule checkStateRule(ComplexElement contained, Element container, String failMessage) {
        HasStateRule rule = new HasStateRule();
        addElementsToRule(rule,contained,container,failMessage);
        return rule;
    }

    protected Move moveWithActionsAndRules(String name, Action action, IExpression rule, String message) {
        Move move= new Move(name);
        move.addAction(action);
        move.setRules(rule);
        move.setResultMessage(message);
        return move;
    }

    protected ComplexElement createAndAddElement(String name, Element container, Element state) {
        ComplexElement complexElement= new ComplexElement(name);
        complexElement.setContainerElement(container);
        complexElement.addState(state);
        addElement(complexElement);
        return complexElement;
    }

    protected void createAndAddSuportedAction(int numberOfItemsAffected, String action){
        actionsList.add(new SupportedAction(numberOfItemsAffected,action));
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