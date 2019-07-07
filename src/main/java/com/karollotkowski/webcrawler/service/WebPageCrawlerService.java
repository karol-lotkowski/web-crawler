package com.karollotkowski.webcrawler.service;

import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.exception.PageMapCreationException;
import java.util.Set;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebPageCrawlerService {

  public PageMap pageMap(@NonNull final String domain) {
    log.debug("Preparing page map for domain: {}", domain);

    try {
      return PageMap.builder().domain(domain).pages(mockPages(domain)).build();

    } catch (final Exception e) {
      throw new PageMapCreationException(domain);
    }
  }

  private Set<String> mockPages(@NonNull final String domain) {
    return Set.of(domain + "/sub-page1", domain + "/sub-page2");
  }
}
