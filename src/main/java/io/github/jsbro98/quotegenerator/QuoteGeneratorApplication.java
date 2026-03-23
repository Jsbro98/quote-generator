package io.github.jsbro98.quotegenerator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class QuoteGeneratorApplication {
  static void main(String[] args) {
    SpringApplication.run(QuoteGeneratorApplication.class, args);
  }
}
