package model.elements;

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

    private void setName(String name) {
        this.name = name;
    }

    protected void init() {}

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other == this) {
            return true;
        }
        if (!(other instanceof Element)) {
            return false;
        }

        Element otherElement = (Element)other;
        return (this.name.equals(otherElement.name));
    }

    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }
}
