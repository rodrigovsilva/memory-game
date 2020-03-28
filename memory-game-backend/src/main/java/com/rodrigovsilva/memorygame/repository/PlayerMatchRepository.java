package com.rodrigovsilva.memorygame.repository;

import com.rodrigovsilva.memorygame.model.PlayerMatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Player match repository interface.
 *
 * @author Rodrigo Silva
 */
public interface PlayerMatchRepository extends CrudRepository<PlayerMatch, Long> {

    /**
     * Find all player matches by Name.
     *
     * @param playerId Player id.
     * @return All player matches.
     */
    Optional<List<PlayerMatch>> findAllByPlayerId(Long playerId);
}
