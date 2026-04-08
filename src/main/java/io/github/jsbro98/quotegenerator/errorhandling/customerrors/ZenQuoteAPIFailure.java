package io.github.jsbro98.quotegenerator.errorhandling.customerrors;

public class ZenQuoteAPIFailure extends RuntimeException {
  public ZenQuoteAPIFailure(String message) {
    super(message);
  }
}
