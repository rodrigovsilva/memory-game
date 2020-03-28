package com.rodrigovsilva.memorygame.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * This entity represents all information about a cards of the match.
 *
 * @author Rodrigo Silva
 */
@Entity
@Table(name = "match_card")
public class MatchCard {

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
}
