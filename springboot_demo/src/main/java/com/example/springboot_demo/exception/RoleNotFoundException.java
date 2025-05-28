package com.example.springboot_demo.exception;

public class RoleNotFoundException extends ServiceOperationException {
  public RoleNotFoundException(String message) {
    super(message);
  }
}
