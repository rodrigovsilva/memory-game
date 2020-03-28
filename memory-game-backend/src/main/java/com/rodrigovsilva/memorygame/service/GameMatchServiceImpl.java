
package com.rodrigovsilva.memorygame.service;

import com.rodrigovsilva.memorygame.common.constant.AppConstants;
import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.common.util.EntitiySerializer;
import com.rodrigovsilva.memorygame.dto.MatchCardDTO;
import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.exception.PlayerAlreadyExistsException;
import com.rodrigovsilva.memorygame.model.MatchCard;
import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import com.rodrigovsilva.memorygame.repository.MatchCardRepository;
import com.rodrigovsilva.memorygame.repository.PlayerMatchRepository;
import com.rodrigovsilva.memorygame.repository.PlayerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.security.SecureRandom;
import java.util.*;
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
    MatchCardRepository matchCardRepository;

    @Autowired
    EntitiySerializer entitiySerializer;

    @Autowired
    public GameMatchServiceImpl(PlayerRepository playerRepository, PlayerMatchRepository playerMatchRepository, MatchCardRepository matchCardRepository) {
        this.playerRepository = playerRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.matchCardRepository = matchCardRepository;
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
            throw new PlayerAlreadyExistsException(ExceptionMessages.PLAYER_ALREADY_EXISTS.getMessage(newPlayer.getName()));
        }, () -> {
            Player createdPlayer = playerRepository.save(Player.Builder.builder().id(newPlayer.getId()).name(newPlayer.getName()).build());
            newPlayer.setId(createdPlayer.getId());
        });

        return newPlayer;
    }

    @Override
    public PlayerMatchDTO createNewMatch(PlayerDTO playerDTO, Integer numberOfCards) throws PlayerAlreadyExistsException {

        //create a new player only if there is no id
        if (playerDTO.getId() == null) {
            playerDTO = this.createNewPlayer(playerDTO);
        }

        // to avoid a game without number of cards selection
        if (numberOfCards == null) {
            numberOfCards = AppConstants.DEFAULT_GAME_NUMBER_OF_CARDS;
        }

        // create a match model and save it
        PlayerMatch playerMatch = PlayerMatch.Builder.builder().player(entitiySerializer.toPlayer(playerDTO)).totalCards(numberOfCards).build();
        playerMatchRepository.save(playerMatch);

        // prepare match data
        PlayerMatchDTO newMatch = entitiySerializer.toPlayerMatchDTO(playerMatch);
        newMatch.setMatchCards(this.prepareMatchCards(playerMatch));

        return newMatch;
    }

    @Override
    public List<MatchCardDTO> prepareMatchCards(PlayerMatch match) {

        SecureRandom secureRandom = new SecureRandom();

        Map<Integer, Integer> numbers = new HashMap<>();

        List<MatchCard> matchCardList = this.sortMatchCards(match.getTotalCards());

        matchCardRepository.saveAll(matchCardList);

        // convert cards from model to dto
        List<MatchCardDTO> preparedCards = matchCardList.stream().map(matchCard -> {
            return entitiySerializer.toMatchCardDTO(matchCard);
        }).collect(Collectors.toList());

        LOGGER.info("preparedCards {}", preparedCards);

        return preparedCards;
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

                return PlayerMatchDTO.Builder.builder()//
                        .id(playerMatch.getId())//
                        .player(player1)//
                        .totalCards(playerMatch.getTotalCards())
                        .createdAt(playerMatch.getCreatedAt())
                        .build();
            }).collect(Collectors.toList());

        } else {
            return new ArrayList<PlayerMatchDTO>();
        }

    }

    @Override
    public List<MatchCard> sortMatchCards(Integer totalCards) {
        SecureRandom secureRandom = new SecureRandom();

        Map<Integer, Integer> numbers = new HashMap<>();

        List<MatchCard> matchCardList = new ArrayList<>();

        // build cards map by position
        for (int position = 0; position < totalCards; position++) {

            Integer newNumber = secureRandom.nextInt(secureRandom.nextInt(AppConstants.MAX_CARD_NUMBER));

            while (numbers.containsValue(newNumber)) {
                newNumber = secureRandom.nextInt(secureRandom.nextInt(AppConstants.MAX_CARD_NUMBER));
            }

            numbers.put(position, newNumber);

            matchCardList.add(MatchCard.Builder.builder().position(position).number(newNumber).build());

        }

        return matchCardList;
    }
}
