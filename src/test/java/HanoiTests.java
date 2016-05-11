import model.actions.ChangeContainerAction;
import model.actions.Move;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.AndExpression;
import model.rulesexpressions.expressions.OrExpression;
import model.rulesexpressions.rules.IsEmptyRule;
import model.rulesexpressions.rules.IsNotEmptyRule;
import model.rulesexpressions.rules.SizeComparisonLesserRule;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HanoiTests {

    @Test
    public void testHanoi() {

        //===================================================Stacks====================================================

        ComplexElement stack1 = new ComplexElement("Stack 1");
        ComplexElement stack2 = new ComplexElement("Stack 2");
        ComplexElement stack3 = new ComplexElement("Stack 3");

        //=======================================================Discs================================================

        ComplexElement disc1 = new ComplexElement("Disc 1");
        disc1.setSize(6);
        disc1.setContainerElement(stack1);

        ComplexElement disc2 = new ComplexElement("Disc 2");
        disc2.setSize(5);
        disc2.setContainerElement(stack1);

        ComplexElement disc3 = new ComplexElement("Disc 3");
        disc3.setSize(4);
        disc3.setContainerElement(stack1);

        ComplexElement disc4 = new ComplexElement("Disc 4");
        disc4.setSize(3);
        disc4.setContainerElement(stack1);

        ComplexElement disc5 = new ComplexElement("Disc 5");
        disc5.setSize(2);
        disc5.setContainerElement(stack1);

        ComplexElement disc6 = new ComplexElement("Disc 6");
        disc6.setSize(1);
        disc6.setContainerElement(stack1);

        //======================================================Rules==================================================
        //Not empty rules
        IsNotEmptyRule notEmptyRuleStack1 = new IsNotEmptyRule();
        notEmptyRuleStack1.setElementToValidate(stack1);

        IsNotEmptyRule notEmptyRuleStack2 = new IsNotEmptyRule();
        notEmptyRuleStack2.setElementToValidate(stack2);

        IsNotEmptyRule notEmptyRuleStack3 = new IsNotEmptyRule();
        notEmptyRuleStack3.setElementToValidate(stack3);

        //Empty rules
        IsEmptyRule emptyRuleStack1 = new IsEmptyRule();
        emptyRuleStack1.setElementToValidate(stack1);

        IsEmptyRule emptyRuleStack2 = new IsEmptyRule();
        emptyRuleStack2.setElementToValidate(stack2);

        IsEmptyRule emptyRuleStack3 = new IsEmptyRule();
        emptyRuleStack3.setElementToValidate(stack3);

        //=================================================Lesser rules================================================

        //From stack 1 to stack 2
        SizeComparisonLesserRule lesserRuleStack1Stack2 = new SizeComparisonLesserRule();
        lesserRuleStack1Stack2.setElementToValidate(stack1);
        lesserRuleStack1Stack2.setIndexToValidate("last");
        lesserRuleStack1Stack2.setElementToCompare(stack2);
        lesserRuleStack1Stack2.setIndexToCompare("last");

        //From stack 2 to stack 1
        SizeComparisonLesserRule lesserRuleStack2Stack1 = new SizeComparisonLesserRule();
        lesserRuleStack2Stack1.setElementToValidate(stack2);
        lesserRuleStack2Stack1.setIndexToValidate("last");
        lesserRuleStack2Stack1.setElementToCompare(stack1);
        lesserRuleStack2Stack1.setIndexToCompare("last");

        //From stack 1 to stack 3
        SizeComparisonLesserRule lesserRuleStack1Stack3 = new SizeComparisonLesserRule();
        lesserRuleStack1Stack3.setElementToValidate(stack1);
        lesserRuleStack1Stack3.setIndexToValidate("last");
        lesserRuleStack1Stack3.setElementToCompare(stack3);
        lesserRuleStack1Stack3.setIndexToCompare("last");

        //From stack 3 to stack 1
        SizeComparisonLesserRule lesserRuleStack3Stack1 = new SizeComparisonLesserRule();
        lesserRuleStack3Stack1.setElementToValidate(stack3);
        lesserRuleStack3Stack1.setIndexToValidate("last");
        lesserRuleStack3Stack1.setElementToCompare(stack1);
        lesserRuleStack3Stack1.setIndexToCompare("last");

        //From stack 2 to stack 3
        SizeComparisonLesserRule lesserRuleStack2Stack3 = new SizeComparisonLesserRule();
        lesserRuleStack2Stack3.setElementToValidate(stack2);
        lesserRuleStack2Stack3.setIndexToValidate("last");
        lesserRuleStack2Stack3.setElementToCompare(stack3);
        lesserRuleStack2Stack3.setIndexToCompare("last");

        //From stack 3 to stack 2
        SizeComparisonLesserRule lesserRuleStack3Stack2 = new SizeComparisonLesserRule();
        lesserRuleStack3Stack2.setElementToValidate(stack3);
        lesserRuleStack3Stack2.setIndexToValidate("last");
        lesserRuleStack3Stack2.setElementToCompare(stack2);
        lesserRuleStack3Stack2.setIndexToCompare("last");

        //================================================Logic rules=================================================

        //From stack 1 to stack 2
        OrExpression canPushFromStack1ToStack2 = new OrExpression();
        canPushFromStack1ToStack2.setLeftExpression(emptyRuleStack2);
        canPushFromStack1ToStack2.setRightExpression(lesserRuleStack1Stack2);

        AndExpression canMoveFromStack1ToStack2 = new AndExpression();
        canMoveFromStack1ToStack2.setLeftExpression(notEmptyRuleStack1);
        canMoveFromStack1ToStack2.setRightExpression(canPushFromStack1ToStack2);

        //From stack 2 to stack 1
        OrExpression canPushFromStack2ToStack1 = new OrExpression();
        canPushFromStack2ToStack1.setLeftExpression(emptyRuleStack1);
        canPushFromStack2ToStack1.setRightExpression(lesserRuleStack2Stack1);

        AndExpression canMoveFromStack2ToStack1 = new AndExpression();
        canMoveFromStack2ToStack1.setLeftExpression(notEmptyRuleStack2);
        canMoveFromStack2ToStack1.setRightExpression(canPushFromStack2ToStack1);

        //From stack 1 to stack 3
        OrExpression canPushFromStack1ToStack3 = new OrExpression();
        canPushFromStack1ToStack3.setLeftExpression(emptyRuleStack3);
        canPushFromStack1ToStack3.setRightExpression(lesserRuleStack1Stack3);

        AndExpression canMoveFromStack1ToStack3 = new AndExpression();
        canMoveFromStack1ToStack3.setLeftExpression(notEmptyRuleStack1);
        canMoveFromStack1ToStack3.setRightExpression(canPushFromStack1ToStack3);

        //From stack 3 to stack 1
        OrExpression canPushFromStack3ToStack1 = new OrExpression();
        canPushFromStack3ToStack1.setLeftExpression(emptyRuleStack1);
        canPushFromStack3ToStack1.setRightExpression(lesserRuleStack3Stack1);

        AndExpression canMoveFromStack3ToStack1 = new AndExpression();
        canMoveFromStack3ToStack1.setLeftExpression(notEmptyRuleStack3);
        canMoveFromStack3ToStack1.setRightExpression(canPushFromStack3ToStack1);

        //From stack 2 to stack 3
        OrExpression canPushFromStack2ToStack3 = new OrExpression();
        canPushFromStack2ToStack3.setLeftExpression(emptyRuleStack3);
        canPushFromStack2ToStack3.setRightExpression(lesserRuleStack2Stack3);

        AndExpression canMoveFromStack2ToStack3 = new AndExpression();
        canMoveFromStack2ToStack3.setLeftExpression(notEmptyRuleStack2);
        canMoveFromStack2ToStack3.setRightExpression(canPushFromStack2ToStack3);

        //From stack 3 to stack 2
        OrExpression canPushFromStack3ToStack2 = new OrExpression();
        canPushFromStack3ToStack2.setLeftExpression(emptyRuleStack2);
        canPushFromStack3ToStack2.setRightExpression(lesserRuleStack3Stack2);

        AndExpression canMoveFromStack3ToStack2 = new AndExpression();
        canMoveFromStack3ToStack2.setLeftExpression(notEmptyRuleStack3);
        canMoveFromStack3ToStack2.setRightExpression(canPushFromStack3ToStack2);

        //================================================Actions===================================================

        //From stack 1 to stack 2
        ChangeContainerAction actionToMoveFromStack1ToStack2 = new ChangeContainerAction();
        actionToMoveFromStack1ToStack2.setElementToUpdate(stack1);
        actionToMoveFromStack1ToStack2.setIndex("last");
        actionToMoveFromStack1ToStack2.addItemToUpdate(stack2);

        //From stack 2 to stack 1
        ChangeContainerAction actionToMoveFromStack2ToStack1 = new ChangeContainerAction();
        actionToMoveFromStack2ToStack1.setElementToUpdate(stack2);
        actionToMoveFromStack2ToStack1.setIndex("last");
        actionToMoveFromStack2ToStack1.addItemToUpdate(stack1);

        //From stack 1 to stack 3
        ChangeContainerAction actionToMoveFromStack1ToStack3 = new ChangeContainerAction();
        actionToMoveFromStack1ToStack3.setElementToUpdate(stack1);
        actionToMoveFromStack1ToStack3.setIndex("last");
        actionToMoveFromStack1ToStack3.addItemToUpdate(stack3);

        //From stack 3 to stack 1
        ChangeContainerAction actionToMoveFromStack3ToStack1 = new ChangeContainerAction();
        actionToMoveFromStack3ToStack1.setElementToUpdate(stack3);
        actionToMoveFromStack3ToStack1.setIndex("last");
        actionToMoveFromStack3ToStack1.addItemToUpdate(stack1);

        //From stack 2 to stack 3
        ChangeContainerAction actionToMoveFromStack2ToStack3 = new ChangeContainerAction();
        actionToMoveFromStack2ToStack3.setElementToUpdate(stack2);
        actionToMoveFromStack2ToStack3.setIndex("last");
        actionToMoveFromStack2ToStack3.addItemToUpdate(stack3);

        //From stack 3 to stack 2
        ChangeContainerAction actionToMoveFromStack3ToStack2 = new ChangeContainerAction();
        actionToMoveFromStack3ToStack2.setElementToUpdate(stack3);
        actionToMoveFromStack3ToStack2.setIndex("last");
        actionToMoveFromStack3ToStack2.addItemToUpdate(stack2);

        //===============================================Moves=====================================================

        //From stack 1 to stack 2
        Move moveFromStack1toStack2 = new Move("Move top stack 1");
        moveFromStack1toStack2.addAction(actionToMoveFromStack1ToStack2);
        moveFromStack1toStack2.setRules(canMoveFromStack1ToStack2);

        stack2.addMove(moveFromStack1toStack2);

        //From stack 3 to stack 2
        Move moveFromStack3toStack2 = new Move("Move top stack 3");
        moveFromStack3toStack2.addAction(actionToMoveFromStack3ToStack2);
        moveFromStack3toStack2.setRules(canMoveFromStack3ToStack2);

        stack2.addMove(moveFromStack3toStack2);

        //From stack 2 to stack 1
        Move moveFromStack2toStack1 = new Move("Move top stack 2");
        moveFromStack2toStack1.addAction(actionToMoveFromStack2ToStack1);
        moveFromStack2toStack1.setRules(canMoveFromStack2ToStack1);

        stack1.addMove(moveFromStack2toStack1);

        //From stack 3 to stack 1
        Move moveFromStack3toStack1 = new Move("Move top stack 3");
        moveFromStack3toStack1.addAction(actionToMoveFromStack3ToStack1);
        moveFromStack3toStack1.setRules(canMoveFromStack3ToStack1);

        stack1.addMove(moveFromStack3toStack1);

        //From stack 1 to stack 3
        Move moveFromStack1toStack3 = new Move("Move top stack 1");
        moveFromStack1toStack3.addAction(actionToMoveFromStack1ToStack3);
        moveFromStack1toStack3.setRules(canMoveFromStack1ToStack3);

        stack3.addMove(moveFromStack1toStack3);

        //From stack 2 to stack 3
        Move moveFromStack2toStack3 = new Move("Move top stack 2");
        moveFromStack2toStack3.addAction(actionToMoveFromStack2ToStack3);
        moveFromStack2toStack3.setRules(canMoveFromStack2ToStack3);

        stack3.addMove(moveFromStack2toStack3);

        //==============================================Tests=====================================================

        assertTrue(stack2.execute("Move top stack 3").equals("Rule violated"));
        assertFalse(stack2.hasElements());
        assertFalse(disc6.getContainerElement().equals(stack2));
        assertTrue(stack2.execute("Move top stack 1").equals("Ok"));
        assertTrue(stack2.hasElements());
        assertTrue(disc6.getContainerElement().equals(stack2));
        assertTrue(stack2.execute("Move top stack 1").equals("Rule violated"));
        assertTrue(disc5.getContainerElement().equals(stack1));
        assertTrue(stack3.execute("Move top stack 1").equals("Ok"));
        assertTrue(disc5.getContainerElement().equals(stack3));
        assertTrue(stack2.hasElement(disc6));
        assertTrue(stack3.execute("Move top stack 2").equals("Ok"));
        assertTrue(disc6.getContainerElement().equals(stack3));
        assertFalse(stack2.hasElement(disc6));
    }

}
