package com.rodrigovsilva.memorygame.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * This entity represents all information about a cards of the match.
 *
 * @author Rodrigo Silva
 */
@Entity
@Table(name = "match_card")
public class MatchCard implements Comparable<MatchCard> {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "position")
    @NotNull
    private Integer position;

    @NotNull
    @Column(name = "number")
    private Integer number;

    @ManyToOne(targetEntity = PlayerMatch.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "player_match_id", foreignKey = @ForeignKey(name = "FK_PLAYER_MATCH_TO_MATCH_CARD"))
    @NotNull
    private PlayerMatch playerMatch;


    public MatchCard() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public PlayerMatch getPlayerMatch() {
        return playerMatch;
    }

    public void setPlayerMatch(PlayerMatch playerMatch) {
        this.playerMatch = playerMatch;
    }


    @Override
    public int compareTo(MatchCard matchCard) {
        if (matchCard == null) return -1;
        return Integer.compare(this.getPosition(), matchCard.getPosition());
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchCard matchCard = (MatchCard) o;
        return Objects.equals(getPosition(), matchCard.getPosition()) &&
                Objects.equals(getNumber(), matchCard.getNumber());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getNumber());
    }

    public static final class Builder {
        private Long id;
        private Integer position;
        private Integer number;
        private PlayerMatch playerMatch;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder position(Integer position) {
            this.position = position;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder playerMatch(PlayerMatch playerMatch) {
            this.playerMatch = playerMatch;
            return this;
        }

        public MatchCard build() {
            MatchCard matchCard = new MatchCard();
            matchCard.setId(id);
            matchCard.setPosition(position);
            matchCard.setNumber(number);
            matchCard.setPlayerMatch(playerMatch);
            return matchCard;
        }
    }
}
