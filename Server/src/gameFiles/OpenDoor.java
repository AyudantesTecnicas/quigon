package gameFiles;

import gameCreation.GameBuilder;

public final class OpenDoor extends GameBuilder {
    public OpenDoor() {
        gameName = "OpenDoor";
        gameDescription= "There is a Door on this game";
    }

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
