package com.bankbox.domain;

import org.springframework.http.HttpStatus;

public class Error {
	private final HttpStatus status;
	private final String message;
	private final String code;

	public Error(HttpStatus status, String description, String code) {
		this.status = status;
		this.message = description;
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getCode() {
		return code;
	}
}
