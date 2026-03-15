package io.github.jsbro98.quotegenerator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(QuoteController.class)
public class QuoteControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  QuoteService quoteService;

  @Test
  public void itReturnsEndpointRandom() throws Exception {
    RandomQuote[] testQuoteData = {
            new RandomQuote("Be brave", "Myself", "html...")
    };

    when(quoteService.getRandomQuote()).thenReturn(testQuoteData);

    mockMvc.perform(get("/quotes/random")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
