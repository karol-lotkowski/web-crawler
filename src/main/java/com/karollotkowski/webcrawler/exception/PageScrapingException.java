package com.karollotkowski.webcrawler.exception;

import lombok.NonNull;

public class PageScrapingException extends RuntimeException {

  public PageScrapingException(@NonNull final String url) {
    super("Cannot scrape page for URL: " + url);
  }
}
