package gameCreation;

import com.sun.nio.sctp.InvalidStreamException;
import gameFiles.*;

import java.security.InvalidParameterException;

public final class GameCreator {
    GameMaker gameMaker;

    public GameCreator(String gameName) throws InvalidParameterException {
        gameMaker= new GameMaker();
        GameBuilder gameBuilder;

        switch (gameName) {
            case "CursedObject":  gameBuilder = new CursedObject();
                break;
            case "FetchQuest":  gameBuilder = new FetchQuest();
                break;
            case "OpenDoor":  gameBuilder = new OpenDoor();
                break;
            case "OpenDoor2":  gameBuilder = new OpenDoor2();
                break;
            case "TreasureHunt":  gameBuilder = new TreasureHunt();
                break;
            case "TowerOfHanoi":  gameBuilder = new TowerOfHanoi();
                break;
            case "WolfSheep":  gameBuilder = new WolfSheep();
                break;
            default: throw new InvalidParameterException();
        }
        gameMaker.setGameBuilder(gameBuilder);
        gameMaker.buildGame();
    }

    public Game getGame() { return gameMaker.getGame(); }

}
