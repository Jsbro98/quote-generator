package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.QuoteFromListRequest;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import io.github.jsbro98.quotegenerator.RandomQuote;
import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@Service
public class QuoteService {
  private final ZenQuotesClientAPI clientAPI;
  private final QuoteRepository quoteRepository;
  private static final Random SERVICE_RANDOM = new Random();

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
  // TODO: change this method to have savedQuotes here use a Deque
  //  and iterate through the stack to retrieve them instead of using
  //  a random call and removing to ensure no duplicate quotes
  public QuoteFromListRequest getRandomSavedQuote() {
    ArrayList<QuoteFromListRequest> savedQuotes = (ArrayList<QuoteFromListRequest>) quoteRepository.getSavedQuotes();
    int size = savedQuotes.size();
    QuoteFromListRequest quote = savedQuotes.get(SERVICE_RANDOM.nextInt(size));
    savedQuotes.remove(quote);
    return quote;
  }

  private void fetchNewQuotes(QuoteRepository quoteRepository) {
    ArrayList<QuoteFromListRequest> newData = new ArrayList<>(
            Arrays.asList(clientAPI.getQuoteBatch()));
    quoteRepository.resetQuotes();
    quoteRepository.saveQuotes(newData);
  }
}
