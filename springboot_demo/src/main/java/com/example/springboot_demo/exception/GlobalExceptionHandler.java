package com.example.springboot_demo.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

  private static final String TIMESTAMP = "timestamp";
  private static final String MESSAGE = "message";
  private static final String DETAILS = "details";

  @ExceptionHandler(ServiceOperationException.class)
  public ResponseEntity<Object> handleServiceOperationException(ServiceOperationException ex, WebRequest request) {
    logger.error("Service operation error: {}", ex.getMessage(), ex);

    Map<String, Object> response = new HashMap<>();
    response.put(TIMESTAMP, LocalDateTime.now());
    response.put(MESSAGE, ex.getMessage());
    response.put(DETAILS, request.getDescription(false));

    return new ResponseEntity<>(response, ex.getStatus());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
    if (ex instanceof ResponseStatusException responsestatusexception) {
      // Let ResponseStatusException propagate to the client
      throw responsestatusexception;
    }
    logger.error("An unexpected error occurred: {}", ex.getMessage(), ex);
    return new ResponseEntity<>("An unexpected error occurred. Please try again later.",
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
