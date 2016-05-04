package Model.elements;

import Model.actions.Move;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ComplexElement extends Element implements Comparable<ComplexElement> {

    //Attributes
    private List<Element> states;
    private List<Move> moves;
    private ComplexElement containerElement;
    private Integer size;
    private Boolean visible;
    private List<ComplexElement> elements;

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
            if (!this.states.contains(state)) {
                this.states.add(state);
            }
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

    public void setContainerElement(ComplexElement containerElement) {
        this.containerElement.removeElement(this);
        this.containerElement = containerElement;
        containerElement.addElement(this);
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

    private Integer getSize() {
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
        this.elements = null;
    }

    public String execute(String moveName) {
        Iterator<Move> iterator = this.moves.iterator();
        Move move;
        String oneString = "Invalid Action";
        while (iterator.hasNext()) {
            move = iterator.next();

            if (move.getName().equals(moveName)) {
                move.execute();
                oneString = move.getResultMessage();
            }
        }
        return oneString;
    }

    @Override
    public int compareTo(ComplexElement other) {
        return (this.size - other.getSize());
    }

    public void addElement(ComplexElement element) {
        if (element != null) {
            if (this.elements == null) {
                this.elements = new ArrayList<>();
            }
            if (!this.elements.contains(element)) {
                this.elements.add(element);
            }
        }
    }

    public void removeElement(ComplexElement element) {
        this.elements.remove(element);
    }

    public Boolean hasElement(ComplexElement element) {
        return this.elements.contains(element);
    }

    public ComplexElement getElementAt(String index) {
        Integer intIndex;

        if (!index.isEmpty()) {
            index = index.toLowerCase();

            if (index.equals("last")) {
                return this.elements.get(this.elements.size() - 1);
            }

            if (index.equals("first")) {
                return this.elements.get(0);
            }

            intIndex = Integer.parseInt(index);

            if (intIndex >= 0 && intIndex < this.elements.size()) {
                return this.elements.get(intIndex);
            }
        }

        return null;
    }
}
