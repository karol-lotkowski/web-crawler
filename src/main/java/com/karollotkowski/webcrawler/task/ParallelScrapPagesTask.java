package com.karollotkowski.webcrawler.task;

import static java.lang.Runtime.getRuntime;
import static java.util.stream.Collectors.toSet;

import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.scraper.PageScraper;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class ParallelScrapPagesTask implements ScrapPagesTask {

  private final String url;
  private final PageScraper pageScraper;

  private final Set<String> visitedUrls = new HashSet<>();
  private final ForkJoinPool forkJoinPool = new ForkJoinPool(getRuntime().availableProcessors());

  @Override
  public Set<Page> call() {
    log.debug("Getting pages for URL: {}", url);

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

        if (!linksToVisit.isEmpty()) {
          pages.addAll(
              forkJoinPool
                  .submit(
                      () ->
                          linksToVisit
                              .parallelStream()
                              .map(this::getPageAndSubPages)
                              .flatMap(Collection::stream)
                              .collect(toSet()))
                  .get());
        }
        return pages;

      } catch (final Exception ex) {
        log.error("Cannot prepare info about page: {}", url, ex);
      }
    }
    return Set.of();
  }
}
