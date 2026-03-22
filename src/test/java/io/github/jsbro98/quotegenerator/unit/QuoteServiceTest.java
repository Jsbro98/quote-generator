package io.github.jsbro98.quotegenerator.unit;

import io.github.jsbro98.quotegenerator.RandomQuote;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
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
