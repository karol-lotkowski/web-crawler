package com.karollotkowski.webcrawler.service;

import static java.util.concurrent.Executors.newCachedThreadPool;

import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.exception.PageMapCreationException;
import com.karollotkowski.webcrawler.task.ScrapPagesTaskSupplier;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class WebPageCrawlerService {

  private final ScrapPagesTaskSupplier scrapPagesTaskSupplier;

  private final ExecutorService executorService = newCachedThreadPool();

  public PageMap pageMap(@NonNull final String domain) {
    log.debug("Preparing page map for domain: {}", domain);

    try {
      return PageMap.builder().domain(domain).pages(getPages(domain).get()).build();

    } catch (final Exception e) {
      throw new PageMapCreationException(domain);
    }
  }

  private Future<Set<Page>> getPages(final String url) {
    return executorService.submit(scrapPagesTaskSupplier.get(url));
  }
}
