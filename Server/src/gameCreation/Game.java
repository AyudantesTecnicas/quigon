package gameCreation;

import java.util.Vector;

public class Game {

    private Vector<Room> rooms;

    Game(){
        rooms= new Vector<Room>();
    }

    public String receiveCommands(String command){
        return "doing Something";
    }

    public void setRooms(Vector<Room> rooms) { this.rooms = rooms; }
    public Vector<Room> getRooms(){
        return rooms;
    }
}
