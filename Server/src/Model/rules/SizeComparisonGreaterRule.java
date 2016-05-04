package Model.rules;

import Model.elements.ComplexElement;

public class SizeComparisonGreaterRule extends RuleExpression {

    @Override
    protected Boolean validate() {
        ComplexElement elementToCompare = (ComplexElement)(this.elementOfElementToValidate);
        return ( (elementToCompare.compareTo(this.elementToValidate) == 0) && (elementToCompare.compareTo(this.elementToValidate) < 0) );
    }

}
