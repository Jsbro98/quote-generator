package io.github.jsbro98.quotegenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class QuoteRepository {
  private final Map<Integer, RandomQuote> quotes;

  public QuoteRepository() {
    quotes = new HashMap<>();
  }

  @Autowired
  public QuoteRepository(HashMap<Integer, RandomQuote> quotes) {
    this.quotes = quotes;
  }
}
