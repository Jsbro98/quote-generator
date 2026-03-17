package io.github.jsbro98.quotegenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public record QuoteFromListRequest(
        @JsonProperty("q") String quote,
        @JsonProperty("a") String author,
        @JsonProperty("c") int chars,
        @JsonProperty("h") String html) {
}
