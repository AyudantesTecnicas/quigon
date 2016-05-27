import creation.GameBuilderImp;
import model.actions.*;
import model.elements.ComplexElement;
import model.rulesexpressions.rules.HasContainerRule;

public final class FetchQuestBuilder extends GameBuilderImp {

    private FetchQuestConstants constants = new FetchQuestConstants();

    public FetchQuestBuilder() {
        gameName = "FetchQuest";
        gameDescription = "Don't take it!";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {
        //Create elements
        ComplexElement room = createAndAddElement(constants.room, null, null);
        ComplexElement stick = createAndAddElement(constants.stick, room, null);
        ComplexElement character = createAndAddElement(constants.character, room, null);
        game.character = character;
        //Create rules for movements
        HasContainerRule stickIsInRoom = checkContainerRule(stick, room, constants.noStick);
        HasContainerRule victoryCondition = checkContainerRule(stick, character, constants.notWon);
        //Create actions
        Action addStickToCharacter = buildChangeContainerAction(character, stick);
        //Create Moves
        Move pickStick = moveWithActionsAndRules(constants.pick, addStickToCharacter, stickIsInRoom, constants.pickStick);
        //Inject moves to elements
        stick.addMove(pickStick);
        //Set victory condition
        game.setVictoryCondition(victoryCondition);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
    }

}
