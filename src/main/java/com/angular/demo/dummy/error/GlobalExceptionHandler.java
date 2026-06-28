package com.angular.demo.dummy.error;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({AccessDeniedException.class, AuthorizationDeniedException.class})
  public void handleSecurityExceptions(Exception ex) throws Exception {
    throw ex;
  }

  @ExceptionHandler(Exception.class)
  public ProblemDetail handleGenericException(Exception ex) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred.");
    problemDetail.setTitle("Internal Server Error");
    return problemDetail;
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ProblemDetail handleResourceNotFound(ResourceNotFoundException ex) {
    ProblemDetail problemDetail =
        ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND, "Resource with id " + ex.getResourceId() + " not found.");
    problemDetail.setTitle("Not Found");
    problemDetail.setInstance(URI.create("/angular/" + ex.getResourceId()));
    problemDetail.setProperty("resourceId", ex.getResourceId());
    return problemDetail;
  }
}
