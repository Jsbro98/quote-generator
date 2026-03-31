package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    fetchNewQuotes(this.quoteRepository);
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
      fetchNewQuotes(this.quoteRepository);
    }
  }

  private void fetchNewQuotes(QuoteRepository quoteRepository) {
    ArrayDeque<ZenQuoteDTO> newData = new ArrayDeque<>(
            Arrays.asList(clientAPI.getQuoteBatch()));
    quoteRepository.saveQuotes(newData);
  }
}
