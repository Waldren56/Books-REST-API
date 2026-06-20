package com.company.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BookExceptionHandler {

    // Gestisce errori di validazione dei DTO (HTTP 400 Bad Request)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        response.put("error", "Bad Request");
        response.put("validationErrors", errors);
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @ExceptionHandler(BookAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> handleBookAlreadyExists(BookAlreadyExistsException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Book Conflict");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.CONFLICT.value());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }

    @ExceptionHandler(BookDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, Object> handleBookDoesNotExist(BookDoesNotExistException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("error", "Book Not Found");
        response.put("message", ex.getMessage());
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("timestamp", System.currentTimeMillis());
        return response;
    }
}