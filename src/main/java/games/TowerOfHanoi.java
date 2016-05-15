package games;

import creation.GameBuilder;
import games.constants.TowerOfHanoiConstants;
import model.actions.Action;
import model.actions.Move;
import model.elements.ComplexElement;
import model.rulesexpressions.expressions.AndExpression;
import model.rulesexpressions.expressions.OrExpression;
import model.rulesexpressions.rules.IsEmptyRule;
import model.rulesexpressions.rules.IsNotEmptyRule;
import model.rulesexpressions.rules.SizeComparisonLesserRule;

@SuppressWarnings("CPD-START")
public final class TowerOfHanoi extends GameBuilder {

    public static final String gameDescription = "There are tree stacks with plates on this game.";

    //stacks
    private ComplexElement stack1;
    private ComplexElement stack2;
    private ComplexElement stack3;

    //not empty rules
    private IsNotEmptyRule notEmptyRuleStack1;
    private IsNotEmptyRule notEmptyRuleStack2;
    private IsNotEmptyRule notEmptyRuleStack3;

    //Empty rules
    private IsEmptyRule emptyRuleStack1;
    private IsEmptyRule emptyRuleStack2;
    private IsEmptyRule emptyRuleStack3;

    //Lesser rules
    private SizeComparisonLesserRule lesserRuleStack1Stack2;
    private SizeComparisonLesserRule lesserRuleStack2Stack1;
    private SizeComparisonLesserRule lesserRuleStack1Stack3;
    private SizeComparisonLesserRule lesserRuleStack3Stack1;
    private SizeComparisonLesserRule lesserRuleStack2Stack3;
    private SizeComparisonLesserRule lesserRuleStack3Stack2;

    //Logic rules
    private AndExpression canMoveFromStack1ToStack2;
    private AndExpression canMoveFromStack2ToStack1;
    private AndExpression canMoveFromStack1ToStack3;
    private AndExpression canMoveFromStack3ToStack1;
    private AndExpression canMoveFromStack2ToStack3;
    private AndExpression canMoveFromStack3ToStack2;

    //Actions
    private Action actionToMoveFromStack1ToStack2;
    private Action actionToMoveFromStack2ToStack1;
    private Action actionToMoveFromStack1ToStack3;
    private Action actionToMoveFromStack3ToStack1;
    private Action actionToMoveFromStack2ToStack3;
    private Action actionToMoveFromStack3ToStack2;

    public TowerOfHanoi() {
        gameName = "TowerOfHanoi";
    }

    public void setElements() {
        createElements();
        createRules();
        createActions();
        createComplexRules();
        createMoves();
        createVictoryRules();
    }

    private void createMoves() {
        Move moveFromStack1toStack2 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack1,
                this.actionToMoveFromStack1ToStack2, this.canMoveFromStack1ToStack2, TowerOfHanoiConstants.moved);
        this.stack2.addMove(moveFromStack1toStack2);

        Move moveFromStack2toStack1 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack2,
                this.actionToMoveFromStack2ToStack1, this.canMoveFromStack2ToStack1, TowerOfHanoiConstants.moved);
        this.stack1.addMove(moveFromStack2toStack1);

        Move moveFromStack1toStack3 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack1,
                this.actionToMoveFromStack1ToStack3, this.canMoveFromStack1ToStack3, TowerOfHanoiConstants.moved);
        this.stack3.addMove(moveFromStack1toStack3);

        Move moveFromStack3toStack1 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack3,
                this.actionToMoveFromStack3ToStack1, this.canMoveFromStack3ToStack1, TowerOfHanoiConstants.moved);
        this.stack1.addMove(moveFromStack3toStack1);

        Move moveFromStack2toStack3 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack2,
                this.actionToMoveFromStack2ToStack3, this.canMoveFromStack2ToStack3, TowerOfHanoiConstants.moved);
        this.stack3.addMove(moveFromStack2toStack3);

        Move moveFromStack3toStack2 = moveWithActionsAndRules(TowerOfHanoiConstants.moveStack3,
                this.actionToMoveFromStack3ToStack2, this.canMoveFromStack3ToStack2, TowerOfHanoiConstants.moved);
        this.stack2.addMove(moveFromStack3toStack2);
    }

    private void createActions() {
        this.actionToMoveFromStack1ToStack2 = buildChangeContainerAction(this.stack1, TowerOfHanoiConstants.last,
                this.stack2);
        this.actionToMoveFromStack2ToStack1 = buildChangeContainerAction(this.stack2, TowerOfHanoiConstants.last,
                this.stack1);
        this.actionToMoveFromStack1ToStack3 = buildChangeContainerAction(this.stack1, TowerOfHanoiConstants.last,
                this.stack3);
        this.actionToMoveFromStack3ToStack1 = buildChangeContainerAction(this.stack3, TowerOfHanoiConstants.last,
                this.stack1);
        this.actionToMoveFromStack2ToStack3 = buildChangeContainerAction(this.stack2, TowerOfHanoiConstants.last,
                this.stack3);
        this.actionToMoveFromStack3ToStack2 = buildChangeContainerAction(this.stack3, TowerOfHanoiConstants.last,
                this.stack2);
    }

    private void createRulesForPushFromStack1ToStack2() {
        OrExpression canPushFromStack1ToStack2 = new OrExpression();
        canPushFromStack1ToStack2.setLeftExpression(this.emptyRuleStack2);
        canPushFromStack1ToStack2.setRightExpression(this.lesserRuleStack1Stack2);

        this.canMoveFromStack1ToStack2 = new AndExpression();
        this.canMoveFromStack1ToStack2.setLeftExpression(this.notEmptyRuleStack1);
        this.canMoveFromStack1ToStack2.setRightExpression(canPushFromStack1ToStack2);
    }

    private void createRulesForPushFromStack2ToStack1() {
        OrExpression canPushFromStack2ToStack1 = new OrExpression();
        canPushFromStack2ToStack1.setLeftExpression(this.emptyRuleStack1);
        canPushFromStack2ToStack1.setRightExpression(this.lesserRuleStack2Stack1);

        this.canMoveFromStack2ToStack1 = new AndExpression();
        this.canMoveFromStack2ToStack1.setLeftExpression(this.notEmptyRuleStack2);
        this.canMoveFromStack2ToStack1.setRightExpression(canPushFromStack2ToStack1);
    }

    private void createRulesForPushFromStack1ToStack3() {
        OrExpression canPushFromStack1ToStack3 = new OrExpression();
        canPushFromStack1ToStack3.setLeftExpression(this.emptyRuleStack3);
        canPushFromStack1ToStack3.setRightExpression(this.lesserRuleStack1Stack3);

        this.canMoveFromStack1ToStack3 = new AndExpression();
        this.canMoveFromStack1ToStack3.setLeftExpression(this.notEmptyRuleStack1);
        this.canMoveFromStack1ToStack3.setRightExpression(canPushFromStack1ToStack3);
    }

    private void createRulesForPushFromStack3ToStack1() {
        OrExpression canPushFromStack3ToStack1 = new OrExpression();
        canPushFromStack3ToStack1.setLeftExpression(this.emptyRuleStack1);
        canPushFromStack3ToStack1.setRightExpression(this.lesserRuleStack3Stack1);

        this.canMoveFromStack3ToStack1 = new AndExpression();
        this.canMoveFromStack3ToStack1.setLeftExpression(this.notEmptyRuleStack3);
        this.canMoveFromStack3ToStack1.setRightExpression(canPushFromStack3ToStack1);
    }

    private void createRulesForPushFromStack2ToStack3() {
        OrExpression canPushFromStack2ToStack3 = new OrExpression();
        canPushFromStack2ToStack3.setLeftExpression(this.emptyRuleStack3);
        canPushFromStack2ToStack3.setRightExpression(this.lesserRuleStack2Stack3);

        this.canMoveFromStack2ToStack3 = new AndExpression();
        this.canMoveFromStack2ToStack3.setLeftExpression(this.notEmptyRuleStack2);
        this.canMoveFromStack2ToStack3.setRightExpression(canPushFromStack2ToStack3);
    }

    private void createRulesForPushFromStack3ToStack2() {
        OrExpression canPushFromStack3ToStack2 = new OrExpression();
        canPushFromStack3ToStack2.setLeftExpression(this.emptyRuleStack2);
        canPushFromStack3ToStack2.setRightExpression(this.lesserRuleStack3Stack2);

        this.canMoveFromStack3ToStack2 = new AndExpression();
        this.canMoveFromStack3ToStack2.setLeftExpression(this.notEmptyRuleStack3);
        this.canMoveFromStack3ToStack2.setRightExpression(canPushFromStack3ToStack2);
    }

    private void createComplexRules() {
        this.createRulesForPushFromStack1ToStack2();
        this.createRulesForPushFromStack2ToStack1();
        this.createRulesForPushFromStack1ToStack3();
        this.createRulesForPushFromStack3ToStack1();
        this.createRulesForPushFromStack2ToStack3();
        this.createRulesForPushFromStack3ToStack2();
    }

    private void createVictoryRules() {
        AndExpression stack1andStack2Empty = new AndExpression();
        stack1andStack2Empty.setLeftExpression(this.emptyRuleStack1);
        stack1andStack2Empty.setRightExpression(this.emptyRuleStack2);

        AndExpression stack1andStack3Empty = new AndExpression();
        stack1andStack3Empty.setLeftExpression(this.emptyRuleStack1);
        stack1andStack3Empty.setRightExpression(this.emptyRuleStack3);

        OrExpression victoryRule = new OrExpression();
        victoryRule.setRightExpression(stack1andStack2Empty);
        victoryRule.setLeftExpression(stack1andStack3Empty);

        game.setVictoryCondition(victoryRule);
    }

    private void createRules() {
        this.notEmptyRuleStack1 = checkIsNotEmptyRule(this.stack1, TowerOfHanoiConstants.noDisc);
        this.notEmptyRuleStack2 = checkIsNotEmptyRule(this.stack2, TowerOfHanoiConstants.noDisc);
        this.notEmptyRuleStack3 = checkIsNotEmptyRule(this.stack3, TowerOfHanoiConstants.noDisc);

        this.emptyRuleStack1 = checkIsEmptyRule(this.stack1, "");
        this.emptyRuleStack2 = checkIsEmptyRule(this.stack2, "");
        this.emptyRuleStack3 = checkIsEmptyRule(this.stack3, "");

        this.lesserRuleStack1Stack2 = checkSizeComparisonLesserRule(this.stack1, "last", this.stack2, "last",
                TowerOfHanoiConstants.discIsGreater);
        this.lesserRuleStack2Stack1 = checkSizeComparisonLesserRule(this.stack2, "last", this.stack1, "last",
                TowerOfHanoiConstants.discIsGreater);
        this.lesserRuleStack1Stack3 = checkSizeComparisonLesserRule(this.stack1, "last", this.stack3, "last",
                TowerOfHanoiConstants.discIsGreater);
        this.lesserRuleStack3Stack1 = checkSizeComparisonLesserRule(this.stack3, "last", this.stack1, "last",
                TowerOfHanoiConstants.discIsGreater);
        this.lesserRuleStack2Stack3 = checkSizeComparisonLesserRule(this.stack2, "last", this.stack3, "last",
                TowerOfHanoiConstants.discIsGreater);
        this.lesserRuleStack3Stack2 = checkSizeComparisonLesserRule(this.stack3, "last", this.stack2, "last",
                TowerOfHanoiConstants.discIsGreater);
    }

    private void createElements() {
        //Stacks
        this.stack1 = createAndAddElement(TowerOfHanoiConstants.stack1, null, null);
        this.stack2 = createAndAddElement(TowerOfHanoiConstants.stack2, null, null);
        this.stack3 = createAndAddElement(TowerOfHanoiConstants.stack3, null, null);

        //Discs
        createAndAddElement(TowerOfHanoiConstants.disc6, stack1, null, 6);
        createAndAddElement(TowerOfHanoiConstants.disc5, stack1, null, 5);
        createAndAddElement(TowerOfHanoiConstants.disc4, stack1, null, 4);
        createAndAddElement(TowerOfHanoiConstants.disc3, stack1, null, 3);
        createAndAddElement(TowerOfHanoiConstants.disc2, stack1, null, 2);
        createAndAddElement(TowerOfHanoiConstants.disc1, stack1, null, 1);

        //Character
        game.character = null;
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, TowerOfHanoiConstants.moveStack1);
        createAndAddSuportedAction(1, TowerOfHanoiConstants.moveStack2);
        createAndAddSuportedAction(1, TowerOfHanoiConstants.moveStack3);
    }
}
