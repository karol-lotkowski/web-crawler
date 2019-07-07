package com.karollotkowski.webcrawler.controller;

import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.service.WebPageCrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@AllArgsConstructor
@RestController
@Api(tags = "web-crawler-controller")
public class WebCrawlerController {

  private static final String BASE_URL = "/api/pageMap";

  private final WebPageCrawlerService webPageCrawlerService;

  @GetMapping(BASE_URL)
  @ApiOperation(value = "Generate a page map for given domain", response = PageMap.class)
  public PageMap pageMap(@RequestParam("domain") final String domain) {
    log.debug("Requested to generate page map for domain: {}", domain);

    return webPageCrawlerService.pageMap(domain);
  }
}
