package com.rodrigovsilva.memorygame.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * An business exception to be thrown when an operation was requested.
 *
 * @author Rodrigo Silva
 */
public class MemoryGameBusinessException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MemoryGameBusinessException() {
        super();
    }

    public MemoryGameBusinessException(String message) {
        super(message);
    }

    public MemoryGameBusinessException(Throwable cause) {
        super(cause);
    }

    public MemoryGameBusinessException(Throwable cause, String message) {
        super(message, cause);
    }

}