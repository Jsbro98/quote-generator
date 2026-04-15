package io.github.jsbro98.quotegenerator.integration;

import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import io.github.jsbro98.quotegenerator.service.RefreshService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.*;

@SpringBootTest
class RefreshServiceIT {

  @MockitoBean
  ZenQuotesClientAPI clientAPI;

  @MockitoSpyBean
  QuoteRepository quoteRepository;

  @Autowired
  RefreshService refreshService;

  @BeforeEach
  void cleanUp() {
    quoteRepository.resetQuotes();
  }

  @Test
  void shouldHandleRefresh_whenAskedTo() {
    ZenQuoteDTO[] fakeData = {
            new ZenQuoteDTO("Test Quote One", "Test", "20", "<html></html>"),
            new ZenQuoteDTO("Test Quote Two", "Test", "20", "<html></html>")
    };
    when(clientAPI.getQuoteBatch()).thenReturn(fakeData);

    refreshService.refreshQuotes();

    await()
            .atMost(5, SECONDS)
            .until(() -> quoteRepository.howManyQuotesLeft() == 2);
  }

  @Test
  void shouldThrow_whenAPIGivesEmptyData() {
    ZenQuoteDTO[] fakeData = {};
    when(clientAPI.getQuoteBatch()).thenReturn(fakeData);

    refreshService.refreshQuotes();

    await()
            .atMost(5, SECONDS)
            // is the dataset still empty? (not accepted)
            .until(() -> quoteRepository.howManyQuotesLeft() == 0);

    // if it threw and aborted, it should've never saved the data
    verify(quoteRepository, never()).saveQuotes(any());
  }
}