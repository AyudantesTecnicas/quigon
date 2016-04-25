package gameFiles;

import gameCreation.Game;

import java.util.Map;
import java.util.Vector;

public class TreasureHunt extends Game {
    public TreasureHunt() {}

    public void setAmountOfRooms(){
        amountOfRooms=5;
    }
    public void setRooms() {
        fillGraph(0,1);
        fillGraph(1,2);
        fillGraph(2,3);
        fillGraph(2,4);
        fillGraph(3,4);
    }

    public void setItems(){
        fillVector(0,4);
        fillVector(1,5);
        fillVector(2,6);
        fillVector(3,10);
        fillVector(4,5);
    }

}
