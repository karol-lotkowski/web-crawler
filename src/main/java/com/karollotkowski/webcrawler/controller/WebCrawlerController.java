package com.karollotkowski.webcrawler.controller;

import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.service.WebPageCrawlerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
public class WebCrawlerController {

  private static final String BASE_URL = "/api/pageMap";

  private final WebPageCrawlerService webPageCrawlerService;

  @GetMapping(BASE_URL)
  public PageMap pageMap(@RequestParam("domain") final String domain) {
    log.debug("Requested to generate page map for domain: {}", domain);

    return webPageCrawlerService.pageMap(domain);
  }
}
