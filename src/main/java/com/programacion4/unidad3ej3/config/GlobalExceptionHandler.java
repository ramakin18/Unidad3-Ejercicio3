package com.programacion4.unidad3ej3.config;

import com.programacion4.unidad3ej3.config.exceptions.ConflictException;
import com.programacion4.unidad3ej3.config.exceptions.ResourceNotFoundException;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.Instant;
import java.util.List;

import com.programacion4.unidad3ej3.config.exceptions.CustomException;
@Getter
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Maneja las excepciones personalizadas
     * @param ex La excepción personalizada
     * Captura las excepciones personalizadas y las convierte en una respuesta HTTP con el estado de la excepción
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse<Object>> handleCustomException(CustomException ex) {
        BaseResponse<Object> response = BaseResponse.builder()
                .message(ex.getMessage())
                .errors(ex.getErrors())
                .timestamp(Instant.now().toString())
                .build();

        return new ResponseEntity<>(response, ex.getStatus());
    }

    /**
     * Maneja las excepciones de validación
     * @param ex La excepción de validación
     * @return La respuesta HTTP con el estado de la excepción
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Object>> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + ": " + f.getDefaultMessage())
                .toList();

        BaseResponse<Object> response = BaseResponse.builder()
                .message("Error de validación")
                .errors(errors)
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Maneja las excepciones genéricas
     * @param ex La excepción genérica
     * @return La respuesta HTTP con el estado de la excepción
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Object>> handleGeneric(Exception ex) {
        // En producción, no mostrar el ex.getMessage() detallado para evitar fugas de info
        BaseResponse<Object> response = BaseResponse.builder()
                .message("Ocurrió un error inesperado")
                .errors(List.of("Contacte al administrador"))
                .timestamp(Instant.now().toString())
                .build();

        return ResponseEntity.internalServerError().body(response); 
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<BaseResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                BaseResponse.builder()
                        .message(ex.getMessage())
                        .status(ex.getStatus().value()) // El .value() lo pasa de "NOT_FOUND" a 404
                        .success(false)
                        .build()
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<BaseResponse> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(ex.getStatus()).body(
                BaseResponse.builder()
                        .message(ex.getMessage())
                        .status(ex.getStatus().value()) // El .value() lo pasa de "CONFLICT" a 409
                        .success(false)
                        .build()
        );
    }
}
