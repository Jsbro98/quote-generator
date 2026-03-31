package io.github.jsbro98.quotegenerator;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
public class ZenQuotesClientAPI {
  private final String baseUrl = "https://zenquotes.io/api";
  private final RestClient restClient = RestClient.builder()
          .baseUrl(baseUrl).build();

  // ZenQuotes /random API returns an array of JSON with 1 item
  public ZenQuoteRandomDTO randomQuote() {
    ZenQuoteRandomDTO[] response = restClient.get()
            .uri("/random")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteRandomDTO[].class);

    Objects.requireNonNull(response, "response must not be null");
    // return the 1 element in the array
    return response[0];
  }

  // this external API call returns a batch of ~50 random quotes
  public ZenQuoteDTO[] getQuoteBatch() {
    return restClient.get()
            .uri("/quotes")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteDTO[].class);
  }
}
