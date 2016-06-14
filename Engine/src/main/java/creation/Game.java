package creation;

import model.actions.TimeCondition;
import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.Player;
import model.elements.PlayerManager;
import parser.GameAction;
import parser.GameParser;
import time.GameTimeManager;

import java.util.*;

public class Game implements Observer {
    private String gameName;
    public PlayerManager playerManager;
    List<Element> elementList;
    GameParser parser;
    private String gameDescription;
    private GameTimeManager gameTimeManager;
    private Notifier notifier;

    public Game() {
        playerManager = new PlayerManager();
        gameTimeManager = new GameTimeManager();
    }

    public void startClock() {
        gameTimeManager.startTimers();
    }

    public void stopClock() {
        gameTimeManager.stopTimers();
    }

    void setName(String gameName) {
        this.gameName = gameName;
    }

    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    public void addTimeCondition(TimeCondition timeCondition) {
        this.gameTimeManager.createNewTimerTask(timeCondition);
    }

    public void shootTimeEvents() {
        this.gameTimeManager.shootTimeEvents();
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
        sendCommand = checkWinConditions(playerManager.currentPlayer, sendCommand);
        return sendCommand;
    }

    private String checkWinConditions(Player aPlayer, String command){
        if (aPlayer.hasWon()) {
            command = GameBuilderImp.winText;
        } else if (aPlayer.hasLost()) {
            command = GameBuilderImp.loseText;
        }
        return command;
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

    @Override
    public void update(Observable o, Object arg) {
        if (notifier!=null)
        notifier.notifyEveryone((String)arg);
        String result="";
        int characterIndex=0;
        for (Player aPlayer: playerManager.characters){
            result = checkWinConditions(aPlayer,result);
            if (!result.equals("")){
                if (notifier!=null)
                notifier.notifyPlayer(characterIndex,result);
            }
            characterIndex++;
        }
    }
}
