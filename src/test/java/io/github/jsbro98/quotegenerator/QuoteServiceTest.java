package io.github.jsbro98.quotegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuoteServiceTest {

  @Autowired
  private QuoteService quoteService;

  @Test
  public void shouldGetRandomQuote() {
    RandomQuote[] quote = quoteService.getRandomQuote();
    assertNotNull(quote);
    assertTrue(quote.length > 0);
  }
}
