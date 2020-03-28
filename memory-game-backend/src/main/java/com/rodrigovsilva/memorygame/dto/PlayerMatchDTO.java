package com.rodrigovsilva.memorygame.dto;

import java.util.Calendar;
import java.util.List;

/**
 * Data transfer object for all player matches.
 */
public class PlayerMatchDTO {

    private Long id;

    private PlayerDTO player;

    private Integer totalCards;

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

    public Integer getTotalCards() {
        return totalCards;
    }

    public List<MatchCardDTO> matchCards;

    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public List<MatchCardDTO> getMatchCards() {
        return matchCards;
    }

    public void setMatchCards(List<MatchCardDTO> matchCards) {
        this.matchCards = matchCards;
    }

    public static final class Builder {
        private Long id;
        private PlayerDTO player;
        private Integer totalCards;
        private Calendar createdAt;
        public List<MatchCardDTO> matchCards;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder player(PlayerDTO player) {
            this.player = player;
            return this;
        }

        public Builder totalCards(Integer totalCards) {
            this.totalCards = totalCards;
            return this;
        }

        public Builder createdAt(Calendar createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder matchCards(List<MatchCardDTO> matchCards) {
            this.matchCards = matchCards;
            return this;
        }

        public PlayerMatchDTO build() {
            PlayerMatchDTO playerMatchDTO = new PlayerMatchDTO();
            playerMatchDTO.setId(id);
            playerMatchDTO.setPlayer(player);
            playerMatchDTO.setTotalCards(totalCards);
            playerMatchDTO.setCreatedAt(createdAt);
            playerMatchDTO.setMatchCards(matchCards);
            return playerMatchDTO;
        }
    }
}
