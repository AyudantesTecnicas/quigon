package model.elements;

import model.actions.Move;
import logic.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexElement extends Element {

    //Attributes
    private List<Element> states;
    private List<Move> moves;
    private Element containerElement;

    //Methods
    public ComplexElement(String name) {
        super(name);
        init();
    }

    public void addState(Element state) {
        Utils.addToCollection(state, states);
    }

    public void removeState(Element state) {
        if (state != null) {
            this.states.remove(state);
        }
    }

    public void addMove(Move move) {
        Utils.addToCollection(move, moves);
    }

    public void setContainerElement(Element containerElement) {
        this.containerElement = containerElement;
    }

    public Element getContainerElement() {
        return this.containerElement;
    }

    public Boolean hasState(Element state) {
        return this.states.contains(state);
    }

    public Boolean hasContainerElement(Element containerElement) {
        return this.containerElement.equals(containerElement);
    }

    protected void init() {
        this.states = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.setContainerElement(null);
    }

    public String execute(String moveName) {
        Iterator<Move> iterator = this.moves.iterator();
        Move move;
        String localString = "Invalid Action";
        while (iterator.hasNext()) {
            move = iterator.next();

            if (move.getName().equals(moveName)) {
                move.execute();
                localString = move.getResultMessage();
            }
        }
        return localString;
    }

}
