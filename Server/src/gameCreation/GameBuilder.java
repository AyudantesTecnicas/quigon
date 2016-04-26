package gameCreation;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public abstract class GameBuilder {

    private Game game;
    public Game getGame() { return game; }
    public void createNewGame() { game = new Game(); }

    private Map<Integer,Vector<Integer>> roomsGraph;
    private Vector<Integer> itemsInRooms;
    protected int amountOfRooms;

    protected abstract void setItems();
    protected abstract void setRooms();
    protected abstract void setAmountOfRooms();

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
}
