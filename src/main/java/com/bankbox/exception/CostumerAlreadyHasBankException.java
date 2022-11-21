package com.bankbox.exception;

public class CostumerAlreadyHasBankException extends RuntimeException {
	public CostumerAlreadyHasBankException(String message) {
		super(message);
	}
}
