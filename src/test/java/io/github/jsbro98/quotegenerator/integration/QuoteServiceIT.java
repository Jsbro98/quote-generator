package io.github.jsbro98.quotegenerator.integration;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuoteServiceIT {

  @Autowired
  private QuoteService quoteService;

  @Test
  public void shouldGetRandomQuote() {
    ZenQuoteRandomDTO quote = quoteService.getRandomQuote();
    assertNotNull(quote, "quote must be present");
  }

  // TODO: refactor this into nested or multiple tests
  @Test
  public void shouldGet_QuotesFromBatch() {
    await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> assertNotNull(quoteService.getRandomSavedQuote()));

    ZenQuoteDTO quoteOne = quoteService.getRandomSavedQuote();
    ZenQuoteDTO quoteTwo = quoteService.getRandomSavedQuote();
    ZenQuoteDTO quoteThree = quoteService.getRandomSavedQuote();

    assertAll("All quotes are not null",
            () -> assertNotNull(quoteOne),
            () -> assertNotNull(quoteTwo),
            () -> assertNotNull(quoteThree)
    );

    assertAll("All quotes are not the same object",
            () -> assertNotSame(quoteOne, quoteTwo),
            () -> assertNotSame(quoteOne, quoteThree),
            () -> assertNotSame(quoteTwo, quoteThree)
    );

    assertAll("All quotes must be different",
            () -> assertNotEquals(quoteOne, quoteTwo),
            () -> assertNotEquals(quoteOne, quoteThree),
            () -> assertNotEquals(quoteTwo, quoteThree)
    );
  }
}
