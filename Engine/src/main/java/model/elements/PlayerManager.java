package model.elements;

import java.util.ArrayList;

public class PlayerManager extends ComplexElement {
    public Player currentPlayer;
    public ArrayList<Player> characters;

    public PlayerManager() {
        super("");
        characters = new ArrayList<>();
    }

    public void playerHasDesconect(String playerId) {
        Player player = getCharacter(playerId);
        if (player != null) {
            player.resetPlayer();
        }
    }

    private Player getCharacter(String id) {
        int characterIndex = Integer.parseInt(id);
        if (characters.size() < characterIndex) {
            return null;
        }
        return characters.get(characterIndex);
    }

    public String getName() {
        return this.currentPlayer.getName();
    }

    public int getNumberOfPlayers() {
        return characters.size();
    }

    public void addCharacter(Player character) {
        if (currentPlayer == null) {
            currentPlayer = character;
        }
        characters.add(character);
    }

    public Player updateCurrentCharacter(String command) {
        currentPlayer = getCharacter(command);
        return currentPlayer;
    }

    public void addState(Element state) {
        this.currentPlayer.addState(state);
    }

    public void removeState(Element state) {
        this.currentPlayer.removeState(state);
    }

    public void setContainerElement(ComplexElement containerElement) {
        if (this.currentPlayer != null) {
            this.currentPlayer.setContainerElement(containerElement);
        }
    }

    public Element getContainerElement() {
        return this.currentPlayer.getContainerElement();
    }

    public Boolean hasState(Element state) {
        return this.currentPlayer.hasState(state);
    }

    public Boolean hasContainerElement(ComplexElement containerElement) {
        return this.currentPlayer.hasContainerElement(containerElement);
    }

    public void setVisible(Boolean visible) {
        this.currentPlayer.setVisible(visible);
    }

    public Boolean getVisible() {
        return this.currentPlayer.getVisible();
    }

    public boolean equals(Object other) {
        return super.equals(other);
    }

    public int hashCode() {
        return this.currentPlayer.hashCode();
    }

}
