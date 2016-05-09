package model.actions;

import model.elements.ComplexElement;
import model.elements.Element;
import model.elements.IndexedElement;
import model.ruleExpressions.expressions.IExpression;

import java.util.ArrayList;
import java.util.List;

public abstract class Action implements IExecutable {

    //Attributes
    protected List<Element> elementsOfElementToUpdate;
    protected IndexedElement elementToUpdate;
    protected String index;
    protected IExpression rules;

    public Action() {
        this.elementsOfElementToUpdate = new ArrayList<>();
        this.elementToUpdate = null;
        this.index = "";
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
        for (Element element : this.elementsOfElementToUpdate) {
            if (this.rules.interpret() || this.rules == null) {
                this.applyChanges(element);
            }
        }
    }

    protected abstract void applyChanges(Element element);

}
