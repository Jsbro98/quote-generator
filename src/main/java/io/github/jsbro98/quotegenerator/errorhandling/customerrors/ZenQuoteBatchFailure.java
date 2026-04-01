package io.github.jsbro98.quotegenerator.errorhandling.customerrors;

public class ZenQuoteBatchFailure extends RuntimeException {
  public ZenQuoteBatchFailure(String message) {
    super(message);
  }
}
