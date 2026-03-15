package io.github.jsbro98.quotegenerator;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class QuoteService {
  private final String baseUrl = "https://zenquotes.io/api";
  private final RestClient restClient = RestClient.builder()
          .baseUrl(baseUrl).build();

  public RandomQuote[] getRandomQuote() {
    return restClient.get()
            .uri("/random")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(RandomQuote[].class);
  }

}
