package com.lib.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.lib.util.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<String> badRequestException(BadRequestException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("your id is allready exist");
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<String>> resourceNotFoundException(ResourceNotFoundException exception){
		 ApiResponse<String> apiResponse = new ApiResponse<>(false, "not found your id ", exception.getMessage());

		return new ResponseEntity<ApiResponse<String>>(apiResponse, HttpStatus.NOT_FOUND);
	}
}
