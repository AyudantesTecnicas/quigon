package driver;

import creation.Game;
import creation.GameBuilder;
import creation.GameBuilderImp;
import server.BuilderLoader;

import java.io.IOException;

public class ConcreteGameDriver implements GameDriver {
    private GameBuilder gameBuilder;
    private Game game;
    private GameState gameState;

    public void initGame(String jarPath) throws GameLoadFailedException {
        try {
            gameBuilder = BuilderLoader.load(jarPath);
            if (gameBuilder != null) {
                game = gameBuilder.build();
            } else {
                throw new GameLoadFailedException();
            }
            gameState = GameState.Ready;
        } catch (Exception e) {
            e.printStackTrace();
        }
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
