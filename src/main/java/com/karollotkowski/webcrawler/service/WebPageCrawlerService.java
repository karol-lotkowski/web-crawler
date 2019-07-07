package com.karollotkowski.webcrawler.service;

import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.exception.PageMapCreationException;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WebPageCrawlerService {

  private final PageDetailsService pageDetailsService;

  public PageMap pageMap(@NonNull final String domain) {
    log.debug("Preparing page map for domain: {}", domain);

    try {
      return PageMap.builder().domain(domain).pages(pageDetailsService.getLinks(domain)).build();

    } catch (final Exception e) {
      throw new PageMapCreationException(domain);
    }
  }
}
