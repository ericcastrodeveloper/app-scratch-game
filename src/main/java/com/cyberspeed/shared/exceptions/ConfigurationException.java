package com.cyberspeed.shared.exceptions;


/**
 * Exception thrown when there is an issue related to the configuration of the application.
 * This can be triggered when there are missing configuration parameters, invalid values,
 * or any other problem that affects the proper initialization of the system's configuration.
 */
public class ConfigurationException extends RuntimeException {
    /**
     * Constructs a new ConfigurationException with a detailed message.
     *
     * @param message the detail message explaining the cause of the exception.
     */
    public ConfigurationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConfigurationException with a detailed message and a cause.
     *
     * @param message the detail message explaining the cause of the exception.
     * @param cause the cause of the exception (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown).
     */
    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
