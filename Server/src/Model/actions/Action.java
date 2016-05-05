package Model.actions;

import Model.elements.ComplexElement;
import Model.elements.Element;
import Model.elements.IndexedElement;

import java.util.ArrayList;
import java.util.List;

public abstract class Action implements IExecutable {

    //Attributes
    protected List<Element> elementsOfElementToUpdate;
    protected IndexedElement elementToUpdate;
    protected String index;

    public Action() {
        this.initAction();
    }

    public void addItemToUpdate(Element elementOfElementToUpdate) {
        if (elementOfElementToUpdate != null) {
            if (!this.elementsOfElementToUpdate.contains(elementOfElementToUpdate)) {
                this.elementsOfElementToUpdate.add(elementOfElementToUpdate);
            }
        }

    }

    public void setElementToUpdate(ComplexElement elementToUpdate) {
        this.elementToUpdate = new IndexedElement(elementToUpdate);
    }

    public void setIndex(String index) {
        this.elementToUpdate.setIndex(index);
    }

    @Override
    public void execute() {
        for (Element element : this.elementsOfElementToUpdate) {
            this.applyChanges(element);
        }
    }

    private void initAction() {
        this.elementsOfElementToUpdate = new ArrayList<>();
        this.elementToUpdate = null;
        this.index = "";
    }

    protected abstract void applyChanges(Element element);

}
