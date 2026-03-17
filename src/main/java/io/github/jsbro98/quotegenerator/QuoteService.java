package io.github.jsbro98.quotegenerator;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class QuoteService {
  private final ZenQuotesClientAPI clientAPI;
  private final QuoteRepository quoteRepository;

  public QuoteService(ZenQuotesClientAPI clientAPI, QuoteRepository quoteRepository) {
    this.clientAPI = clientAPI;
    this.quoteRepository = quoteRepository;
    fetchNewQuotes(this.quoteRepository);
  }

  public RandomQuote[] getRandomQuote() {
    // TODO: maybe create a DTO for this?
    return clientAPI.randomQuote();
  }

  private void fetchNewQuotes(QuoteRepository quoteRepository) {
    ArrayList<QuoteFromListRequest> newData = new ArrayList<>(
            Arrays.asList(clientAPI.getQuoteBatch()));
    quoteRepository.resetQuotes();
    quoteRepository.saveQuotes(newData);
  }
}
