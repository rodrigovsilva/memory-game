package com.rodrigovsilva.memorygame.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

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
    private Long totalCards;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Calendar createdAt;

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
}
