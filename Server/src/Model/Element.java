package Model;

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

}
