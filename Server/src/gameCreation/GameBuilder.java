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

    protected void createElementList() {game.setElements(new ArrayList<>());}

    protected void createParser(){
        game.setParser(new GameParser());
    }

    protected abstract void setActions();
    public void setNameDescription() {
        game.setName(gameName);
    }

<<<<<<< HEAD
    public void createRoomsGraph(){
        int[] nodes = new int[amountOfRooms];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = i;{
            System.out.print("La logica esta mal expresada.\n");
        }
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

=======
>>>>>>> 5c1cc91a104fb266da4145f76dc0688329b9d3d9
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
