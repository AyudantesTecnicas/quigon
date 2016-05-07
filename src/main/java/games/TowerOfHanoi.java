package games;

import creation.GameBuilder;

public final class TowerOfHanoi extends GameBuilder {

    public static String gameDescription = "There are tree sticks with plates on this game.";

    public TowerOfHanoi() {
        gameName = "TowerOfHanoi";
    }

    public void setAmountOfRooms() {
        amountOfRooms = 3;
    }
    @SuppressWarnings("CPD-START")
    public void setElements() {
    }
    @SuppressWarnings("CPD-END")
    public void setActions() {
    }
}
