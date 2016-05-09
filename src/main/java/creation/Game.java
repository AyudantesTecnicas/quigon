package creation;

import model.elements.ComplexElement;
import model.elements.Element;
import model.ruleExpressions.expressions.IExpression;
import parser.GameAction;
import parser.GameParser;

import java.util.List;

public class Game {
    private String gameName;
    public ComplexElement character;
    List<Element> elementList;
    public IExpression rules;
    public GameParser parser;
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

    private String checkAroundItems(String command) {
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
            sendCommand = checkAroundItems(command);
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

    public void setParser(GameParser parser) {
        this.parser = parser;
    }

    public void setElements(List<Element> elementList) {
        this.elementList = elementList;
    }
}
