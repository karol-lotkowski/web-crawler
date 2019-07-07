package com.karollotkowski.webcrawler.runner;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WebCrawlerCommandLineRunner implements CommandLineRunner {

  //  private final WebPageCrawlerService pageMapService;

  @Override
  public void run(String... args) throws Exception {
    //    log.info("Application started with command-line arguments: {} .", Arrays.toString(args));

    //    final PageMap pageMap = pageMapService.pageMap(args[0]);
    //    log.info("Page map: {}", pageMap);
  }
}
