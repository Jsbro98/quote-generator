package io.github.jsbro98.quotegenerator.dtorecords;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ZenQuoteDTO(
        @JsonProperty("q") String quote,
        @JsonProperty("a") String author,
        @JsonProperty("c") String chars,
        @JsonProperty("h") String html) {
}
