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
    private Integer size;
    private Boolean visible;

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

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Boolean getVisible() {
        return this.visible;
    }

    private void initComplexElement() {
        this.states = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.setContainerElement(null);
        this.size = 0;
        this.visible = true;
    }

    public String execute(String moveName) {
        Iterator<Move> iterator = this.moves.iterator();
        Move move = null;
        String aString="Invalid Action";
        while(iterator.hasNext()) {
            move = iterator.next();

            if (move.getName().equals(moveName)) {
                move.execute();
                aString= move.getResultMessage();
            }
        }
        return aString;
    }

}
