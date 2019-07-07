package com.karollotkowski.webcrawler.service;

import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.scraper.PageScraper;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class PageDetailsService {

  private final PageScraper pageScraper;

  private final Set<String> visitedUrls = new HashSet<>();

  Set<Page> getPages(@NonNull final String url) {
    log.debug("Getting links for URL: {}", url);

    return getPageAndSubPages(url);
  }

  private Set<Page> getPageAndSubPages(@NonNull final String url) {

    if (!visitedUrls.contains(url)) {
      visitedUrls.add(url);

      try {
        final Page page = Page.from(url, pageScraper.getPageDetails(url));

        final Set<String> linksToVisit = page.getElements().getLinks().getInternal();

        final Set<Page> pages = new HashSet<>(linksToVisit.size() + 1);
        pages.add(page);

        pages.addAll(getSubPages(linksToVisit));

        return pages;

      } catch (final Exception ex) {
        log.error("Cannot prepare info about page: {}", url, ex);
      }
    }
    return Set.of();
  }

  private Set<Page> getSubPages(@NonNull final Set<String> pagesToVisit) {
    if (pagesToVisit.isEmpty()) {
      return Set.of();
    }

    return pagesToVisit.stream()
        .map(this::getPageAndSubPages)
        .flatMap(Collection::stream)
        .collect(Collectors.toSet());
  }
}
