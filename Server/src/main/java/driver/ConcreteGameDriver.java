package driver;

import creation.Game;
import creation.GameBuilderImp;
import creation.GameRandom;
import server.BuilderLoader;

public class ConcreteGameDriver implements GameDriver {
    private GameBuilderImp gameBuilder;
    private Game game;
    private GameState gameState;

    public void loadBuilder(String jarPath) {
        try {
            gameBuilder = BuilderLoader.load(jarPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGameRandom(GameRandom gameRandom) {
        gameBuilder.setGameRandom(gameRandom);
    }

    public void buildGame() throws GameLoadFailedException {
        if (gameBuilder != null) {
            game = gameBuilder.build();
        } else {
            throw new GameLoadFailedException();
        }
        gameState = GameState.Ready;
    }

    public String sendCommand(String cmd) {
        gameState = GameState.InProgress;
        String answer = game.receiveCommands(cmd);
        if (answer.equals(GameBuilderImp.winText)) {
            gameState = GameState.Won;
        }
        if (answer.equals(GameBuilderImp.loseText)) {
            gameState = GameState.Lost;
        }
        return answer;
    }

    @Override
    public GameState getCurrentState() {
        return gameState;
    }
}
