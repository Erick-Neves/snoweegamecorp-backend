package com.snoweegamecorp.api.exceptions;

/**
 * Exception thrown when a resource is not found.
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param msg the detail message
     */
    public ResourceNotFoundException(String msg) {
        super(msg);
    }
}