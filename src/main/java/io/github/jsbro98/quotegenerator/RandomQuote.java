package io.github.jsbro98.quotegenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RandomQuote(
        @JsonProperty("q") String content,
        @JsonProperty("a") String author,
        @JsonProperty("h") String html) {
}
