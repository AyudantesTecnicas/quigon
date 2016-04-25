package gameFiles;

import gameCreation.Game;

import java.util.Map;
import java.util.Vector;

public final class FetchQuest extends Game{
    public FetchQuest() {}

    public void setAmountOfRooms(){
        amountOfRooms=1;
    }

    public void setRooms() {
        fillGraph(0,0);
    }

    public void setItems(){
        fillVector(0,1);
    }
}
