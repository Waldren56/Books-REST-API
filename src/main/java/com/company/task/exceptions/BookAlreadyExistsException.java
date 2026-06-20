package com.company.task.exceptions;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException() {
        super();
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
