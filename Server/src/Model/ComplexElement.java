package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by metro on 27/04/16.
 */
public class ComplexElement extends Element {

    //Attributes
    private List<State> states;
    private Element containerElement;

    //Methods
    public ComplexElement() {
        super();
        this.initStates();
        this.setContainerElement(null);
    }

    public ComplexElement(String name) {
        super(name);
        this.initStates();
        this.setContainerElement(null);
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public void setContainerElement(Element containerElement) {
        this.containerElement = containerElement;
    }

    public Element getContainerElement() {
        return this.containerElement;
    }

    private void initStates() {
        this.states = new ArrayList<>();
    }

}
