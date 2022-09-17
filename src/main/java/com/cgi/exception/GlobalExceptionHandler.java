package com.cgi.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@Value(value="${notFoundMessage}")
	private String notFoundMsg;
	@Value(value="${alreadyExistsMessage}")
	private String alreadyExistsMsg;
	
	@ExceptionHandler(value=NotFoundException.class)
	public ResponseEntity<String> notFound(){
		return new ResponseEntity<String> (notFoundMsg,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value=AlreadyExistsException.class)
	public ResponseEntity<String> alreadyExists(){
		return new ResponseEntity<String> (alreadyExistsMsg,HttpStatus.CONFLICT);
	}
}
