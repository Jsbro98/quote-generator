package io.github.jsbro98.quotegenerator.errorhandling;

import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ApiErrorResponse;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(ZenQuoteBatchFailure.class)
  public ResponseEntity<ApiErrorResponse> handleBatchFailure(ZenQuoteBatchFailure ex) {
    ApiErrorResponse error = new ApiErrorResponse(400, ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  // A fallback for any unexpected errors that don't get specifically caught
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorResponse> handleGenericException(Exception ex) {
    ApiErrorResponse error = new ApiErrorResponse(500, ex.getMessage(), System.currentTimeMillis());
    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
