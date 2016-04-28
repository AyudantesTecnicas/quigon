package gameCreation;

import GameParser.GameParser;
import GameParser.SupportedAction;
import Model.actions.Move;
import Model.elements.Element;

import java.util.*;

public abstract class GameBuilder {

    protected Game game;
    private Map<Integer,Vector<Integer>> roomsGraph;
    private Vector<Integer> itemsInRooms;
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


    protected abstract void setItems();
    protected abstract void setActions();
    protected abstract void setElements();
    protected abstract void setRooms();
    protected abstract void setAmountOfRooms();

    public void setNameDescription() {
        game.setName(gameName);
    }

    public void createRoomsGraph(){
        int[] nodes = new int[amountOfRooms];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = i;
        }
        roomsGraph = new HashMap<>();

        for (int i = 0; i < amountOfRooms; ++i) {
            roomsGraph.put(i, new Vector<>());
        }
    }
    public void createItems(){
        itemsInRooms = new Vector<>();
    }


    public void fillWithEmptyRooms(){


        for (int i=0; i<roomsGraph.size();i++){
            game.getRooms().add(new Room());
        }
    }

    public void completeRoomsLogic(){
        for (int i=0;i< game.getRooms().size();i++){
            for (int j=0;j<roomsGraph.get(i).size();j++){
                Room aRoom= game.getRooms().get(i);
                Room anotherRoom=game.getRooms().get(roomsGraph.get(i).get(j));
                aRoom.connect(anotherRoom);
            }
        }
    };

    public void fillRoomsWithEmptyItems(){

        for (int i=0; i<amountOfRooms;i++){
            for (int j=0; j<itemsInRooms.get(i);j++){
                game.getRooms().get(i).addItem(new Item());
            }
        }
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

    protected void fillGraph(int a, int b){
        roomsGraph.get(a).add(b);
        roomsGraph.get(b).add(a);
    }
    protected void fillVector(int a, int amountOfItems){
        itemsInRooms.add(a,amountOfItems);
    }

    Map<Integer,Vector<Integer>> getRoomsGraph(){
        return roomsGraph;
    }

    Vector<Integer> getItems(){
        return itemsInRooms;
    }

    public void addActionsToParser() {
        game.parser.setSupportedActions(actionsList);
    }
}
