package com.hexaware.userservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  Date: 02-06-2025
 *  Author: Tharun D
 * Global exception handler for handling User Related errors and returning
 * structured error responses to the client.
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler({UserNotFoundException.class})
	public ResponseEntity<String> userExceptionHandler()
	{
		return new ResponseEntity<String>("User ID not found, enter a valid User ID", HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({UserNameAlreadyExistsException.class})
	public ResponseEntity<String> userNameExceptionHandler()
	{
		return new ResponseEntity<String>("User name already exists, try registering using different username", HttpStatus.NOT_IMPLEMENTED);
	}
}
