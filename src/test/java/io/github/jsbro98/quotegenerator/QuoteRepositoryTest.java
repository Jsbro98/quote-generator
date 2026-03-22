package io.github.jsbro98.quotegenerator;

import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuoteRepositoryTest {

  @Autowired
  private QuoteRepository quoteRepository;

  private static ArrayList<QuoteFromListRequest> quotes;

  @BeforeAll
  static void beforeAll() {
    quotes = new ArrayList<>();
    quotes.add(new QuoteFromListRequest("Test 0", "Beep", "265", "<h1>HTML<h1/>"));
    quotes.add(new QuoteFromListRequest("Test 1", "Bloop", "265", "<h1>HTML<h1/>"));
    quotes.add(new QuoteFromListRequest("Test 2", "Blep", "265", "<h1>HTML<h1/>"));
  }

  @BeforeEach
  void setUp() {
    quoteRepository.resetQuotes();
  }

  @Test
  void givenAListOfQuotes_whenSavingQuotes_thenQuotesAreSaved() {
    quoteRepository.saveQuotes(quotes);
    assertNotNull(quoteRepository.getSavedQuotes());
    assertEquals(quotes.size(), quoteRepository.getSavedQuotes().size());
  }

  @Test
  void resetQuotes_shouldClearQuotes() {
    quoteRepository.saveQuotes(quotes);
    assertNotNull(quoteRepository.getSavedQuotes());

    quoteRepository.resetQuotes();
    assertTrue(quoteRepository.getSavedQuotes().isEmpty());
  }

  @Test
  void getSavedQuotes_shouldReturnSavedQuotes() {
    quoteRepository.saveQuotes(quotes);
    assertNotNull(quoteRepository.getSavedQuotes());
  }
}