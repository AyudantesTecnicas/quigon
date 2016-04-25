package gameFiles;

import gameCreation.Game;

import java.util.Map;
import java.util.Vector;

public final class OpenDoor2 extends Game {
    public OpenDoor2() {}

    public void setAmountOfRooms(){
        amountOfRooms=2;
    }
    public void setRooms() {
        fillGraph(0,1);
    }

    public void setItems(){
        fillVector(0,2);
        fillVector(1,1);
    }
}
