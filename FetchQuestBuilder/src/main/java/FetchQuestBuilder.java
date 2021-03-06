import creation.GameBuilderImp;
import model.actions.*;
import model.elements.ComplexElement;
import model.elements.Player;
import model.rulesexpressions.rules.HasContainerRule;

import java.util.ArrayList;

public final class FetchQuestBuilder extends GameBuilderImp {

    private FetchQuestConstants constants = new FetchQuestConstants();
    private ArrayList<Player> characters;

    public FetchQuestBuilder() {
        gameName = "FetchQuest";
        gameDescription = "Don't take it!";
    }

    @SuppressWarnings("CPD-START")
    public void setElements() {
        //Create elements
        ComplexElement room = createAndAddElement(constants.room, null, null);
        ComplexElement stick = createAndAddElement(constants.stick, room, null);

        characters = new ArrayList<>();
        for (int i = 0; i < constants.numberOfPlayers; i++) {
            characters.add(createAndAddPlayer("character" + i, room, null));
        }
        game.playerManager.characters = characters;

        //Create rules for movements
        HasContainerRule stickIsInRoom = checkContainerRule(stick, room, constants.noStick);

        Move pickStick = new Move(constants.pick);
        pickStick.setRules(stickIsInRoom);
        pickStick.setResultMessage(constants.pickStick);

        for (Player character: game.playerManager.characters) {
            Action addStickToCharacter = buildChangeContainerAction(stick, character);
            addStickToCharacter.setRules(checkEqualRule(game.playerManager,character,"not current character"));
            pickStick.addAction(addStickToCharacter);
        }

/*        //Create actions
        Action addStickToCharacter = buildChangeContainerAction(game.currentPlayer, stick);
        //Create Moves
        Move pickStick = moveWithActionsAndRules(constants.pick, addStickToCharacter, stickIsInRoom, constants.pickStick);
*/        //Inject moves to elements
        stick.addMove(pickStick);
        //Set victory condition
        for (Player character:characters) {
            character.setVictoryCondition(checkContainerRule(stick, character, constants.notWon));
        }
    }

    @SuppressWarnings("CPD-END")
    public void setActions() {
        createAndAddSuportedAction(1, constants.pick);
    }

}
