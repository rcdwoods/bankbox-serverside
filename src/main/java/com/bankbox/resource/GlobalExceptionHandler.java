package com.bankbox.resource;

import com.bankbox.domain.Error;
import com.bankbox.exception.BalanceNotEnoughException;
import com.bankbox.exception.BankAccountNotFoundException;
import com.bankbox.exception.CostumerAlreadyExistsException;
import com.bankbox.exception.CostumerAlreadyHasBankException;
import com.bankbox.exception.CostumerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Error handleValidationException(MethodArgumentNotValidException exception) {
			Map<String, String> errors = new HashMap<>();
			exception.getBindingResult().getAllErrors().forEach(error -> {
				String fieldName = ((FieldError) error).getField();
				String errorMessage = error.getDefaultMessage();
				errors.put(fieldName, errorMessage);
			});
			String message = errors.toString()
				.replace("=", " ")
				.replace("{", "")
				.replace("}", "");
			return new Error(HttpStatus.BAD_REQUEST, message, "ARGUMENT_NOT_VALID");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(CostumerNotFoundException.class)
	public Error costumerNotFoundException(CostumerNotFoundException exception) {
		return new Error(HttpStatus.NOT_FOUND, exception.getMessage(), "COSTUMER_NOT_FOUND");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CostumerAlreadyExistsException.class)
	public Error costumerAlreadyExistsException(CostumerAlreadyExistsException exception) {
		return new Error(HttpStatus.BAD_REQUEST, exception.getMessage(), "COSTUMER_ALREADY_EXISTS");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CostumerAlreadyHasBankException.class)
	public Error costumerAlreadyHasBankException(CostumerAlreadyHasBankException exception) {
		return new Error(HttpStatus.BAD_REQUEST, exception.getMessage(), "COSTUMER_ALREADY_HAS_BANK");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BalanceNotEnoughException.class)
	public Error balanceNotEnough(BalanceNotEnoughException exception) {
		return new Error(HttpStatus.BAD_REQUEST, exception.getMessage(), "BALANCE_NOT_ENOUGH");
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(IllegalArgumentException.class)
	public Error illegalArgumentoException(IllegalArgumentException exception) {
		return new Error(HttpStatus.BAD_REQUEST, exception.getMessage(), "ILLEGAL_ARGUMENT");
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(BankAccountNotFoundException.class)
	public Error bankAccountNotFoundException(BankAccountNotFoundException exception) {
		return new Error(HttpStatus.NOT_FOUND, exception.getMessage(), "BANK_ACCOUNT_NOT_FOUND");
	}
}
