package com.company.task.exceptions;

public class BookDoesNotExistException extends RuntimeException {
    public BookDoesNotExistException() {
        super();
    }

    public BookDoesNotExistException(String message) {
        super(message);
    }

    public BookDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
