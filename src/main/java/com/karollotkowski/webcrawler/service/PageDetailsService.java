package com.karollotkowski.webcrawler.service;

import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.scraper.PageScraper;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PageDetailsService {

  private final PageScraper pageScraper;

  Set<Page> getPages(@NonNull final String url) {
    log.debug("Getting links for URL: {}", url);

    return Set.of(Page.from(url, pageScraper.getPageDetails(url)));
  }
}
