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
        return 2;
    }

    public void setCharacter(Player character) {
        if (currentPlayer == null) {
            currentPlayer = character;
        }
        characters.add(character);
    }

    private String checkAroundItems() {
        if (currentPlayer == null) {
            return "An error have occour - Player not defined";
        }

        StringBuilder elementsInRoom = new StringBuilder();
        Element actualRoom = currentPlayer.getContainerElement();
        for (Element element : elementList) {
            ComplexElement complexElement = (ComplexElement) element;
            //If isn't current player
            //If item is in the room OR item is in the current player
            if ((complexElement.getContainerElement() != null)
                    && !(complexElement.equals(currentPlayer))
                    && (complexElement.getContainerElement().equals(actualRoom)
                        || complexElement.getContainerElement().equals(currentPlayer))) {
                elementsInRoom.append(complexElement.getName());
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
        Element actualRoom = currentPlayer.getContainerElement();
        boolean objectFound = false;
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext() && !objectFound) {
            ComplexElement complexElement = (ComplexElement) iterator.next();
            if ((complexElement.getContainerElement() != null)
                    && (complexElement.getContainerElement().equals(actualRoom)
                    || complexElement.getContainerElement().getName().equals(currentPlayer.getName()))
                    && complexElement.getName().equalsIgnoreCase(elementName)) {
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
                for (String itemsID : actionToExecute.getItemsID()) {
                    if (anElement.getName().toLowerCase().equals(itemsID)) {
                        sendCommand = ((ComplexElement) anElement).execute(actionToExecute.getActionID());
                    }
                }
            }
        }
        return sendCommand;
    }

    public String receiveCommands(String command) {
        String sendCommand;
        if (command.equals("look around")) {
            sendCommand = checkAroundItems();
        } else if (command.matches("^(?i)what can i do with [a-zA-Z0-9_-]+\\?$")) {
            String elementName = command.split(" ")[5];
            elementName = elementName.substring(0, elementName.length() - 1);
            sendCommand = checkWhatCanIDoWith(elementName);
        } else {
            sendCommand = commandToSend(command);
        }

        if (currentPlayer.hasWon()) {
            sendCommand = GameBuilderImp.winText;
        } else if (currentPlayer.hasLost()) {
            sendCommand = GameBuilderImp.loseText;
        }
        return sendCommand;
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
