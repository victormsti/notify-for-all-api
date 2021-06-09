package com.notifyforall.api.config.exceptions;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends RestException {

    private static final long serialVersionUID = 1L;

	public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN, "Forbidden");
    }

}
