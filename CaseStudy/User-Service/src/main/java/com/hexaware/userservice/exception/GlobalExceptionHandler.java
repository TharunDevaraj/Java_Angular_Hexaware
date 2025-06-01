package com.hexaware.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<String> userExceptionHandler()
	{
		return new ResponseEntity<String>("User ID not found, enter a valid User ID", HttpStatus.NOT_FOUND);
	}
}
