package com.cyberspeed.infraestructure.application.exceptions;

/**
 * The {@code InvalidParametersException} class is a custom runtime exception that is thrown when invalid parameters
 * are provided to a method or process.
 *
 * <p>This exception is used to signal that an operation cannot proceed due to incorrect or missing input values.
 * It extends {@link RuntimeException}, meaning it is unchecked and does not need to be declared in a method's
 * {@code throws} clause.</p>
 *
 */
public class InvalidParametersException extends RuntimeException {
    /**
     * Constructs a new {@code InvalidParametersException} with the specified detail message.
     *
     * @param message the detail message, explaining why the exception was thrown
     */
    public InvalidParametersException(String message) {
        super(message);
    }
}
