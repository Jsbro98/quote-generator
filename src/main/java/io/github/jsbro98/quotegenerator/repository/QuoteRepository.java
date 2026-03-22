package io.github.jsbro98.quotegenerator.repository;

import io.github.jsbro98.quotegenerator.QuoteFromListRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class QuoteRepository {
  private final List<QuoteFromListRequest> quotes;

  public QuoteRepository() {
    quotes = new ArrayList<>();
  }

  @Autowired
  public QuoteRepository(ArrayList<QuoteFromListRequest> quotes) {
    this.quotes = quotes;
  }

  public void saveQuotes(ArrayList<QuoteFromListRequest> quotes) {
    this.quotes.addAll(quotes);
  }

  public void resetQuotes() {
    quotes.clear();
  }

  public List<QuoteFromListRequest> getSavedQuotes() {
    return quotes;
  }
}
