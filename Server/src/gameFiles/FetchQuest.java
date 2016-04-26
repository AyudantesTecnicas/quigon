package gameFiles;

import gameCreation.GameBuilder;

public final class FetchQuest extends GameBuilder {
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
