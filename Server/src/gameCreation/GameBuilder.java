package gameCreation;

import GameParser.GameParser;
import GameParser.SupportedAction;
import Model.actions.Move;
import Model.elements.Element;

import java.util.*;

public abstract class GameBuilder {

    protected Game game;
    protected int amountOfRooms;

    protected static String gameName;
    protected static String gameDescription;
    protected ArrayList<SupportedAction> actionsList;
    protected List<Element> elementsList;

    public String getName(){return gameName;}
    public String getDescription(){return gameDescription;}

    public Game getGame() { return game; }
    public void createNewGame() { game = new Game(); }

    protected void createParser(){
        GameParser gameParser= new GameParser();
        game.setParser(gameParser);
    }

    protected abstract void setActions();
    public void setNameDescription() {
        game.setName(gameName);
    }

    protected GameBuilder(){
    }

    protected void fillParserSupportedActions(SupportedAction aSupportedAction){
        actionsList.add(aSupportedAction);
    }

    protected void fillElements(Element anElement){
        elementsList.add(anElement);
    }

    protected void setElementsToGame(){
        game.elementList=elementsList;
    }

    public void addActionsToParser() {
        game.parser.setSupportedActions(actionsList);
    }
}