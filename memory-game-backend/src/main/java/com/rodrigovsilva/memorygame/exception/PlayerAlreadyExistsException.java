package com.rodrigovsilva.memorygame.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * When a player is already registered on DB.
 *
 * @author Rodrigo Silva
 */
public class PlayerAlreadyExistsException extends MemoryGameBusinessException {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public PlayerAlreadyExistsException() {
        super();
    }

    public PlayerAlreadyExistsException(String message) {
        super(message);
    }

    public PlayerAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    public PlayerAlreadyExistsException(Throwable cause, String message) {
        super(cause, message);
    }

}