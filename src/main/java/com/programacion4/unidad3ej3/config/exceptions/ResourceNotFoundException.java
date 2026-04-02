package com.programacion4.unidad3ej3.config.exceptions;

import org.springframework.http.HttpStatus;
import java.util.List;

public class ResourceNotFoundException extends CustomException {
    public ResourceNotFoundException(String message) {
        // 404 Not Found es el estándar para cuando no existe un ID
        super(message, HttpStatus.NOT_FOUND, List.of(message));
    }
}
