package games;

import creation.GameBuilder;
import model.actions.*;
import model.elements.ComplexElement;
import model.ruleExpressions.rules.HasContainerRule;

public final class FetchQuest extends GameBuilder {

    public static final String gameDescription = "Don't take it!";

    public FetchQuest() {
        gameName = "FetchQuest";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {
        //Create elements
        ComplexElement room = createAndAddElement("room", null, null);
        ComplexElement stick = createAndAddElement("stick", room, null);
        ComplexElement character = createAndAddElement("character", room, null);
        game.character = character;

        //Create rules for movements
        HasContainerRule stickIsInRoom = checkContainerRule(stick, room, "There's no stick in room");
        HasContainerRule victoryCondition = checkContainerRule(stick, character, "it's a pitty");

        //Create actions
        Action addStickToCharacter = buildChangeContainerAction(character, stick);

        //Create Moves
        Move pickStick = moveWithActionsAndRules("pick", addStickToCharacter, stickIsInRoom, "You won the game!");

        //Inject moves to elements
        stick.addMove(pickStick);

        //Set victory condition
        game.setVictoryCondition(victoryCondition);
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, "pick");
    }

}
