package io.github.jsbro98.quotegenerator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

  public QuoteController() {}

  @GetMapping
  public String gimmieAQuote() {
    return "Here's your quote, bub!";
  }
}
