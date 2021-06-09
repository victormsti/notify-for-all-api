package com.notifyforall.api.config.exceptions;

import org.springframework.http.HttpStatus;

public class HttpClientException extends RestException {

	
	private static final long serialVersionUID = 1L;

	public HttpClientException(HttpStatus status, String message) {
		super(status, message);
	}
	
}
