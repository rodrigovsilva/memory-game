package com.rodrigovsilva.memorygame.controller;

import com.rodrigovsilva.memorygame.dto.ApiErrorDTO;
import com.rodrigovsilva.memorygame.exception.MemoryGameBusinessException;
import com.rodrigovsilva.memorygame.exception.MemoryGameException;
import com.rodrigovsilva.memorygame.exception.PlayerAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.lang.invoke.MethodHandles;

public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(PlayerAlreadyExistsException.class)
    private ResponseEntity<Object> playerAlreadyExistsExceptionHandler(WebRequest request, Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorDTO(HttpStatus.CONFLICT, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemoryGameBusinessException.class)
    private ResponseEntity<Object> businessExceptionHandler(WebRequest request, Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorDTO(HttpStatus.PRECONDITION_FAILED, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MemoryGameException.class)
    private ResponseEntity<Object> appExceptionHandler(WebRequest request, Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> defaultExceptionHandler(WebRequest request, Exception e) {
        LOGGER.error(e.getMessage(), e);
        return new ResponseEntity<>(new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, e.getCause() != null ? e.getCause().getMessage() : e.getMessage(), e.getLocalizedMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
