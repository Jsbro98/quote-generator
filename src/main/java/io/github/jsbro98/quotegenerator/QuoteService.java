package io.github.jsbro98.quotegenerator;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class QuoteService {
  private final RestClient restClient = RestClient.create();
  private final String baseUrl = "https://zenquotes.io/api/random";

  public String getQuote() {
    return restClient.get()
            .uri(baseUrl)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(String.class);
  }
}
