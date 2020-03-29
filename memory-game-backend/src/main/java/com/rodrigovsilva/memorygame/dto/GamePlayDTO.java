package com.rodrigovsilva.memorygame.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Data transfer object for game play information
 */
public class GamePlayDTO implements Serializable {

    private PlayerMatchDTO playerMatch;

    private List<Integer> selectedCards;

    public PlayerMatchDTO getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(PlayerMatchDTO playerMatch) {
        this.playerMatch = playerMatch;
    }

    public List<Integer> getSelectedCards() {
        return selectedCards;
    }

    public void setSelectedCards(List<Integer> selectedCards) {
        this.selectedCards = selectedCards;
    }
}
