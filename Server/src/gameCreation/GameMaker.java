package gameCreation;
import gameFiles.*;

class GameMaker {
    private GameBuilder gameBuilder;

    void setGameBuilder(GameBuilder aGameBuilder) { gameBuilder = aGameBuilder; }
    public Game getGame() { return gameBuilder.getGame(); }

    public void buildGame() {
        gameBuilder.createNewGame();
        gameBuilder.setAmountOfRooms();
        gameBuilder.createItems();
        gameBuilder.createRoomsGraph();
        gameBuilder.setItems();
        gameBuilder.setRooms();
        gameBuilder.fillWithEmptyRooms();
        gameBuilder.completeRoomsLogic();
        gameBuilder.fillRoomsWithEmptyItems();
        gameBuilder.setNameDescription();
    }
}
