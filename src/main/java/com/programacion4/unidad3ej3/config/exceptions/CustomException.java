package com.programacion4.unidad3ej3.config.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import java.util.List;

@Getter
public abstract class CustomException extends RuntimeException {

    private final org.springframework.http.HttpStatus status;
    private final java.util.List<String> errors;

    public CustomException(String message, org.springframework.http.HttpStatus status, java.util.List<String> errors) {
        super(message);
        this.status = status;
        this.errors = errors;
    }
    public org.springframework.http.HttpStatus getStatus() {
        return this.status;
    }

}



