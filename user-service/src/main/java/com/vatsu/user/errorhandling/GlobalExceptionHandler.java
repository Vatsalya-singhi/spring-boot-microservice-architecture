package com.vatsu.user.errorhandling;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	public ResponseEntity<Map<String, String>> handleResourceNotFound(InvalidConfigurationPropertyValueException e) {
		Map<String, String> errorResponse = new HashMap<>();
		errorResponse.put("error", e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
}
