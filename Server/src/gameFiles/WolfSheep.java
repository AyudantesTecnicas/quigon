package gameFiles;

import gameCreation.Game;

import java.util.Map;
import java.util.Vector;

public class WolfSheep extends Game {
    public WolfSheep() {}

    public void setAmountOfRooms(){
        amountOfRooms=2;
    }
    public void setRooms() {
        fillGraph(0,1);
    }
    public void setItems(){
        fillVector(0,3);
    }
}
