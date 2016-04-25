package gameFiles;

import gameCreation.Game;

import java.util.Map;
import java.util.Vector;

public final class TowerOfHanoi extends Game {
    public TowerOfHanoi() {}

    public void setAmountOfRooms(){
        amountOfRooms=3;
    }
    public void setRooms() {
        fillGraph(0,1);
        fillGraph(0,2);
        fillGraph(1,2);
    }

    public void setItems(){
        fillVector(0,6);
    }

}
