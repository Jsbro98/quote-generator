package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class QuoteService {
  private static final Logger log = LoggerFactory.getLogger(QuoteService.class);

  private final QuoteRepository quoteRepository;
  private final RefreshService refreshService;
  private final ZenQuotesClientAPI clientAPI;

  public QuoteService(ZenQuotesClientAPI clientAPI, QuoteRepository quoteRepository, RefreshService refreshService) {
    this.quoteRepository = quoteRepository;
    this.refreshService = refreshService;
    this.clientAPI = clientAPI;
  }

  // created to remove initialization out of the constructor
  @EventListener(ApplicationReadyEvent.class)
  @Async
  public void initialize() {
    log.info("Application ready. Performing initial quote fetch.");
    refreshService.refreshQuotes();
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

  private void refetchIfNeeded() {
    if (quoteRepository.quotesAreGettingLow()) {
      log.info("fetching more quotes. Repo size: {}", quoteRepository.howManyQuotesLeft());
      refreshService.refreshQuotes();
    }
  }

}
