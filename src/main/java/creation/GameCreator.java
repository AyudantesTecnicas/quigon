package creation;

import games.*;

import java.security.InvalidParameterException;

public final class GameCreator {
    private GameMaker gameMaker;
    private GameBuilder gameBuilder;

    public GameCreator() {
        gameMaker = new GameMaker();
    }

    private void createFurther(String gameName) throws InvalidParameterException {
        switch (gameName) {
            case "OpenDoor2":
                gameBuilder = new OpenDoor2();
                break;
            case "TreasureHunt":
                gameBuilder = new TreasureHunt();
                break;
            case "TowerOfHanoi":
                gameBuilder = new TowerOfHanoi();
                break;
            case "WolfSheep":
                gameBuilder = new WolfSheep();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    private void instantiateGame(String gameName) throws InvalidParameterException {
        switch (gameName) {
            case "CursedObject":
                gameBuilder = new CursedObject();
                break;
            case "FetchQuest":
                gameBuilder = new FetchQuest();
                break;
            case "OpenDoor":
                gameBuilder = new OpenDoor();
                break;
            default:
                createFurther(gameName);
        }
    }

    public void createGame(String gameName) throws InvalidParameterException {
        instantiateGame(gameName);
        gameMaker.setGameBuilder(gameBuilder);
        gameMaker.buildGame();
    }

    public Game getGame() {
        return gameMaker.getGame();
    }

    public void resetGame() {
        gameMaker.setGameBuilder(gameBuilder);
        gameMaker.buildGame();
        System.out.println(gameBuilder.getGame().getName() + " reset.");
    }
}


