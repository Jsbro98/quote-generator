package io.github.jsbro98.quotegenerator.errorhandling;

import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ApiErrorResponse;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteAPIFailure;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

  // TODO: create private helpers for DRY

  @ExceptionHandler(ZenQuoteBatchFailure.class)
  public ResponseEntity<ApiErrorResponse> handleBatchFailure(ZenQuoteBatchFailure ex) {
    HttpStatus status = HttpStatus.BAD_GATEWAY;
    ApiErrorResponse error = new ApiErrorResponse(status.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, status);
  }

  @ExceptionHandler(ZenQuoteAPIFailure.class)
  public ResponseEntity<ApiErrorResponse> handleAPIFailure(ZenQuoteAPIFailure ex) {
    HttpStatus status = HttpStatus.BAD_GATEWAY;
    ApiErrorResponse error = new ApiErrorResponse(status.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, status);
  }

  // A fallback for any unexpected errors that don't get specifically caught
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
    ApiErrorResponse error = new ApiErrorResponse(status.value(), ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, status);
  }
}
