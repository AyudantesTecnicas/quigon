package driver;

import creation.Game;
import creation.GameBuilderImp;
import creation.GameRandom;
import creation.Notifier;
import server.BuilderLoader;
import time.GameTimer;

public class ConcreteGameDriver implements GameDriver, Notifier {
    private GameBuilderImp gameBuilder;
    private Game game;
    private PlayerState[] playerStates;

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

    public void setGameTimer(GameTimer gameTimer) throws GameBuilderNotLoadedException {
        if (gameBuilder != null) {
            gameBuilder.setGameTimer(gameTimer);
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
        playerStates = new PlayerState[game.getNumberOfPlayers()];
        for (int i = 0; i < playerStates.length; i++) {
            playerStates[i] = PlayerState.NotPlaying;
        }
        game.setNotifier(this);
    }

    public String sendCommand(int player, String cmd) throws GameNotBuiltException {
        if (game != null) {
            playerStates[player] = PlayerState.Playing;
            String answer = game.receiveCommands(player + ":" + cmd);
            setWonLostState(player, answer);
            return answer;
        } else {
            throw new GameNotBuiltException();
        }
    }

    public void setWonLostState(int number, String msg) {
        if (msg.equals(GameBuilderImp.loseText)) {
            playerStates[number] = PlayerState.Lost;
        }
        if (msg.equals(GameBuilderImp.winText)) {
            playerStates[number] = PlayerState.Won;
        }
    }

    public void notifyPlayer(int numberOfPlayer, String msg) {
        setWonLostState(numberOfPlayer, msg);
    }

    public void notifyEveryone(String msg) {
        // implementation irrelevant for this GameDriver
    }

    @Override
    public PlayerState getCurrentStateOfPlayer(int number) {
        return playerStates[number];
    }
}
