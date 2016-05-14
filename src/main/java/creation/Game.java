package creation;

import model.elements.ComplexElement;
import model.elements.Element;
import model.rulesexpressions.expressions.IExpression;
import parser.GameAction;
import parser.GameParser;

import java.util.Iterator;
import java.util.List;

public class Game {
    private String gameName;
    public ComplexElement character;
    List<Element> elementList;
    GameParser parser;
    private IExpression victoryCondition;

    Game() {
    }

    void setName(String gameName) {
        this.gameName = gameName;
    }

    public void setCharacter(ComplexElement character) {
        this.character = character;
    }

    private boolean checkVictory() {
        return victoryCondition.interpret();
    }

    private String checkAroundItems() {
        StringBuilder elementsInRoom = new StringBuilder();
        Element actualRoom = character.getContainerElement();
        for (Element element : elementList) {
            ComplexElement complexElement = (ComplexElement) element;
            if ((complexElement.getContainerElement() != null) && complexElement.getContainerElement().equals(actualRoom)) {
                elementsInRoom.append(complexElement.getName());
                elementsInRoom.append('\n');
            }
        }
        return (elementsInRoom.toString());
    }

    private String checkWhatCanIDoWith(String elementName) {
        String movesOfElement = "object not found";
        Element actualRoom = character.getContainerElement();
        boolean objectFound = false;
        Iterator<Element> iterator = elementList.iterator();
        while (iterator.hasNext() && !objectFound) {
            ComplexElement complexElement = (ComplexElement) iterator.next();
            if (    (complexElement.getContainerElement() != null)
                    && complexElement.getContainerElement().equals(actualRoom)
                    && complexElement.getName().equals(elementName) ) {
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
                    if (anElement.getName().equals(itemsID)) {
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
            elementName = elementName.substring(0,elementName.length() - 1);
            sendCommand = checkWhatCanIDoWith(elementName);
        } else {
            sendCommand = commandToSend(command);
        }

        if (checkVictory()) {
            sendCommand = GameBuilder.winText;
        }
        return sendCommand;
    }

    public String getName() {
        return gameName;
    }

    public void setVictoryCondition(IExpression condition) {
        victoryCondition = condition;
    }

    void setParser(GameParser parser) {
        this.parser = parser;
    }

    public void setElements(List<Element> elementList) {
        this.elementList = elementList;
    }
}
