package Model.elements;

import Model.actions.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by metro on 27/04/16.
 */
public class ComplexElement extends Element {

    //Attributes
    private List<Element> states;
    private List<Move> moves;
    private Element containerElement;

    //Methods
    public ComplexElement() {
        super();
        this.initComplexElement();
    }

    public ComplexElement(String name) {
        super(name);
        this.initComplexElement();
    }

    public void addState(Element state) {
        if (state != null) {
            if (!this.states.contains(state))
                this.states.add(state);
        }
    }

    public void removeState(Element state) {
        if (state != null) {
            this.states.remove(state);
        }
    }

    public void addMove(Move move) {
        if (move != null) {
            if (!this.moves.contains(move)) {
                this.moves.add(move);
            }
        }
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

    private void initComplexElement() {
        this.states = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.setContainerElement(null);
    }

    public void execute(String moveName) {
        Iterator<Move> iterator = this.moves.iterator();
        Move move = null;

        while(iterator.hasNext()) {
            move = iterator.next();

            if (move.getName().equals(moveName)) {
                move.execute();
                break;
            }
        }
    }

}
