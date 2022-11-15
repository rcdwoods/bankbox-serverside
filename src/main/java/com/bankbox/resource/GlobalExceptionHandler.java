package com.bankbox.resource;

import com.bankbox.domain.Error;
import com.bankbox.exception.CostumerAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
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

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(CostumerAlreadyExistsException.class)
	public Error costumerAlreadyExistsException(CostumerAlreadyExistsException exception) {
		return new Error(HttpStatus.BAD_REQUEST, exception.getMessage(), "COSTUMER_ALREADY_EXISTS");
	}
}
