package gameFiles;

import gameCreation.GameBuilder;

public class WolfSheep extends GameBuilder {

    public static String gameDescription= "There is a Wolf, a Sheep and a Cabbage";
    public WolfSheep() {
        gameName = "WolfSheep";

    }
    public void setElements(){}
    public void setActions(){}
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
