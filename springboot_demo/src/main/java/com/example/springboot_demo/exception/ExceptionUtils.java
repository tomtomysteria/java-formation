package com.example.springboot_demo.exception;

import org.springframework.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionUtils {

  // Private constructor to prevent instantiation
  private ExceptionUtils() {
    throw new UnsupportedOperationException("Utility class");
  }

  private static final Logger logger = LogManager.getLogger(ExceptionUtils.class);

  public static ServiceOperationException handleAndThrowServiceOperationException(Exception e, String defaultMessage) {
    Throwable rootCause = findRootCause(e);
    String message = determineMessage(rootCause, defaultMessage);
    HttpStatus status = determineHttpStatus(rootCause);
    return new ServiceOperationException(message, e, status);
  }

  private static Throwable findRootCause(Throwable throwable) {
    Throwable cause = throwable;
    while (cause.getCause() != null && cause != cause.getCause()) {
      cause = cause.getCause();
    }
    return cause;
  }

  private static HttpStatus determineHttpStatus(Throwable rootCause) {
    if (rootCause instanceof RoleNotFoundException) {
      return HttpStatus.BAD_REQUEST;
    } else if (rootCause instanceof UserNotFoundException) {
      return HttpStatus.NOT_FOUND;
    } else {
      return HttpStatus.INTERNAL_SERVER_ERROR;
    }
  }

  private static String determineMessage(Throwable rootCause, String defaultMessage) {
    return rootCause.getMessage() != null ? rootCause.getMessage() : defaultMessage;
  }
}
