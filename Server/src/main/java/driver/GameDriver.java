package driver;

import creation.GameRandom;

public interface GameDriver {
    void loadBuilder(String jarPath);

    void setGameRandom(GameRandom gameRandom);

    void buildGame() throws GameLoadFailedException;

    String sendCommand(String cmd);

    GameState getCurrentState();
}