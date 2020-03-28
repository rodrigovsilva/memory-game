package com.rodrigovsilva.memorygame.dto;

import java.util.Calendar;

/**
 * Data transfer object for all player matches.
 */
public class PlayerMatchDTO {

    private Long id;

    private PlayerDTO player;

    private Long turns;

    private Long totalCards;

    private Calendar createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PlayerDTO getPlayer() {
        return player;
    }

    public void setPlayer(PlayerDTO player) {
        this.player = player;
    }

    public Long getTurns() {
        return turns;
    }

    public void setTurns(Long turns) {
        this.turns = turns;
    }

    public Long getTotalCards() {
        return totalCards;
    }

    public void setTotalCards(Long totalCards) {
        this.totalCards = totalCards;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Inner builder for player matches.
     */
    public static final class PlayerMatchDTOBuilder {
        private Long id;
        private PlayerDTO player;
        private Long turns;
        private Long totalCards;
        private Calendar createdAt;

        private PlayerMatchDTOBuilder() {
        }

        public static PlayerMatchDTOBuilder builder() {
            return new PlayerMatchDTOBuilder();
        }

        public PlayerMatchDTOBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public PlayerMatchDTOBuilder withPlayer(PlayerDTO player) {
            this.player = player;
            return this;
        }

        public PlayerMatchDTOBuilder withTurns(Long turns) {
            this.turns = turns;
            return this;
        }

        public PlayerMatchDTOBuilder withTotalCards(Long totalCards) {
            this.totalCards = totalCards;
            return this;
        }

        public PlayerMatchDTOBuilder withCreatedAt(Calendar createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public PlayerMatchDTO build() {
            PlayerMatchDTO playerMatchDTO = new PlayerMatchDTO();
            playerMatchDTO.setId(id);
            playerMatchDTO.setPlayer(player);
            playerMatchDTO.setTurns(turns);
            playerMatchDTO.setTotalCards(totalCards);
            playerMatchDTO.setCreatedAt(createdAt);
            return playerMatchDTO;
        }
    }
}
