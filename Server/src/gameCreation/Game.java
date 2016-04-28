package gameCreation;

import GameParser.GameParser;
import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.rules.IExpression;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Game {
    private String gameName;
    public ComplexElement character;
    public List<Element> elementList;
    public IExpression rules;
    public GameParser parser;

    Game(){

    }
    public void setName(String gameName) { this.gameName = gameName; }

    public void reset() {
        System.out.println(gameName + " reset.");
    }

    //public boolean checkVictoryCondition(){

    //}
    public String receiveCommands(String command){
        return "doing Something";
    }
    public String getName(){return gameName;}

    public void setParser(GameParser parser) {
        this.parser = parser;
    }
    public void setElements( List<Element> elementList) {
        this.elementList = elementList;
    }
}
