package com.karollotkowski.webcrawler.task;

import com.karollotkowski.webcrawler.scraper.PageScraper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ScrapPagesTaskSupplier {

  private final PageScraper pageScraper;

  public ScrapPagesTask get(@NonNull final String url) {
    return new ParallelScrapPagesTask(url, pageScraper);
  }
}
