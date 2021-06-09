package com.notifyforall.api.config.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper=false)
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
    private String message;
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RestException(HttpStatus status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }
}
