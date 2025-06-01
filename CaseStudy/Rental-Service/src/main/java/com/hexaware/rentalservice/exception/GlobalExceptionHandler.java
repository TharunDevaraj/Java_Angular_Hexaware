package com.hexaware.rentalservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

	@ExceptionHandler({FeedbackNotFoundException.class})
	public ResponseEntity<String> userExceptionHandler()
	{
		return new ResponseEntity<String>("Feedback ID not found, enter a valid Feedback ID", HttpStatus.NOT_FOUND);
	}
}
