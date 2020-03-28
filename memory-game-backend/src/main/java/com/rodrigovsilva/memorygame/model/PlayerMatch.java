package com.rodrigovsilva.memorygame.model;

import com.rodrigovsilva.memorygame.dto.MatchCardDTO;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

/**
 * This entity represents all information about a player match.
 *
 * @author Rodrigo Silva
 */
@Entity
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

    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Calendar createdAt;

    @OneToMany(targetEntity = MatchCard.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_match_id", foreignKey = @ForeignKey(name = "FK_MATCH_CARD_TO_PLAYER_MATCH"))
    @NotNull
    public List<MatchCard> matchCards;

    public PlayerMatch() {
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


    public static final class Builder {
        private Long id;
        private Player player;
        private Integer totalCards;
        private Calendar createdAt;

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

        public PlayerMatch build() {
            PlayerMatch playerMatch = new PlayerMatch();
            playerMatch.setId(id);
            playerMatch.setPlayer(player);
            playerMatch.setTotalCards(totalCards);
            playerMatch.setCreatedAt(createdAt);
            return playerMatch;
        }
    }
}
