package io.github.jsbro98.quotegenerator.service;

import io.github.jsbro98.quotegenerator.ZenQuotesClientAPI;
import io.github.jsbro98.quotegenerator.dtorecords.ZenQuoteDTO;
import io.github.jsbro98.quotegenerator.errorhandling.customerrors.ZenQuoteBatchFailure;
import io.github.jsbro98.quotegenerator.repository.QuoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class RefreshService {
  private static final Logger log = LoggerFactory.getLogger(RefreshService.class);

  private final QuoteRepository quoteRepository;
  private final ZenQuotesClientAPI clientAPI;
  private final AtomicBoolean isRefreshing = new AtomicBoolean(false);

  public RefreshService(QuoteRepository quoteRepository, ZenQuotesClientAPI clientAPI) {
    this.quoteRepository = quoteRepository;
    this.clientAPI = clientAPI;
  }

  @Async
  public void refreshQuotes() {
    if (!isRefreshing.compareAndSet(false, true)) {
      log.info("refresh already in progress, skipping.");
      return;
    }

    try {
      log.debug("refreshing quotes");
      ArrayDeque<ZenQuoteDTO> newData = new ArrayDeque<>(
              Arrays.asList(clientAPI.getQuoteBatch()));

      if (newData.isEmpty()) {
        log.error("quote fetching came back empty");
        throw new ZenQuoteBatchFailure("Batch quote retrieval failed");
      }

      quoteRepository.saveQuotes(newData);
    } finally {
      isRefreshing.set(false);
    }
  }

}
