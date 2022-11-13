package com.bankbox.exception;

public class CostumerNotFoundException extends RuntimeException {
	public CostumerNotFoundException(String message) {
		super(message);
	}
}
