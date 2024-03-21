package com.snoweegamecorp.api.exceptions;

public class FieldMessage {
    private String fieldName;
    private String message;
    public FieldMessage(){
    }
    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldMessage) {
        this.fieldName = fieldMessage;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
