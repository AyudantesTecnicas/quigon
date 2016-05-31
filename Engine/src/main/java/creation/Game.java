package creation;

import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.Player;
import model.rulesexpressions.expressions.IExpression;
import parser.GameAction;
import parser.GameParser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private String gameName;
    public Player currentPlayer;
    public ArrayList<Player> characters;
    List<Element> elementList;
    GameParser parser;
    private String gameDescription;

    Game() {
        characters = new ArrayList<>();
    }

    void setName(String gameName) {
        this.gameName = gameName;
    }

    void setGameDescription(String description) {
        gameDescription = description;
    }

    public String getHelp() {
        return gameDescription;
    }

    public int getNumberOfPlayers() {
        return characters.size();
    }

    public void setCharacter(Player character) {
        if (currentPlayer == null) {
            currentPlayer = character;
        }
        characters.add(character);
    }

    private void updateCurrentCharacter(String command) {
        int characterIndex = Integer.parseInt(command);
        if (characters.size() < characterIndex) {
            currentPlayer = null;
        }
        currentPlayer = characters.get(characterIndex);
    }

    private boolean isRechableElement(ComplexElement element) {
        //If isn't current player
        //If item is in the room OR item is in the current player
        return ((element.getContainerElement() != null)
                && !(element.equals(currentPlayer))
                && (element.getContainerElement().equals(currentPlayer.getContainerElement())
                    || element.getContainerElement().equals(currentPlayer)));
    }

    private String checkAroundItems() {
        if (currentPlayer == null) {
            return "An error have occour - Player not defined";
        }

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
        if (currentPlayer == null) {
            return "An error have occour - Player not defined";
        }

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
        System.out.print("Recieved command " + command);
        String playerIdentifier = command.substring(0,command.indexOf(":")); //exclude ':'
        System.out.print("   PlayerIdentifier " + playerIdentifier);
        String gameCommand = command.substring(command.indexOf(":") + 1);
        System.out.print(" GameCommand " + gameCommand + '\n');
        updateCurrentCharacter(playerIdentifier);
        if (currentPlayer == null) {
            return "BUG - Invalid player identifier - " + command;
        }

        String sendCommand;
        if (commandOfGame(gameCommand)) {
            sendCommand = interpretCommand(gameCommand);
        } else {
            sendCommand = commandToSend(gameCommand);
        }

        if (currentPlayer.hasWon()) {
            sendCommand = GameBuilderImp.winText;
        } else if (currentPlayer.hasLost()) {
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
