package gameFiles;

import gameCreation.GameBuilder;

public final class CursedObject extends GameBuilder {
    public CursedObject() {}

    public void setItems(){
        fillVector(0,1);
        fillVector(1,2);
        fillVector(2,1);
    }

    public void setAmountOfRooms(){
        amountOfRooms=3;
    }

    public void setRooms() {
        fillGraph(0,1);
        fillGraph(1,2);
    }
}
