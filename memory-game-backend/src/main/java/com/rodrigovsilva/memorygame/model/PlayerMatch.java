package com.rodrigovsilva.memorygame.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rodrigovsilva.memorygame.dto.MatchCardDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * This entity represents all information about a player match.
 *
 * @author Rodrigo Silva
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt"},
        allowGetters = true)
@Table(name = "player_match")
public class PlayerMatch {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Player.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "player_id", foreignKey = @ForeignKey(name = "FK_PLAYER_TO_PLAYER_MATCH"))
    @NotNull
    private Player player;

    @NotNull
    @Column(name = "total_cards")
    private Integer totalCards;

    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Calendar createdAt;

    @OneToMany(targetEntity = MatchCard.class, fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "player_match_id", foreignKey = @ForeignKey(name = "FK_MATCH_CARD_TO_PLAYER_MATCH"))
    public Set<MatchCard> matchCards;

    @Column(name = "victory", nullable = true)
    public Boolean victory;

    public PlayerMatch() {
        this.matchCards = new TreeSet<>((t, t1) -> t.getPosition().compareTo(t1.getPosition()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Integer getTotalCards() {
        return totalCards;
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

    public Set<MatchCard> getMatchCards() {
        return matchCards;
    }

    public void setMatchCards(Set<MatchCard> matchCards) {
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
        public Set<MatchCard> matchCards;
        private Long id;
        private Player player;
        private Integer totalCards;
        private Calendar createdAt;
        private Boolean victory;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder player(Player player) {
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

        public Builder matchCards(Set<MatchCard> matchCards) {
            this.matchCards = matchCards;
            return this;
        }

        public Builder victory(Boolean victory) {
            this.victory = victory;
            return this;
        }

        public PlayerMatch build() {
            PlayerMatch playerMatch = new PlayerMatch();
            playerMatch.setId(id);
            playerMatch.setPlayer(player);
            playerMatch.setTotalCards(totalCards);
            playerMatch.setCreatedAt(createdAt);
            playerMatch.setMatchCards(matchCards);
            playerMatch.setVictory(victory);
            return playerMatch;
        }
    }
}
