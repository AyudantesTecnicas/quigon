package Model.ruleExpressions.rules;

import Model.elements.ComplexElement;
import Model.elements.IndexedElement;
import Model.ruleExpressions.expressions.RuleExpression;

public class SizeComparisonGreaterRule extends RuleExpression {

    //Attributes
    private IndexedElement elementToCompare;

    //Methods
    public SizeComparisonGreaterRule() {
        super();
        elementToCompare = null;
    }

    public void setElementToCompare(ComplexElement elementToCompare) {
        this.elementToCompare = new IndexedElement(elementToCompare);
    }

    public void setIndexToCompare(String indexToCompare) {
        this.elementToCompare.setIndex(indexToCompare);
    }

    @Override
    protected Boolean validate() {
        Boolean equal = (this.elementToValidate.getElement().compareTo(this.elementToCompare.getElement()) == 0);
        Boolean less = (this.elementToValidate.getElement().compareTo(this.elementToCompare.getElement()) < 0);
        return (equal && less);
    }

}
