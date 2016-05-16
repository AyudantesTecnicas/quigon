package creation;

import games.*;

import java.security.InvalidParameterException;

public final class GameCreator {
    private GameMaker gameMaker;
    private GameBuilderImp gameBuilderImp;

    public GameCreator() {
        gameMaker = new GameMaker();
    }

    private void createFurther(String gameName) throws InvalidParameterException {
        switch (gameName) {
            case "OpenDoor2":
                gameBuilderImp = new OpenDoor2();
                break;
            case "TreasureHunt":
                gameBuilderImp = new TreasureHunt();
                break;
            case "TowerOfHanoi":
                gameBuilderImp = new TowerOfHanoi();
                break;
            case "WolfSheep":
                gameBuilderImp = new WolfSheep();
                break;
            default:
                throw new InvalidParameterException();
        }
    }

    private void instantiateGame(String gameName) throws InvalidParameterException {
        switch (gameName) {
            case "CursedObject":
                gameBuilderImp = new CursedObject();
                break;
            case "OpenDoor":
                gameBuilderImp = new OpenDoor();
                break;
            default:
                createFurther(gameName);
        }
    }

    public void createGame(String gameName) throws InvalidParameterException {
        instantiateGame(gameName);
        gameMaker.setGameBuilderImp(gameBuilderImp);
        gameMaker.buildGame();
    }

    public Game getGame() {
        return gameMaker.getGame();
    }

    public void resetGame() {
        gameMaker.setGameBuilderImp(gameBuilderImp);
        gameMaker.buildGame();
        System.out.println(gameBuilderImp.getGame().getName() + " reset.");
    }
}


