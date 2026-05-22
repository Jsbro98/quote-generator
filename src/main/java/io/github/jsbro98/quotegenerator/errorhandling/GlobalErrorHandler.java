package io.github.jsbro98.quotegenerator.errorhandling;

import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ApiErrorResponse;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteAPIFailure;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(ZenQuoteBatchFailure.class)
  public ResponseEntity<ApiErrorResponse> handleBatchFailure(ZenQuoteBatchFailure ex) {
    return buildResponse(HttpStatus.BAD_GATEWAY, ex.getMessage());
  }

  @ExceptionHandler(ZenQuoteAPIFailure.class)
  public ResponseEntity<ApiErrorResponse> handleAPIFailure(ZenQuoteAPIFailure ex) {
    return buildResponse(HttpStatus.BAD_GATEWAY, ex.getMessage());
  }

  @ExceptionHandler(NoResourceFoundException.class)
  public ResponseEntity<ApiErrorResponse> handleNotFound(NoResourceFoundException ex) {
    return buildResponse(HttpStatus.NOT_FOUND, ex.getMessage());
  }

  // generic catch all for all through
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
  }

  // helper method to keep things DRY
  private ResponseEntity<ApiErrorResponse> buildResponse(HttpStatus status, String message) {
    ApiErrorResponse error = new ApiErrorResponse(status.value(), message, System.currentTimeMillis());
    return new ResponseEntity<>(error, status);
  }
}
