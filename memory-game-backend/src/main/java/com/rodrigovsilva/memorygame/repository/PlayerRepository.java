package com.rodrigovsilva.memorygame.repository;

import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Player repository interface.
 *
 * @author Rodrigo Silva
 */
public interface PlayerRepository extends CrudRepository<Player, Long> {

    /**
     * Find player by name.
     *
     * @param name Player name.
     * @return Player by name.
     */
    Optional<Player> findByName(String name);

}
