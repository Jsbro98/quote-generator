package io.github.jsbro98.quotegenerator.unit;

import io.github.jsbro98.quotegenerator.QuoteFromListRequest;
import io.github.jsbro98.quotegenerator.RandomQuote;
import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

  @Mock
  ZenQuotesClientAPI clientAPI;

  @Mock
  QuoteRepository repository;

  private QuoteService quoteService;

  @BeforeEach
  public void setup() {
    doReturn(new QuoteFromListRequest[]{}).when(clientAPI).getQuoteBatch();
    doReturn(new RandomQuote[]{}).when(clientAPI).randomQuote();
    quoteService = new QuoteService(clientAPI, repository);
  }

  @Test
  public void shouldGetRandomQuote() {
    RandomQuote[] quote = quoteService.getRandomQuote();
    assertNotNull(quote);
    verify(clientAPI).randomQuote();
  }
}
