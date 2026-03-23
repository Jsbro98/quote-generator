package io.github.jsbro98.quotegenerator.repository;

import io.github.jsbro98.quotegenerator.QuoteFromListRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayDeque;
import java.util.Deque;

@Repository
public class QuoteRepository {
  private final Deque<QuoteFromListRequest> quotes =  new ArrayDeque<>();
  private final int REFILL_THRESHOLD = 3;

  public void saveQuotes(ArrayDeque<QuoteFromListRequest> quotes) {
    this.quotes.addAll(quotes);
  }

  public void resetQuotes() {
    quotes.clear();
  }

  public Deque<QuoteFromListRequest> getSavedQuotes() {
    return quotes;
  }
}
