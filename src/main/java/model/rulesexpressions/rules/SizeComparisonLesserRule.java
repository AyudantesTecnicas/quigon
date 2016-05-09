package model.rulesexpressions.rules;

import model.elements.ComplexElement;
import model.elements.IndexedElement;
import model.rulesexpressions.expressions.RuleExpression;

public class SizeComparisonLesserRule extends RuleExpression {

    //Attributes
    private IndexedElement elementToCompare;

    //Methods
    public SizeComparisonLesserRule() {
        super();
        elementToCompare = null;
    }

    public void setElementToCompare(ComplexElement elementToCompare) {
        this.elementToCompare = new IndexedElement(elementToCompare);
    }

    public void setIndexToCompare(String indexToCompare) {
        if (this.elementToCompare != null) {
            this.elementToCompare.setIndex(indexToCompare);
        }
    }

    @Override
    protected Boolean validate() {
        Boolean equal = (this.elementToValidate.getElement().compareTo(this.elementToCompare.getElement()) == 0);
        Boolean less = (this.elementToValidate.getElement().compareTo(this.elementToCompare.getElement()) > 0);

        return (equal.equals(less));
    }

}
