package com.snoweegamecorp.api.exceptions;

/**
 * Represents a field message with a field name and a message.
 */
public class FieldMessage {
    private String fieldName;
    private String message;

    /**
     * Default constructor.
     */
    public FieldMessage(){
    }

    /**
     * Constructor to create a FieldMessage with a field name and message.
     * @param fieldName The name of the field.
     * @param message The message associated with the field.
     */
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    /**
     * Get the field name.
     * @return The field name.
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Set the field name.
     * @param fieldName The field name to set.
     */
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    /**
     * Get the message.
     * @return The message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message.
     * @param message The message to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }
}