package com.rodrigovsilva.memorygame.exception.handler;

import com.rodrigovsilva.memorygame.common.message.ExceptionMessages;
import com.rodrigovsilva.memorygame.common.util.ObjectParserUtil;
import com.rodrigovsilva.memorygame.dto.ApiErrorDTO;
import com.rodrigovsilva.memorygame.exception.MemoryGameException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Custom Rest exception handler for Response Entities.
 */
@RestControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Logger definition.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Message resource.
     */
    @Autowired
    private MessageSource messageSource;

    /**
     * Handle when a client sends an invalid request to the API.
     *
     * @param ex      Exception to be handled
     * @param headers Http Headers
     * @param status  Http Status
     * @param request Http Request
     * @return Response Entity with API Error data
     */
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(resolveLocalizedErrorMessage(error));
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(resolveLocalizedErrorMessage(error));
        }

        LOGGER.error(ObjectParserUtil.getInstance().toString(errors), ex);
        ApiErrorDTO apiError =
                new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
    }

    /**
     * Handle when the part of a multipart request is not found or the request has a missing parameter.
     *
     * @param ex      Exception to be handled
     * @param headers Http Headers
     * @param status  Http Status
     * @param request Http Request
     * @return Response Entity with API Error data
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ExceptionMessages.PARAMETER_IS_MISSING.getMessage(ex.getParameterName());

        LOGGER.error(error, ex);
        ApiErrorDTO apiError =
                new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Handle when a http message cannot be converted or read (Ex: Post without body etc).
     *
     * @param ex      Exception to be handled
     * @param headers Http Headers
     * @param status  Http Status
     * @param request Http Request
     * @return Response Entity with API Error data
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String[] errorMessage = StringUtils.split(ex.getLocalizedMessage(), ":");

        String error = errorMessage != null ? errorMessage[0] : ex.getLocalizedMessage();
        LOGGER.error(error, ex);
        ApiErrorDTO apiError =
                new ApiErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Handle when any handler has not found.
     *
     * @param ex      Exception to be handled
     * @param headers Http Headers
     * @param status  Http Status
     * @param request Http Request
     * @return Response Entity with API Error data
     */
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = ExceptionMessages.NO_HANDLER_FOOUND_ERROR.getMessage(ex.getHttpMethod(), ex.getRequestURL());
        LOGGER.error(error, ex);
        ApiErrorDTO apiError = new ApiErrorDTO(HttpStatus.NOT_FOUND, ex.getMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Resolve localized error message.
     *
     * @param fieldError Field with error.
     * @return Localized error message.
     */
    private String resolveLocalizedErrorMessage(ObjectError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);

        //If the message was not found, return the most accurate field error code instead.
        //You can remove this check if you prefer to get the default error message.
        if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
            String[] fieldErrorCodes = fieldError.getCodes();
            localizedErrorMessage = fieldErrorCodes[0];
        }

        return localizedErrorMessage;
    }

    @ExceptionHandler(MemoryGameException.class)
    public ResponseEntity<?> kbExceptionHandler(Exception ex, WebRequest request) {
        ApiErrorDTO errorDetails = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        ApiErrorDTO errorDetails = new ApiErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR, request.getDescription(false), ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}