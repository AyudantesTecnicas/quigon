package creation;

class GameMaker {
    private GameBuilderImp gameBuilderImp;

    void setGameBuilderImp(GameBuilderImp gameBuilderImp) {
        this.gameBuilderImp = gameBuilderImp;
    }

    public Game getGame() {
        return gameBuilderImp.getGame();
    }

    public void buildGame() {
        gameBuilderImp.createNewGame();
        gameBuilderImp.setNameDescription();
        gameBuilderImp.createParser();
        gameBuilderImp.createElementList();
        gameBuilderImp.setElements();
        gameBuilderImp.setElementsToGame();
        gameBuilderImp.createActionsList();
        gameBuilderImp.setActions();
        gameBuilderImp.addActionsToParser();
        gameBuilderImp.setElementsToGame();
    }
}
