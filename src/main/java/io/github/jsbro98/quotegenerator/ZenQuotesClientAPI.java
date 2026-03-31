package io.github.jsbro98.quotegenerator;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ZenQuotesClientAPI {
  private final String baseUrl = "https://zenquotes.io/api";
  private final RestClient restClient = RestClient.builder()
          .baseUrl(baseUrl).build();

  public ZenQuoteRandomDTO randomQuote() {
    ZenQuoteRandomDTO[] response = restClient.get()
            .uri("/random")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteRandomDTO[].class);
  }

  public ZenQuoteDTO[] getQuoteBatch() {
    return restClient.get()
            .uri("/quotes")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteDTO[].class);
  }
}
