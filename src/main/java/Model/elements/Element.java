package model.elements;

/**
 * Created by metro on 27/04/16.
 */
public class Element {

    //Attributes
    private String name;

    //Methods
    public Element() {
        this.setName("");
    }

    public Element(String name) {
        this.setName(name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Element))return false;
        Element otherElement = (Element)other;
        return (this.name.equals(otherElement.name));
    }

}
