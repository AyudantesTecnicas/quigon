package gameFiles;

import gameCreation.GameBuilder;

import java.util.ArrayList;

public final class CursedObject extends GameBuilder {

    public static String gameDescription= "There is a cursed object on this game. And the thief...";

    public CursedObject() {
        gameName = "CursedObject";
    }

    public void setItems(){
        fillVector(0,1);
        fillVector(1,2);
        fillVector(2,1);
    }
    public void setElements(){}
    public void setActions(){}
/*
    public ArrayList<Element> createElements(){
        //crearMoves
        //crearReglas
        //crearAcciones
    }
*/

    public void setAmountOfRooms(){
        amountOfRooms=3;
    }

    public void setRooms() {
        fillGraph(0,1);
        fillGraph(1,2);
    }
}
