package com.bankbox.exception;

public class CostumerAlreadyExistsException extends RuntimeException {
	public CostumerAlreadyExistsException(String message) {
		super(message);
	}
}
