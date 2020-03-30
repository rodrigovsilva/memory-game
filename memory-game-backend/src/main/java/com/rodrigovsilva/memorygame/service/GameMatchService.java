package com.rodrigovsilva.memorygame.service;

import com.rodrigovsilva.memorygame.dto.GamePlayDTO;
import com.rodrigovsilva.memorygame.dto.MatchCardDTO;
import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.exception.PlayerAlreadyExistsException;
import com.rodrigovsilva.memorygame.model.MatchCard;
import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.model.PlayerMatch;

import java.util.List;
import java.util.Set;

/**
 * All Game Match service methods interfaces.
 *
 * @author Rodrigo Silva.
 */
public interface GameMatchService {

    /**
     * Create new player
     *
     * @param newPlayer New player.
     * @return New player created.
     * @throws PlayerAlreadyExistsException if the player already exists.
     */
    PlayerDTO createNewPlayer(PlayerDTO newPlayer) throws PlayerAlreadyExistsException;

    /**
     * Create a new match.
     *
     * @param playerDTO     Player will play the match.
     * @param numberOfCards Number of cards.
     * @return New match created.
     * @throws PlayerAlreadyExistsException if the player already exists.
     */
    PlayerMatchDTO createNewMatch(PlayerDTO playerDTO, Integer numberOfCards);

    /**
     * Prepare and match cards prepared.
     *
     * @param match match
     * @return Match cards prepared
     */
    Set<MatchCardDTO> prepareMatchCards(PlayerMatch match);

    /**
     * Sort match cards.
     *
     * @param playerMatch Player match.
     * @return List of sorted cards.
     */
    public Set<MatchCard> sortMatchCards(PlayerMatch playerMatch);

    /**
     * List all player matches
     *
     * @return List of all player matches.
     */
    List<PlayerMatchDTO> listPlayerMatches();

    /**
     * Check cards of the game.
     *
     * @param gamePlayDTO Cards of the game
     * @return Play match results.
     */
    GamePlayDTO checkCards(GamePlayDTO gamePlayDTO);


}
