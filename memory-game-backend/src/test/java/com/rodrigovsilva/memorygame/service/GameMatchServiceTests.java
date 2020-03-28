package com.rodrigovsilva.memorygame.service;

import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.model.Player;
import com.rodrigovsilva.memorygame.repository.PlayerMatchRepository;
import com.rodrigovsilva.memorygame.repository.PlayerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class GameMatchServiceTests {

    @Mock
    private PlayerMatchRepository playerMatchRepository;

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private GameMatchService gameMatchService= new GameMatchServiceImpl(this.playerRepository);

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
}
