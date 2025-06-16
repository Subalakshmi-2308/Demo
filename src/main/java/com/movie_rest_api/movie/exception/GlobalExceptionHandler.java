package com.movie_rest_api.movie.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String,Object>> handlerForResourceNotFound(Exception ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
				"Message :",ex.getMessage(),
				"Status :", 404,
				"Error :","Resource Not Found",
				"Timestamp :", LocalDateTime.now()
				
				));
	}
}
