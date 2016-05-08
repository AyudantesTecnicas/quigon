package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Action implements IExecutable {

    //Attributes
    private List<Element> elementsOfElementToUpdate;
    ComplexElement elementToUpdate;

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
        this.elementToUpdate = elementToUpdate;
    }

    @Override
    public void execute() {

        for (Element anElementsOfElementToUpdate : this.elementsOfElementToUpdate) {
            this.applyChanges(anElementsOfElementToUpdate);
        }
    }

    private void initAction() {
        this.elementsOfElementToUpdate = new ArrayList<>();
        this.setElementToUpdate(null);
    }

    protected abstract void applyChanges(Element element);

}
