package com.rodrigovsilva.memorygame.controller;

import com.rodrigovsilva.memorygame.common.util.ObjectParserUtil;
import com.rodrigovsilva.memorygame.dto.ApiErrorDTO;
import com.rodrigovsilva.memorygame.dto.PlayerMatchDTO;
import com.rodrigovsilva.memorygame.service.GameMatchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;
import java.util.List;

/**
 * Memory Game Rest controller.
 *
 * @author Rodrigo Silva
 */
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
            //List<PlayerMatchDTO> playerMatches = gameMatchService.

            responseEntity = new ResponseEntity<>("get game", HttpStatus.CREATED);

        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            responseEntity = new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            LOGGER.debug("importData response body {}", ObjectParserUtil.getInstance().toString(responseEntity));
        }

        return responseEntity;
    }

}
