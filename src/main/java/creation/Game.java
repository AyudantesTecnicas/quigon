package creation;

import parser.GameParser;
import model.elements.ComplexElement;
import model.elements.Element;
import model.rules.IExpression;
import parser.GameAction;

import java.util.List;

public class Game {
    private String gameName;
    public ComplexElement character;
    public List<Element> elementList;
    public IExpression rules;
    public GameParser parser;
    public IExpression victoryCondition;

    Game(){

    }

    public void setName(String gameName) {
        this.gameName = gameName;
    }

    private boolean checkVictory(){
        return victoryCondition.interpret();
    }

    public String receiveCommands(String command) {
        String sendCommand="";
        if (command.equals("look around")) {
            Element actualRoom = character.getContainerElement();
            StringBuilder elementsInRoom = new StringBuilder();

            for (Element element : elementList) {
                ComplexElement complexElement = (ComplexElement)element;
                if ((complexElement.getContainerElement() != null) && complexElement.getContainerElement().equals(actualRoom)) {
                    elementsInRoom.append(complexElement.getName() + '\n');
                }
            }
            sendCommand = elementsInRoom.toString();

        } else {
            GameAction actionToExecute = parser.parseInstruction(command);
            sendCommand = actionToExecute.getMessage();
            if (actionToExecute.isASupportedAction()) {
                sendCommand = "object not found";
                for (Element anElement : elementList)  {
                    for (String itemsID : actionToExecute.getItemsID()) {
                        if(anElement.getName().equals(itemsID)) {
                            sendCommand = ((ComplexElement)anElement).execute(actionToExecute.getActionID());
                        }
                    }
                }
            }
        }

        if (checkVictory()) {
            sendCommand = GameBuilder.winText;
        }
        return sendCommand;
    }


    public String getName(){return gameName;}

    public void setVictoryCondition(IExpression condition){
        victoryCondition= condition;
    }

    public void setParser(GameParser parser) {
        this.parser = parser;
    }
    public void setElements( List<Element> elementList) {
        this.elementList = elementList;
    }
}
