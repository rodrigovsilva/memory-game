package com.rodrigovsilva.memorygame.common.util;

import com.rodrigovsilva.memorygame.dto.MatchCardDTO;
import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.model.MatchCard;
import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import org.springframework.stereotype.Component;

/**
 * Entities serializer (Model and DTOs)
 */
@Component
public class EntitiySerializer {

    /**
     * Serialize a Player model to player DTO.
     *
     * @param player Player model.
     * @return Player DTO.
     */
    public PlayerDTO toPlayerDTO(Player player) {
        return PlayerDTO.Builder.builder()//
                .id(player.getId()) //
                .name(player.getName())//
                .build();

    }

    /**
     * Serialize a Player Match model to player DTO.
     *
     * @param playerMatch Player Match model.
     * @return Player Match DTO.
     */
    public PlayerMatchDTO toPlayerMatchDTO(PlayerMatch playerMatch) {

        return PlayerMatchDTO.Builder.builder()//
                .id(playerMatch.getId())//
                .player(this.toPlayerDTO(playerMatch.getPlayer()))//
                .totalCards(playerMatch.getTotalCards())
                .createdAt(playerMatch.getCreatedAt())
                .build();
    }

    /**
     * Serialize a Player DTO to player model.
     *
     * @param playerDTO Player DTO.
     * @return Player model.
     */
    public Player toPlayer(PlayerDTO playerDTO) {
        return Player.Builder.builder()//
                .id(playerDTO.getId()) //
                .name(playerDTO.getName())//
                .build();

    }

    /**
     * Serialize a Player Match DTO to player model.
     *
     * @param playerMatchDTO Player Match DTO.
     * @return Player Match model.
     */
    public PlayerMatch toPlayerMatch(PlayerMatchDTO playerMatchDTO) {

        return PlayerMatch.Builder.builder()//
                .id(playerMatchDTO.getId())//
                .player(this.toPlayer(playerMatchDTO.getPlayer()))//
                .totalCards(playerMatchDTO.getTotalCards())
                .createdAt(playerMatchDTO.getCreatedAt())
                .build();
    }

    /**
     * Serialize a Match card model to player DTO.
     *
     * @param matchCard Match card model.
     * @return Card of match DTO.
     */
    public MatchCardDTO toMatchCardDTO(MatchCard matchCard) {
        return MatchCardDTO.Builder.builder()//
                .position(matchCard.getPosition()) //
                .number(matchCard.getNumber())//
                .build();

    }

    /**
     * Serialize a Match Card DTO to player model.
     *
     * @param matchCardDTO Match card DTO.
     * @return Match card model.
     */
    public MatchCard toMatchCard(MatchCardDTO matchCardDTO) {

        return MatchCard.Builder.builder()//
                .position(matchCardDTO.getPosition())//
                .number(matchCardDTO.getNumber())
                .build();
    }
}
