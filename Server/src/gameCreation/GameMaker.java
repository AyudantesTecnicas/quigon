package gameCreation;
import gameFiles.*;

class GameMaker {
    private GameBuilder gameBuilder;

    void setGameBuilder(GameBuilder aGameBuilder) { gameBuilder = aGameBuilder; }
    public Game getGame() { return gameBuilder.getGame(); }

    public void buildGame() {
        gameBuilder.createNewGame();
        gameBuilder.setNameDescription();
        gameBuilder.createParser();
        gameBuilder.createElementList();
        gameBuilder.setActions();
        gameBuilder.addActionsToParser();
        gameBuilder.setElementsToGame();
        gameBuilder.setVictoryCondition();
    }
}
