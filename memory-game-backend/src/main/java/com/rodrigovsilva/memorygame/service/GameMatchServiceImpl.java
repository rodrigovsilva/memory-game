
package com.rodrigovsilva.memorygame.service;

import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.exception.PlayerAlreadyExistsException;
import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import com.rodrigovsilva.memorygame.repository.PlayerMatchRepository;
import com.rodrigovsilva.memorygame.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * All game match service methods implementations.
 *
 * @author Rodrigo Silva
 */
@Service
public class GameMatchServiceImpl implements GameMatchService {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    PlayerMatchRepository playerMatchRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    public GameMatchServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    /**
     * Default constructor.
     */
    public GameMatchServiceImpl() {

    }

    @Override
    public PlayerDTO createNewPlayer(PlayerDTO newPlayer) throws PlayerAlreadyExistsException {

        Optional<Player> optionalExistingPlayer = playerRepository.findByName(newPlayer.getName());

        final PlayerDTO playerDTO = null;
        optionalExistingPlayer.ifPresentOrElse(player -> {
            LOGGER.info(ExceptionMessages.PLAYER_ALREADY_EXISTS.getMessage(newPlayer.getName()));
            throw new PlayerAlreadyExistsException(ExceptionMessages.PLAYER_ALREADY_EXISTS.getMessage(newPlayer.getName()));
        }, () -> {
            Player createdPlayer = playerRepository.save(Player.Builder.builder().id(newPlayer.getId()).name(newPlayer.getName()).build());
            newPlayer.setId(createdPlayer.getId());
        });

        return newPlayer;
    }

    @Override
    public List<PlayerMatchDTO> listPlayerMatches(Player player) {

        Optional<List<PlayerMatch>> optionalPlayerMatches = playerMatchRepository.findAllByPlayerId(player.getId());

        // convert all player matches model to dto
        if (optionalPlayerMatches.isPresent()) {
            List<PlayerMatch> playerMatches = optionalPlayerMatches.get();

            return playerMatches.stream().map(playerMatch -> {
                PlayerDTO player1 = PlayerDTO.Builder.builder()//
                        .id(playerMatch.getPlayer().getId()) //
                        .name(playerMatch.getPlayer().getName())//
                        .build();

                return PlayerMatchDTO.PlayerMatchDTOBuilder.builder()//
                        .withId(playerMatch.getId())//
                        .withPlayer(player1)//
                        .withTotalCards(playerMatch.getTotalCards())
                        .withCreatedAt(playerMatch.getCreatedAt())
                        .build();
            }).collect(Collectors.toList());

        } else {
            return new ArrayList<PlayerMatchDTO>();
        }

    }
}
