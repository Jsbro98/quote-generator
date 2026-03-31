package io.github.jsbro98.quotegenerator.controller;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
  private static final Logger log = LoggerFactory.getLogger(QuoteController.class);

  private final QuoteService quoteService;

  public QuoteController(QuoteService quoteService) {
    this.quoteService = quoteService;
  }

  @GetMapping("/random")
  public ZenQuoteRandomDTO retrieveQuote() {
    log.info("Retrieving random single quote from API");
    return quoteService.getRandomQuote();
  }

  @GetMapping("/single-quote")
  public ZenQuoteDTO retrieveSavedQuotes() {
    log.info("Retrieving single quote from saved batch");
    return quoteService.getRandomSavedQuote();
  }
}
