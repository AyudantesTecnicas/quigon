package gameCreation;

import java.util.Vector;

public class Room {
    private Vector<Room> rooms;
    private Vector<Item> items;

    void connect(Room aRoom){
        rooms.add(aRoom);
    }

    void addItem(Item item){
        items.add(item);
    }
    public int itemsSize(){return items.size();}

    Room(){
        rooms= new Vector<>();
        items= new Vector<>();
    }
}
