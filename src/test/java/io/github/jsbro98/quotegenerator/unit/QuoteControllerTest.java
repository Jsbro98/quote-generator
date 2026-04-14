package io.github.jsbro98.quotegenerator.unit;

import io.github.jsbro98.quotegenerator.controller.QuoteController;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteRandomDTO;
import io.github.jsbro98.quotegenerator.service.QuoteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(QuoteController.class)
class QuoteControllerTest {
  @Autowired
  MockMvc mockMvc;

  @MockitoBean
  QuoteService quoteService;

  @BeforeEach
  void setUp() {
    doReturn(mock(ZenQuoteRandomDTO.class)).when(quoteService).getRandomQuote();
  }

  @Test
  void itReturnsARandomQuote_whenCallingRandomEndpoint() throws Exception {
    mockMvc.perform(get("/quotes/random")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void itReturnsAQuote_whenCallingSingleQuoteEndpoint() throws Exception {
    mockMvc.perform(get("/quotes/single-quote")
                    .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
  }

  @Test
  void shouldReturn200() throws Exception {
    mockMvc.perform(get("/quotes/random"))
            .andExpect(status().is(200));
  }

  @Test
  void shouldReturnJSON() throws Exception {
    mockMvc.perform(get("/quotes/random"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
