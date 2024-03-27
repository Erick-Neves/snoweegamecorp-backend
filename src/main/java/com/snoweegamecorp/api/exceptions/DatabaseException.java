package com.snoweegamecorp.api.exceptions;

/**
 * Custom exception for database related errors
 */
public class DatabaseException extends RuntimeException{
    /**
     * Constructor for DatabaseException
     * @param msg the error message
     */
    public DatabaseException(String msg){
        super(msg);
    }
}