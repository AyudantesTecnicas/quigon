package model.elements;

public class IndexedElement {

    //Attributes
    private ComplexElement element;
    private String index;

    //Methods
    public IndexedElement(ComplexElement element) {
        this.element = element;
        this.index = "";
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public ComplexElement getElement() {
        //If does not have an index the element to update is the element itself
        if (this.index.isEmpty()) {
            return this.element;
        }

        //Else the element to update is an element of the element
        return this.element.getElementAt(this.index);
    }

}
