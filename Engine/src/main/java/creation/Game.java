package creation;

import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.Player;
import model.elements.PlayerManager;
import model.rulesexpressions.expressions.IExpression;
import parser.GameAction;
import parser.GameParser;
import time.GameTimer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

public class Game {
    private String gameName;
    public PlayerManager playerManager;
    List<Element> elementList;
    GameParser parser;
    private String gameDescription;
    private GameTimer gameTimer;

    public Game() {
        playerManager = new PlayerManager();
        gameTimer = new GameTimer();
    }

    void startClock() {
        gameTimer.start();
    }

    protected void stopClock() {
        gameTimer.stop();
    }

    void setName(String gameName) {
        this.gameName = gameName;
    }

    public void setTimeObserver(Observer observer) {
        gameTimer.addObserver(observer);
    }

    void setGameDescription(String description) {
        gameDescription = description;
    }

    public String getHelp() {
        return gameDescription;
    }

    public int getNumberOfPlayers() {
        return playerManager.getNumberOfPlayers();
    }

    public void playerHasDesconect(String playerId) {
        playerManager.playerHasDesconect(playerId);
    }

    public void addCharacter(Player character) {
        playerManager.addCharacter(character);
    }

    private boolean isRechableElement(ComplexElement element) {
        //If isn't current player
        //If item is in the room OR item is in the current player
        return ((element.getContainerElement() != null)
                && !(element.equals(playerManager.currentPlayer))
                && (element.getContainerElement().equals(playerManager.currentPlayer.getContainerElement())
                    || element.getContainerElement().equals(playerManager.currentPlayer)));
    }

    private String checkAroundItems() {
        StringBuilder elementsInRoom = new StringBuilder();
        for (Element element : elementList) {
            if (isRechableElement((ComplexElement) element)) {
                elementsInRoom.append(element.getName());
                elementsInRoom.append('\n');
            }
        }
        return (elementsInRoom.toString());
    }

    private String checkWhatCanIDoWith(String elementName) {
        String movesOfElement = "object not found";
        boolean objectFound = false;
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext() && !objectFound) {
            ComplexElement complexElement = (ComplexElement) iterator.next();
            if (isRechableElement(complexElement) && complexElement.getName().equalsIgnoreCase(elementName)) {
                movesOfElement = complexElement.listMoves();
                objectFound = true;
            }
        }
        return movesOfElement;
    }

    private String commandToSend(String command) {
        String sendCommand;

        GameAction actionToExecute = parser.parseInstruction(command);
        sendCommand = actionToExecute.getMessage();
        if (actionToExecute.isASupportedAction()) {
            sendCommand = "object not found";
            for (Element anElement : elementList) {
                if (isRechableElement((ComplexElement) anElement)) {
                    for (String itemsID : actionToExecute.getItemsID()) {
                        if (anElement.getName().toLowerCase().equals(itemsID)) {
                            sendCommand = ((ComplexElement) anElement).execute(actionToExecute.getActionID());
                        }
                    }
                }
            }
        }
        return sendCommand;
    }

    public String receiveCommands(String command) {
        String playerIdentifier = command.substring(0,command.indexOf(":")); //exclude ':'
        String gameCommand = command.substring(command.indexOf(":") + 1);
        if (playerManager.updateCurrentCharacter(playerIdentifier) == null) {
            return "BUG - Invalid player identifier - " + command;
        }

        String sendCommand;
        if (commandOfGame(gameCommand)) {
            sendCommand = interpretCommand(gameCommand);
        } else {
            sendCommand = commandToSend(gameCommand);
        }

        if (playerManager.currentPlayer.hasWon()) {
            sendCommand = GameBuilderImp.winText;
        } else if (playerManager.currentPlayer.hasLost()) {
            sendCommand = GameBuilderImp.loseText;
        }
        return sendCommand;
    }

    private boolean commandOfGame(String command) {
        return (command.equals("look around") || (command.matches("^(?i)what can i do with [a-zA-Z0-9_-]+\\?$")));
    }

    private String interpretCommand(String command) {
        if (command.equals("look around")) {
            return checkAroundItems();
        } else if (command.matches("^(?i)what can i do with [a-zA-Z0-9_-]+\\?$")) {
            String elementName = command.split(" ")[5];
            elementName = elementName.substring(0, elementName.length() - 1);
            return checkWhatCanIDoWith(elementName);
        }
        return "BUG - can't find game's command";
    }

    public String getName() {
        return gameName;
    }

    void setParser(GameParser parser) {
        this.parser = parser;
    }

    public void setElements(List<Element> elementList) {
        this.elementList = elementList;
    }
}
