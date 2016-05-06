package creation;

import model.elements.Element;
import parser.GameParser;
import parser.SupportedAction;

import java.util.*;

public abstract class GameBuilder {

    public static final String winText = "You won the game!";
    public static final String logicMessage = "Wrong expressed logic";

    protected Game game;
    protected int amountOfRooms;

    protected static String gameName;
    protected static String gameDescription;
    protected ArrayList<SupportedAction> actionsList;
    protected List<Element> elementsList;

    public String getName() {
        return gameName;
    }

    public String getDescription() {
        return gameDescription;
    }

    public Game getGame() {
        return game;
    }

    public void createNewGame() {
        game = new Game();
    }

    public void createActionsList() {
        actionsList = new ArrayList<SupportedAction>();
    }

    protected void createElementList() {
        elementsList = new ArrayList<Element>();
    }

    protected void createParser() {
        game.setParser(new GameParser());
    }

    protected abstract void setActions();

    public void setNameDescription() {
        game.setName(gameName);
    }

    protected GameBuilder() {
    }

    protected void fillParserSupportedActions(SupportedAction supportedAction) {
        actionsList.add(supportedAction);
    }

    protected void addElement(Element anElement) {
        elementsList.add(anElement);
    }

    protected void setElementsToGame() {
        game.elementList = elementsList;
    }

    public void addActionsToParser() {
        game.parser.setSupportedActions(actionsList);
    }

    protected abstract void setElements();
}