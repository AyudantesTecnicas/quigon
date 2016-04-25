package gameCreation;

import gameFiles.*;

import java.util.Map;
import java.util.Vector;

public class ActualGame {
    private Game game;
    private Vector<Room> rooms;

    public Vector<Room> getRooms(){
        return rooms;
    }

    private void fillWithEmptyRooms(Map<Integer,Vector<Integer>> roomsGraph){
        for (int i=0; i<roomsGraph.size();i++){
            rooms.add(new Room());
        }
    }

    private void gameSelection(String gameName){
        switch (gameName) {
            case "CursedObject":  game = new CursedObject();
                break;
            case "FetchQuest":  game = new FetchQuest();
                break;
            case "OpenDoor":  game = new OpenDoor();
                break;
            case "OpenDoor2":  game = new OpenDoor2();
                break;
            case "TreasureHunt":  game = new TreasureHunt();
                break;
            case "TowerOfHanoi":  game = new TowerOfHanoi();
                break;
            case "WolfSheep":  game = new WolfSheep();
                break;
            default: game = null;
                break;
        }
    }

    private void completeRoomsLogic(){
        Map<Integer,Vector<Integer>>roomsGraph = game.getRoomsGraph();
        rooms= new Vector<>();
        fillWithEmptyRooms(roomsGraph);

        for (int i=0;i<rooms.size();i++){
            for (int j=0;j<roomsGraph.get(i).size();j++){
                Room aRoom=rooms.get(i);
                Room anotherRoom= rooms.get(roomsGraph.get(i).get(j));
                aRoom.connect(anotherRoom);
            }
        }
    }
    private void fillRoomsWithEmptyItems(){
        Vector<Integer> items = game.getItems();

        for (int i=0; i<items.size();i++){
                for (int j=0; j<items.get(i);j++){
                    rooms.get(i).addItem(new Item());
            }
        }
    }

    private void completeLogic(){
        completeRoomsLogic();
        fillRoomsWithEmptyItems();
    }

    public ActualGame(String gameName){
        gameSelection(gameName);
        completeLogic();
    }
}
