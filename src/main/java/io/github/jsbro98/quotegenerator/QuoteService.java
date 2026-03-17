package io.github.jsbro98.quotegenerator;

import org.springframework.stereotype.Service;

@Service
public class QuoteService {
  private final ZenQuotesClientAPI clientAPI;

  public QuoteService(ZenQuotesClientAPI clientAPI) {
    this.clientAPI = clientAPI;
  }

  public RandomQuote[] getRandomQuote() {
    // TODO: maybe create a DTO for this?
    return clientAPI.randomQuote();
  }
}
