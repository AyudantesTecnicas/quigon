package gameFiles;

import gameCreation.GameBuilder;

public class WolfSheep extends GameBuilder {
    public WolfSheep() {
        gameName = "WolfSheep";
        gameDescription= "There is a Wolf, a Sheep and a Cabbage";
    }

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
