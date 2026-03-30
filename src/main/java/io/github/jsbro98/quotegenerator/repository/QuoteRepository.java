package io.github.jsbro98.quotegenerator.repository;

import io.github.jsbro98.quotegenerator.ZenQuoteDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;

@Repository
public class QuoteRepository {
  private final Deque<ZenQuoteDTO> quotes =  new ArrayDeque<>();
  private final int REFILL_THRESHOLD = 3;

  public void saveQuotes(ArrayDeque<ZenQuoteDTO> quotes) {
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
