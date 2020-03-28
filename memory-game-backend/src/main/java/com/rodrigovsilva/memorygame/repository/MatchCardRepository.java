package com.rodrigovsilva.memorygame.repository;

import com.rodrigovsilva.memorygame.model.MatchCard;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Match card repository interface.
 *
 * @author Rodrigo Silva
 */
public interface MatchCardRepository extends CrudRepository<MatchCard, Long> {

    /**
     * Find all match cards of a match.
     *
     * @param playerMatch Player match id.
     * @return All match cards.
     */
    Optional<List<MatchCard>> findAllByPlayerMatch(PlayerMatch playerMatch);
}
