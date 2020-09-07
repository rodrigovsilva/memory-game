package com.rodrigovsilva.memorygame.controller;

import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.dto.GamePlayDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.exception.MemoryGameBusinessException;
import com.rodrigovsilva.memorygame.exception.MemoryGameException;
import com.rodrigovsilva.memorygame.service.GameMatchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Memory Game Rest controller.
 *
 * @author Rodrigo Silva
 */
@CrossOrigin
@RestController
@RequestMapping("/game")
public class GameMatchController extends BaseController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    GameMatchService gameMatchService;

    @GetMapping
    public ResponseEntity<Object> get() {

        List<PlayerMatchDTO> playerMatches = gameMatchService.listPlayerMatches();

        return new ResponseEntity<>(playerMatches, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createNewGame(@RequestBody PlayerMatchDTO playerMatchDTO) throws MemoryGameException {

        // invalid arguments
        if (playerMatchDTO == null || (playerMatchDTO != null && playerMatchDTO.getPlayer() == null)) {
            throw new MemoryGameException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
        }

        if (StringUtils.isEmpty(playerMatchDTO.getPlayer().getName())) {
            throw new MemoryGameBusinessException(ExceptionMessages.PARAMETER_IS_MISSING.getMessage("Player Name"));
        }

        PlayerMatchDTO createdMatch = gameMatchService.createNewMatch(playerMatchDTO.getPlayer(), playerMatchDTO.getTotalCards());

        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);

    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkCards(@RequestBody GamePlayDTO gamePlayDTO) {

        gamePlayDTO = gameMatchService.checkCards(gamePlayDTO);

        return new ResponseEntity<>(gamePlayDTO, HttpStatus.OK);

    }
}
