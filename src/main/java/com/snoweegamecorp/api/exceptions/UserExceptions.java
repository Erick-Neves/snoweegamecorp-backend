package com.snoweegamecorp.api.exceptions;

import com.snoweegamecorp.api.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.time.Instant;
public class UserExceptions {
    public ResponseEntity<ValidationError> userErrorValidatingEmail(){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err = new ValidationError();
        err.setTimeStamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Invalid registration!");
        err.addError("email", "E-mail already registered!");
        //err.setMessage(e.getMessage());
        //err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
