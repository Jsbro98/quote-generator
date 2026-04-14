package io.github.jsbro98.quotegenerator;

import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteAPIFailure;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ZenQuotesClientAPI {
  private final RestClient restClient;

  // @Value is used here to allow url injection for testing/stubbing
  public ZenQuotesClientAPI(@Value("${zenquotes.base-url:https://zenquotes.io/api}") String baseURL) {
    this.restClient = RestClient.builder()
            .baseUrl(baseURL)
            .build();
  }

  // ZenQuotes /random API returns an array of JSON with 1 item
  public ZenQuoteRandomDTO randomQuote() {
    ZenQuoteRandomDTO[] response = restClient.get()
            .uri("/random")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteRandomDTO[].class);

    if (response == null || response.length == 0) {
      throw new ZenQuoteAPIFailure("ZenQuotes failed to return a random quote");
    }

    return response[0];
  }

  // this external API call returns a batch of ~50 random quotes
  public ZenQuoteDTO[] getQuoteBatch() {
    ZenQuoteDTO[] batchQuoteResponse = restClient.get()
            .uri("/quotes")
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .body(ZenQuoteDTO[].class);

    if (batchQuoteResponse == null || batchQuoteResponse.length == 0) {
      throw new ZenQuoteAPIFailure("ZenQuotes failed to return quote batch");
    }

    return batchQuoteResponse;
  }
}
