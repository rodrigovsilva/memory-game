package com.rodrigovsilva.memorygame.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.*;

/**
 * Data transfer object for all player matches.
 */
public class PlayerMatchDTO implements Serializable {

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

    public Set<MatchCardDTO> matchCards;

    public Boolean victory;

    public PlayerMatchDTO() {
        matchCards = new TreeSet<MatchCardDTO>((t, t1) -> t.getPosition().compareTo(t1.getPosition()));
    }

    public void setTotalCards(Integer totalCards) {
        this.totalCards = totalCards;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Set<MatchCardDTO> getMatchCards() {
        return matchCards;
    }

    public void setMatchCards(Set<MatchCardDTO> matchCards) {
        this.matchCards = new TreeSet<>((t, t1) -> t.getPosition().compareTo(t1.getPosition()));
        if (matchCards != null) {
            this.matchCards.addAll(matchCards);
        }
    }

    public Boolean getVictory() {
        return victory;
    }

    public void setVictory(Boolean victory) {
        this.victory = victory;
    }

    public static final class Builder {
        private Long id;
        private PlayerDTO player;
        private Integer totalCards;
        private Calendar createdAt;
        public Set<MatchCardDTO> matchCards;
        public Boolean victory;

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

        public Builder matchCards(Set<MatchCardDTO> matchCards) {
            this.matchCards = matchCards;
            return this;
        }

        public Builder victory(Boolean victory) {
            this.victory = victory;
            return this;
        }

        public PlayerMatchDTO build() {
            PlayerMatchDTO playerMatchDTO = new PlayerMatchDTO();
            playerMatchDTO.setId(id);
            playerMatchDTO.setPlayer(player);
            playerMatchDTO.setTotalCards(totalCards);
            playerMatchDTO.setCreatedAt(createdAt);
            playerMatchDTO.setMatchCards(matchCards);
            playerMatchDTO.setVictory(victory);
            return playerMatchDTO;
        }
    }
}
