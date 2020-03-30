package com.rodrigovsilva.memorygame.service;

import com.google.gson.Gson;
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
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.invoke.MethodHandles;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class GameMatchServiceTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Mock
    private PlayerMatchRepository playerMatchRepository;

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    MatchCardRepository matchCardRepository;

    EntitiySerializer entitiySerializer = new EntitiySerializer();

    @InjectMocks
    private GameMatchService gameMatchService = new GameMatchServiceImpl(this.playerRepository, this.playerMatchRepository, this.matchCardRepository, this.entitiySerializer);

    @Test
    public void whenCreateNewPlayerSuccesfully() {

        final Long id = 1L;
        final String name = "Rodrigo";

        final Player player = Player.Builder.builder().id(id).name(name).build();
        final PlayerDTO playerDTO = PlayerDTO.Builder.builder().id(id).name(name).build();

        // creating user
        given(playerRepository.findByName(name)).willReturn(Optional.empty());
        given(playerRepository.save(player)).willReturn(player);

        PlayerDTO createdPlayer = gameMatchService.createNewPlayer(playerDTO);

        assertThat(createdPlayer).isNotNull();

        verify(playerRepository).save(any(Player.class));
    }

    @Test
    public void whenCreateNewPlayerAlreadyExists_thenReturnException() {

        final Long id = 1L;
        final String name = "Rodrigo";

        final Player player = Player.Builder.builder().id(id).name(name).build();
        final PlayerDTO playerDTO = PlayerDTO.Builder.builder().id(id).name(name).build();

        // creating user
        given(playerRepository.findByName(name)).willReturn(Optional.of(player));
        Assertions.assertThrows(PlayerAlreadyExistsException.class, () -> {
            gameMatchService.createNewPlayer(playerDTO);

        });

        verify(playerRepository, never()).save(any(Player.class));
    }

    @Test
    public void whenCreateNewMatchSuccessfully() {

        final Long id = 1L;
        final String name = "Rodrigo";
        final Integer totalCards = 4;

        final Player player = Player.Builder.builder().id(id).name(name).build();
        final PlayerDTO playerDTO = PlayerDTO.Builder.builder().id(id).name(name).build();
        final PlayerMatch playerMatch = PlayerMatch.Builder.builder().id(11L).player(player).totalCards(totalCards).createdAt(Calendar.getInstance()).build();
        final PlayerMatchDTO playerMatchDTO = PlayerMatchDTO.Builder.builder().id(11L).player(playerDTO).totalCards(totalCards).createdAt(Calendar.getInstance()).build();
        final Set<MatchCard> matchCardList = gameMatchService.sortMatchCards(playerMatch);
        final Set<MatchCardDTO> matchCardListDTO =
                matchCardList.stream().map(matchCard -> {
                    return entitiySerializer.toMatchCardDTO(matchCard);
                }).collect(Collectors.toSet());
        // creating user
        given(playerRepository.findByName(name)).willReturn(Optional.empty());
        given(playerRepository.save(player)).willReturn(player);
        given(playerMatchRepository.save(playerMatch)).willReturn(playerMatch);
        given(matchCardRepository.saveAll(matchCardList)).willReturn(matchCardList);

        PlayerMatchDTO createdMatch = gameMatchService.createNewMatch(playerDTO, totalCards);

        LOGGER.info("{}\n{}", new Gson().toJson(createdMatch.getMatchCards()), new Gson().toJson(matchCardListDTO));
        assertThat(createdMatch).isNotNull();
        Assert.assertEquals(createdMatch.getMatchCards(), matchCardListDTO);
        //verify(playerRepository).save(any(Player.class));
        verify(playerMatchRepository).save(any(PlayerMatch.class));
        verify(matchCardRepository).saveAll(any(List.class));
    }
}
