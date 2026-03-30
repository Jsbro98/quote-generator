package io.github.jsbro98.quotegenerator.controller;

import io.github.jsbro98.quotegenerator.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import io.github.jsbro98.quotegenerator.ZenQuoteRandomDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
  private QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping("/random")
  public ZenQuoteRandomDTO[] retrieveQuote() {
    return quoteService.getRandomQuote();
  }

  @GetMapping("/single-quote")
  public ZenQuoteDTO retrieveSavedQuotes() {
    return quoteService.getRandomSavedQuote();
  }
}
