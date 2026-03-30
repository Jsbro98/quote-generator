package io.github.jsbro98.quotegenerator.integration;

import io.github.jsbro98.quotegenerator.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.controller.QuoteController;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
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
public class QuoteControllerIT {
  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  QuoteService quoteService;

  @BeforeEach
  void setUp() {
    ZenQuoteRandomDTO[] fakeQuote = new ZenQuoteRandomDTO[]{
            new ZenQuoteRandomDTO("Test quote", "Test author", "<html>")
    };

    when(quoteService.getRandomQuote()).thenReturn(fakeQuote);
  }

  @Test
  public void itReturnsARandomQuote_whenCallingRandomEndpoint() throws Exception {
    mockMvc.perform(get("/quotes/random")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void itReturnsAQuote_whenCallingSingleQuoteEndpoint() throws Exception {
    mockMvc.perform(get("/quotes/single-quote")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  public void shouldReturn200() throws Exception {
    mockMvc.perform(get("/quotes/random"))
            .andExpect(status().is(200));
  }

  @Test
  public void shouldReturnJSON() throws Exception {
    mockMvc.perform(get("/quotes/random"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
