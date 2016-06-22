package driver;

import creation.GameRandom;
import time.GameTimer;

public interface GameDriver {
    void loadBuilder(String jarPath);

    void setGameRandom(GameRandom gameRandom) throws GameBuilderNotLoadedException;

    void setGameTimer(GameTimer gameTimer) throws GameBuilderNotLoadedException;

    void buildGame() throws GameBuilderNotLoadedException;

    String sendCommand(int player, String cmd) throws GameNotBuiltException;

    PlayerState getCurrentStateOfPlayer(int number);
}