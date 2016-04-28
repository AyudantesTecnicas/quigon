package gameCreation;

import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.IExpression;

import java.util.List;
import java.util.Vector;

public class Game {
    private Vector<Room> rooms;
    private String gameName;
    public ComplexElement character;
    public List<Element> elementList;
    public IExpression rules;


    Game(){
        rooms= new Vector<Room>();
    }
    public void setName(String gameName) { this.gameName = gameName; }

    public void reset() {
        System.out.println(gameName + " reset.");
    }

    public String receiveCommands(String command){
        return "doing Something";
    }
    public String getName(){return gameName;}
    public void setRooms(Vector<Room> rooms) { this.rooms = rooms; }
    public Vector<Room> getRooms(){
        return rooms;
    }
}
