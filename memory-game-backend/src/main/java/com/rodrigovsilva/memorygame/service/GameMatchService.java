package com.rodrigovsilva.memorygame.service;

import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.model.Player;

import java.util.List;

/**
 * All Game Match service methods interfaces.
 *
 * @author Rodrigo Silva.
 */
public interface GameMatchService {

    /**
     * Create new player
     *
     * @param newPlayer New player
     * @return New player created.
     */
    PlayerDTO createNewPlayer(PlayerDTO newPlayer);

    /**
     * List all player matches
     *
     * @param player Player
     * @return List of all player matches.
     */
    List<PlayerMatchDTO> listPlayerMatches(Player player);
    
}
