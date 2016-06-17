package driver;

import creation.GameRandom;

public interface GameDriver {
    void loadBuilder(String jarPath);

    void setGameRandom(GameRandom gameRandom) throws GameBuilderNotLoadedException;

    void buildGame() throws GameBuilderNotLoadedException;

    String sendCommand(int player, String cmd) throws GameNotBuiltException;

    void shootTimeEvent(int number)throws GameNotBuiltException;

    PlayerState getCurrentStateOfPlayer(int number);
}