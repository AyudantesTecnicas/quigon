package driver;

import creation.GameRandom;

public interface GameDriver {
    void loadBuilder(String jarPath);

    void setGameRandom(GameRandom gameRandom) throws GameBuilderNotLoadedException;

    void buildGame() throws GameBuilderNotLoadedException;

    // hay que cambiarlo a nueva forma de mensajes (x:cmd) y ademas game state no es tan trivial ahora pues hay varios jugadores
    String sendCommand(String cmd) throws GameNotBuiltException;

    void shootTimeEvent(int number)throws GameNotBuiltException;

    GameState getCurrentState();
}