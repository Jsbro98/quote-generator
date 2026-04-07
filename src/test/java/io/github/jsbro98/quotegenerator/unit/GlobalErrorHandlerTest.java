package io.github.jsbro98.quotegenerator.unit;

import io.github.jsbro98.quotegenerator.errorhandling.GlobalErrorHandler;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ApiErrorResponse;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GlobalErrorHandlerTest {
  private final GlobalErrorHandler globalErrorHandler = new GlobalErrorHandler();

  @Test
  void shouldHandleBatchException() throws Exception {
    ZenQuoteBatchFailure exception = new ZenQuoteBatchFailure("Bad Batch!");
    ResponseEntity<ApiErrorResponse> response = globalErrorHandler.handleBatchFailure(exception);

    assertNotNull(response.getBody());
    assertEquals(HttpStatus.BAD_GATEWAY, response.getStatusCode());
    assertEquals("Bad Batch!", response.getBody().message());
  }

  @Test
  void shouldHandleGeneralException() throws Exception {
    Exception exception = new Exception("Bad Exception!");
    ResponseEntity<ApiErrorResponse> response = globalErrorHandler.handleGenericException(exception);

    assertNotNull(response.getBody());
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("Bad Exception!", response.getBody().message());
  }
}
