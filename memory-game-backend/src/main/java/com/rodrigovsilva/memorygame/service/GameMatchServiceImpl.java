
package com.rodrigovsilva.memorygame.service;

import com.google.gson.Gson;
import com.rodrigovsilva.memorygame.common.constant.AppConstants;
import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.common.util.EntitiySerializer;
import com.rodrigovsilva.memorygame.dto.GamePlayDTO;
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
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public GameMatchServiceImpl(PlayerRepository playerRepository, PlayerMatchRepository playerMatchRepository, MatchCardRepository matchCardRepository, EntitiySerializer entitiySerializer) {
        this.playerRepository = playerRepository;
        this.playerMatchRepository = playerMatchRepository;
        this.matchCardRepository = matchCardRepository;
        this.entitiySerializer = entitiySerializer;
    }

    /**
     * Default constructor.
     */
    public GameMatchServiceImpl() {

    }

    @Override
    public PlayerDTO createNewPlayer(final PlayerDTO newPlayer) throws PlayerAlreadyExistsException {

        Optional<Player> optionalExistingPlayer = playerRepository.findByName(newPlayer.getName());

        final PlayerDTO playerDTO = null;
        if (optionalExistingPlayer.isPresent()) {

            return entitiySerializer.toPlayerDTO(optionalExistingPlayer.get());
        } else {
            Player createdPlayer = playerRepository.save(Player.Builder.builder().id(newPlayer.getId()).name(newPlayer.getName()).build());
            newPlayer.setId(createdPlayer.getId());

            return newPlayer;
        }

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
        newMatch.setMatchCards(prepareMatchCards(playerMatch));

        return newMatch;
    }

    @Override
    public Set<MatchCardDTO> prepareMatchCards(PlayerMatch match) {

        Set<MatchCard> matchCardList = this.sortMatchCards(match);

        matchCardRepository.saveAll(matchCardList);

        // convert cards from model to dto
        Set<MatchCardDTO> preparedCards = matchCardList.stream().map(matchCard -> {
            return entitiySerializer.toMatchCardDTO(matchCard);
        }).collect(Collectors.toSet());

        LOGGER.info("preparedCards {}", preparedCards);

        return preparedCards;
    }

    @Override
    public List<PlayerMatchDTO> listPlayerMatches() {

        Iterable<PlayerMatch> itPlayerMatches = playerMatchRepository.findAll();

        // convert all player matches model to dto
        if (itPlayerMatches != null) {

            return StreamSupport.stream(itPlayerMatches.spliterator(), false).map(playerMatch -> {
                return entitiySerializer.toPlayerMatchDTO(playerMatch);
            }).collect(Collectors.toList());

        } else {
            return new ArrayList<PlayerMatchDTO>();
        }
    }

    @Override
    public GamePlayDTO checkCards(GamePlayDTO gamePlayDTO) {

        Optional<PlayerMatch> optionalPlayerMatch = playerMatchRepository.findById(gamePlayDTO.getPlayerMatch().getId());


        optionalPlayerMatch.ifPresent(playerMatch -> {
            List<MatchCard> positionOrderedByCardNumber = playerMatch.getMatchCards().stream().sorted((mc, mc1) -> mc.getNumber().compareTo(mc1.getNumber())).collect(Collectors.toList());

            List<Integer> positions = positionOrderedByCardNumber.stream().map(matchCard -> matchCard.getPosition()).collect(Collectors.toList());

            LOGGER.info("GAME ORDER: {}", new Gson().toJson(positions.toArray()));
            LOGGER.info("GAME PLAY : {}", new Gson().toJson(gamePlayDTO.getSelectedCards().toArray()));
            boolean result = Arrays.equals(positions.toArray(), gamePlayDTO.getSelectedCards().toArray());

            gamePlayDTO.getPlayerMatch().setVictory(result);

            PlayerMatch matchPlayed = optionalPlayerMatch.get();
            matchPlayed.setVictory(result);

            playerMatchRepository.save(matchPlayed);

            LOGGER.info("RESULT : {}", result);
        });

        return gamePlayDTO;
    }

    @Override
    public Set<MatchCard> sortMatchCards(PlayerMatch playerMatch) {

        Random random = new Random();

        Map<Integer, Integer> numbers = new HashMap<>();

        Set<MatchCard> matchCardList = new TreeSet<>();

        // build cards map by position
        for (int position = 0; position < playerMatch.getTotalCards(); position++) {

            Integer newNumber = random.ints(0, AppConstants.MAX_CARD_NUMBER).findAny().getAsInt();

            while (numbers.containsValue(newNumber)) {
                newNumber = random.ints(0, AppConstants.MAX_CARD_NUMBER).findAny().getAsInt();
            }

            numbers.put(position, newNumber);

            matchCardList.add(MatchCard.Builder.builder().position(position).number(newNumber).playerMatch(playerMatch).build());

        }

        return matchCardList;
    }
}
