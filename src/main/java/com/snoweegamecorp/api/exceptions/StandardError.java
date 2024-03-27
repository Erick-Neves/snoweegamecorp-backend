package com.snoweegamecorp.api.exceptions;
import java.time.Instant;

/**
 * Standard error class for API exceptions
 */
public class StandardError {
    private Integer status; // HTTP status code
    private String error; // Error type
    private String path; // Requested path
    private String message; // Error message
    private Instant timeStamp; // Timestamp of the error
    /**
     * Default constructor
     */
    public StandardError(){
    }
    /**
     * Get the timestamp
     * @return Instant object representing the timestamp
     */
    public Instant getTimeStamp() {
        return timeStamp;
    }
    /**
     * Get the HTTP status code
     * @return Integer representing the status code
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * Set the HTTP status code
     * @param status Integer representing the status code
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * Get the error message
     * @return String representing the error message
     */
    public String getMessage() {
        return message;
    }
    /**
     * Set the error message
     * @param message String representing the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
    /**
     * Get the error type
     * @return String representing the error type
     */
    public String getError() {
        return error;
    }
    /**
     * Set the error type
     * @param error String representing the error type
     */
    public void setError(String error) {
        this.error = error;
    }
    /**
     * Get the requested path
     * @return String representing the requested path
     */
    public String getPath() {
        return path;
    }
    /**
     * Set the requested path
     * @param path String representing the requested path
     */
    public void setPath(String path) {
        this.path = path;
    }
    /**
     * Set the timestamp
     * @param timeStamp Instant object representing the timestamp
     */
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}