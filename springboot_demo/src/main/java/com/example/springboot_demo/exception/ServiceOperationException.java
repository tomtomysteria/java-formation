package com.example.springboot_demo.exception;

import org.springframework.http.HttpStatus;

public class ServiceOperationException extends RuntimeException {
  private final HttpStatus status;

  public ServiceOperationException(String message) {
    super(message);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public ServiceOperationException(String message, Throwable cause) {
    super(message, cause);
    this.status = HttpStatus.INTERNAL_SERVER_ERROR;
  }

  public ServiceOperationException(String message, Throwable cause, HttpStatus status) {
    super(message, cause);
    this.status = status;
  }

  public HttpStatus getStatus() {
    return status;
  }
}
