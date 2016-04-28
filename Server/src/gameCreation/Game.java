package gameCreation;

import GameParser.GameParser;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.IExpression;
import GameParser.GameAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Game {
    private String gameName;
    public ComplexElement character;
    public List<Element> elementList;
    public IExpression rules;
    public GameParser parser;
    public IExpression victoryCondition;

    Game(){

    }

    public void setName(String gameName) { this.gameName = gameName; }

    public void reset() {
        System.out.println(gameName + " reset.");
    }

    private boolean checkVictory(){
        return victoryCondition.interpret();
    }

    public String receiveCommands(String command){
        String sendCommand="";
        GameAction actionToExecute = parser.parseInstruction(command);
        sendCommand = actionToExecute.getMessage();
        if (actionToExecute.isASupportedAction()){
            for (Element anElement : elementList){
                for (String itemsID : actionToExecute.getItemsID()){
                    if(anElement.getName().equals(itemsID)){
                        sendCommand= ((ComplexElement)anElement).execute(actionToExecute.getActionID());
                    }
                }
            }
        }

        if (checkVictory())
            sendCommand = "YouWon";
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
