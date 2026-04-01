package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Arrays;

@Service
public class QuoteService {
  private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

  private final ZenQuotesClientAPI clientAPI;
  private final QuoteRepository quoteRepository;

  public QuoteService(ZenQuotesClientAPI clientAPI, QuoteRepository quoteRepository) {
    this.clientAPI = clientAPI;
    this.quoteRepository = quoteRepository;
  }

  // created to remove initialization out of the constructor
  @EventListener(ApplicationReadyEvent.class)
  @Async
  public void initialize() {
    log.info("Application ready. Performing initial quote fetch.");
    fetchNewQuotes();
  }

  // external api call
  public ZenQuoteRandomDTO getRandomQuote() {
    log.info("Fetching random quote directly from API");
    return clientAPI.randomQuote();
  }

  // get a quote from saved batch in repo
  public ZenQuoteDTO getRandomSavedQuote() {
    ZenQuoteDTO quote = quoteRepository.serveSavedQuote();
    log.debug("getting saved quote {}", quote);
    refetchIfNeeded();
    return quote;
  }

  @Async
  private void refetchIfNeeded() {
    if (quoteRepository.quotesAreGettingLow()) {
      log.info("fetching more quotes. Repo size: {}", quoteRepository.howManyQuotesLeft());
      fetchNewQuotes();
    }
  }

  private void fetchNewQuotes() {
    ArrayDeque<ZenQuoteDTO> newData = new ArrayDeque<>(
            Arrays.asList(clientAPI.getQuoteBatch()));

    if (newData.isEmpty()) {
      log.error("Fetching new batch of quotes has failed");
      throw new ZenQuoteBatchFailure("Batch quote retrieval failed");
    }

    quoteRepository.saveQuotes(newData);
  }
}
