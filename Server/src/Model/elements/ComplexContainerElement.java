package Model.elements;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by metro on 03/05/16.
 */
public class ComplexContainerElement extends ComplexElement {

    //Attributes
    private List<ComplexElement> elements;

    //Methods
    public ComplexContainerElement() {
        super();
        this.initComplexContainerElement();
    }

    public ComplexContainerElement(String name) {
        super(name);
        this.initComplexContainerElement();
    }

    public void addElement(ComplexElement element) {
        if (element != null) {
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
        Integer intIndex = 0;

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

    private void initComplexContainerElement() {
        this.elements = new ArrayList<>();
    }

}
