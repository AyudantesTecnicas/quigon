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
        gameBuilder.setElements();
        gameBuilder.setElementsToGame();
        gameBuilder.createActionsList();
        gameBuilder.setActions();
        gameBuilder.addActionsToParser();
    }
}
