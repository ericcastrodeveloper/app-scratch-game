package com.cyberspeed.domain.exceptions;

/**
 * Exception thrown when an invalid random value is generated or provided.
 * This exception is typically used when random number generation produces
 * a value that is out of the expected or permissible range.
 */
public class InvalidRandomValueException extends RuntimeException {

    /**
     * Constructs a new InvalidRandomValueException with a detailed message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public InvalidRandomValueException(String message) {
        super(message);
    }
}

