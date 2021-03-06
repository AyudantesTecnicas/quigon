package model.elements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class Element extends Observable implements ActionListener {

    //Attributes
    private String name;

    //Methods
    public Element() {
        this.setName("");
    }

    public Element(String name) {
        this.setName(name);
    }

    private void setName(String name) {
        this.name = name;
    }

    protected void init() {
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Element)) {
            return false;
        }
        if (other == this) {
            return true;
        }
        Element otherElement = (Element) other;
        return (this.getName().equals(otherElement.getName()));
    }

    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        setChanged();
        notifyObservers();
    }
}
