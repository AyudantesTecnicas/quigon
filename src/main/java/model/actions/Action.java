package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.IndexedElement;
import model.rulesexpressions.expressions.IExpression;

import java.util.ArrayList;
import java.util.List;

public abstract class Action implements IExecutable {

    //Attributes
    private List<Element> elementsOfElementToUpdate;
    IndexedElement elementToUpdate;
    protected IExpression rules;

    public Action() {
        this.elementsOfElementToUpdate = new ArrayList<>();
        this.elementToUpdate = null;
        this.rules = null;
    }

    public void addItemToUpdate(Element elementOfElementToUpdate) {
        if (elementOfElementToUpdate != null) {
            if (!this.elementsOfElementToUpdate.contains(elementOfElementToUpdate)) {
                this.elementsOfElementToUpdate.add(elementOfElementToUpdate);
            }
        }
    }

    public void setRules(IExpression rules) {
        this.rules = rules;
    }

    public void setElementToUpdate(ComplexElement elementToUpdate) {
        this.elementToUpdate = new IndexedElement(elementToUpdate);
    }

    public void setIndex(String index) {
        this.elementToUpdate.setIndex(index);
    }

    @Override
    public void execute() {
        this.elementsOfElementToUpdate.stream().filter(element -> this.rules == null || this.rules.interpret()).forEach(this::applyChanges);
    }

    protected abstract void applyChanges(Element element);

}
