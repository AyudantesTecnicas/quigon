package Model;

import java.util.ArrayList;
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
        this.states.add(state);
    }

    public void addMove(Move move) {
        this.moves.add(move);
    }

    public void setContainerElement(Element containerElement) {
        this.containerElement = containerElement;
    }

    public Element getContainerElement() {
        return this.containerElement;
    }

    private void initComplexElement() {
        this.states = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.setContainerElement(null);
    }


}
