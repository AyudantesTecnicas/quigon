package gameCreation;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;


public abstract class Game {
    private Map<Integer,Vector<Integer>> roomsGraph;
    private Vector<Integer> itemsInRooms;
    protected int amountOfRooms;

    protected Game(){
        setAmountOfRooms();

        int[] nodes = new int[amountOfRooms];
        for (int i = 0; i < nodes.length; ++i) {
            nodes[i] = i;
        }
        roomsGraph = new HashMap<>();

        for (int i = 0; i < nodes.length; ++i) {
            roomsGraph.put(i, new Vector<>());
        }

        itemsInRooms = new Vector<>();
        setRooms();
        setItems();
    }

    protected abstract void setItems();
    protected abstract void setRooms();
    protected abstract void setAmountOfRooms();

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
