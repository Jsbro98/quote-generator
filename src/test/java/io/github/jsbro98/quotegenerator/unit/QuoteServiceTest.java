package io.github.jsbro98.quotegenerator.unit;

import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class QuoteServiceTest {

  @Mock
  ZenQuotesClientAPI clientAPI;

  @Mock
  QuoteRepository repository;

  private QuoteService quoteService;

  @BeforeEach
  public void setup() {
    quoteService = new QuoteService(clientAPI, repository);
  }

  @Test
  public void shouldGetRandomQuote() {
    ZenQuoteRandomDTO mockQuote = mock(ZenQuoteRandomDTO.class);
    doReturn(mockQuote).when(clientAPI).randomQuote();

    ZenQuoteRandomDTO quote = quoteService.getRandomQuote();
    assertNotNull(quote);
    verify(clientAPI).randomQuote();
  }

  @Test
  void shouldThrowExceptionWhenBatchIsEmpty() {
    doReturn(new ZenQuoteDTO[]{}).when(clientAPI).getQuoteBatch();

    assertThrows(ZenQuoteBatchFailure.class, () -> {
      quoteService.initialize();
    });
  }
}
