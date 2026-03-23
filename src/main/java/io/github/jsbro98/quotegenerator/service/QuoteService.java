package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.QuoteFromListRequest;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import io.github.jsbro98.quotegenerator.RandomQuote;
import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuoteService {
  private final ZenQuotesClientAPI clientAPI;
  private final QuoteRepository quoteRepository;

  public QuoteService(ZenQuotesClientAPI clientAPI, QuoteRepository quoteRepository) {
    this.clientAPI = clientAPI;
    this.quoteRepository = quoteRepository;
    fetchNewQuotes(this.quoteRepository);
  }

  // external api call
  public RandomQuote[] getRandomQuote() {
    // TODO: maybe create a DTO for this?
    return clientAPI.randomQuote();
  }

  // get a quote from saved batch in repo
  public QuoteFromListRequest getRandomSavedQuote() {
    ArrayList<QuoteFromListRequest> savedQuotes = (ArrayList<QuoteFromListRequest>) quoteRepository.getSavedQuotes();
    int size = savedQuotes.size();
    QuoteFromListRequest quote = savedQuotes.get(SERVICE_RANDOM.nextInt(size));
    savedQuotes.remove(quote);
    return quote;
  }

  private void fetchNewQuotes(QuoteRepository quoteRepository) {
    ArrayDeque<QuoteFromListRequest> newData = new ArrayDeque<>(
            Arrays.asList(clientAPI.getQuoteBatch()));
    quoteRepository.saveQuotes(newData);
  }
}
