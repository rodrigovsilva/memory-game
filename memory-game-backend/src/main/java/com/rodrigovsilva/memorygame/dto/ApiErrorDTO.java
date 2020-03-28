package com.rodrigovsilva.memorygame.dto;

import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class to carry Api error data.
 *
 * @author Rodrigo Silva
 */
public class ApiErrorDTO {

    private HttpStatus status;
    private String details;
    private List<String> errors;

    public ApiErrorDTO(HttpStatus status, String details, List<String> errors) {
        super();
        this.status = status;
        this.details = details;
        this.errors = errors;
    }

    public ApiErrorDTO(HttpStatus status, String details, String error) {
        super();
        this.status = status;
        this.details = details;
        this.addError(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}