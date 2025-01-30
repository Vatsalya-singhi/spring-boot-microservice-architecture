package com.vatsu.cloud_gateway.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallBackMethodController {

    private static final Logger logger = LoggerFactory.getLogger(FallBackMethodController.class);

    @GetMapping("/users")
    public ResponseEntity<String> userFallback() {
        logger.info("Fallback method for User Service triggered.");
        return ResponseEntity.ok("User Service is currently unavailable. Please try again later.");
    }

    @GetMapping("/departments")
    public ResponseEntity<String> departmentFallback() {
        logger.info("Fallback method for Department Service triggered.");
        return ResponseEntity.ok("Department Service is currently unavailable. Please try again later.");
    }
}
