package io.github.jsbro98.quotegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
class QuoteGeneratorApplicationTests {
  MockMvc mvc = MockMvcBuilders.standaloneSetup(new QuoteController()).build();
  RestTestClient testClient = RestTestClient.bindTo(mvc).build();

  @Test
  void getRequestTest() {
    testClient.get().uri("/quotes")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody();
  }

}
