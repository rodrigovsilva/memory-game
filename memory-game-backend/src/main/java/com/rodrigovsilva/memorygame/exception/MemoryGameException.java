package com.rodrigovsilva.memorygame.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

/**
 * An exception to be thrown when an operation was requested on a non-existing knowledge base article.
 *
 * @author Rodrigo Silva
 */
public class MemoryGameException extends Exception {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public MemoryGameException() {
        super();
    }

    public MemoryGameException(String message) {
        super(message);
    }

    public MemoryGameException(Throwable cause) {
        super(cause);
    }

    public MemoryGameException(Throwable cause, String message) {
        super(message, cause);
    }

}