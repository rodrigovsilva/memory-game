package com.rodrigovsilva.memorygame.controller;

import com.google.gson.Gson;
import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.common.util.ObjectParserUtil;
import com.rodrigovsilva.memorygame.dto.ApiErrorDTO;
import com.rodrigovsilva.memorygame.dto.GamePlayDTO;
import com.rodrigovsilva.memorygame.dto.PlayerDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.exception.MemoryGameBusinessException;
import com.rodrigovsilva.memorygame.exception.MemoryGameException;
import com.rodrigovsilva.memorygame.model.PlayerMatch;
import com.rodrigovsilva.memorygame.service.GameMatchService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
public class GameMatchController {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    GameMatchService gameMatchService;

    @GetMapping
    public ResponseEntity<Object> get() {

        LOGGER.info("get");

        ResponseEntity<Object> responseEntity = null;

        try {
            List<PlayerMatchDTO> playerMatches = gameMatchService.listPlayerMatches();

            responseEntity = new ResponseEntity<>(playerMatches, HttpStatus.OK);

        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("importData response body {}", ObjectParserUtil.getInstance().toString(responseEntity));
        }

        return responseEntity;
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createNewGame(@RequestBody PlayerMatchDTO playerMatchDTO) {

        LOGGER.info("create: {}", new Gson().toJson(playerMatchDTO));

        ResponseEntity<Object> responseEntity = null;

        try {

            // invalid arguments
            if (playerMatchDTO == null || (playerMatchDTO != null && playerMatchDTO.getPlayer() == null)) {
                throw new MemoryGameException(ExceptionMessages.INVALID_ARGUMENTS.getMessage());
            }

            if (StringUtils.isEmpty(playerMatchDTO.getPlayer().getName())) {
                throw new MemoryGameBusinessException(ExceptionMessages.PARAMETER_IS_MISSING.getMessage("Player Name"));
            }

            PlayerMatchDTO createdMatch = gameMatchService.createNewMatch(playerMatchDTO.getPlayer(), playerMatchDTO.getTotalCards());

            responseEntity = new ResponseEntity<>(createdMatch, HttpStatus.CREATED);

        } catch (MemoryGameBusinessException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("create response body {}", ObjectParserUtil.getInstance().toString(responseEntity));
        }

        return responseEntity;
    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkCards(@RequestBody GamePlayDTO gamePlayDTO) {

        LOGGER.info("check: {}", new Gson().toJson(gamePlayDTO));

        ResponseEntity<Object> responseEntity = null;

        try {
            gamePlayDTO = gameMatchService.checkCards(gamePlayDTO);

            responseEntity = new ResponseEntity<>(gamePlayDTO, HttpStatus.CREATED);

        } catch (MemoryGameBusinessException e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.BAD_REQUEST, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("create response body {}", ObjectParserUtil.getInstance().toString(responseEntity));
        }

        return responseEntity;
    }
}
