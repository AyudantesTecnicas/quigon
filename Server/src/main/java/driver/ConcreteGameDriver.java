package driver;

import creation.Game;
import creation.GameBuilderImp;
import creation.GameRandom;
import server.BuilderLoader;

public class ConcreteGameDriver implements GameDriver {
    private GameBuilderImp gameBuilder;
    private Game game;
    private GameState gameState = GameState.NotSet;

    public void loadBuilder(String jarPath) {
        try {
            gameBuilder = BuilderLoader.load(jarPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGameRandom(GameRandom gameRandom) throws GameBuilderNotLoadedException {
        if (gameBuilder != null) {
            gameBuilder.setGameRandom(gameRandom);
        } else {
            throw new GameBuilderNotLoadedException();
        }
    }

    public void buildGame() throws GameBuilderNotLoadedException {
        if (gameBuilder != null) {
            game = gameBuilder.build();
        } else {
            throw new GameBuilderNotLoadedException();
        }
        gameState = GameState.Ready;
    }

    public String sendCommand(String cmd) throws GameNotBuiltException {
        if (game != null) {
            gameState = GameState.InProgress;
            String answer = game.receiveCommands(cmd);
            if (answer.equals(GameBuilderImp.winText)) {
                gameState = GameState.Won;
            }
            if (answer.equals(GameBuilderImp.loseText)) {
                gameState = GameState.Lost;
            }
            return answer;
        } else {
            throw new GameNotBuiltException();
        }
    }

    public void shootTimeEvent(int number) throws GameNotBuiltException {
        if (game != null) {
            game.shootTimeEvent(number);
        } else {
            throw new GameNotBuiltException();
        }
    }

    @Override
    public GameState getCurrentState() {
        return gameState;
    }
}
