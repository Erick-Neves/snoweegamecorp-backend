package com.snoweegamecorp.api.exceptions;
import java.time.Instant;
public class StandardError {
    private static final long serialVerionUID = 1L;
    private Integer status;
    private String message;
    private String error;
    private String path;
    private Instant timeStamp;
    public StandardError(){
    }
    public Instant getTimeStamp() {
        return timeStamp;
    }
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }
}
