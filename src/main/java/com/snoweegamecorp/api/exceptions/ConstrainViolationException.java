package com.snoweegamecorp.api.exceptions;

public class ConstrainViolationException extends RuntimeException{
    public ConstrainViolationException(String msg){
        super(msg);
    }
}
