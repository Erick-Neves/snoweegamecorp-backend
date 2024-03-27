package com.snoweegamecorp.api.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents validation errors in the API.
 */
public class ValidationError extends StandardError{
    private List<FieldMessage> errors = new ArrayList<>();
    /**
     * Gets the list of field messages representing errors.
     * @return the list of errors
     */
    public List<FieldMessage> getErrors() {
        return errors;
    }
    /**
     * Adds a new error to the list of errors.
     * @param fieldName the name of the field where the error occurred
     * @param message the error message
     */
    public void addError(String fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }
}