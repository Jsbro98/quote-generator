package io.github.jsbro98.quotegenerator.repository;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;

@Repository
public class QuoteRepository {
  private static final Logger log = LoggerFactory.getLogger(QuoteRepository.class);

  private final Deque<ZenQuoteDTO> quotes = new ArrayDeque<>();
  private static final int REFILL_THRESHOLD = 3;

  public void saveQuotes(Deque<ZenQuoteDTO> quotes) {
    log.debug("saving {} quotes to repo", quotes.size());
    this.quotes.addAll(quotes);
  }

  public void resetQuotes() {
    quotes.clear();
  }

  public Deque<ZenQuoteDTO> getSavedQuotes() {
    return quotes;
  }

  public ZenQuoteDTO serveSavedQuote() {
    return quotes.poll();
  }

  public boolean quotesAreGettingLow() {
    return quotes.size() < REFILL_THRESHOLD;
  }

  public int howManyQuotesLeft() {
    return quotes.size();
  }
}
