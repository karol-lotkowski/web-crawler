package com.karollotkowski.webcrawler.exception;

import lombok.NonNull;

public class PageMapCreationException extends RuntimeException {

  public PageMapCreationException(@NonNull final String domain) {
    super("Cannot prepare page map for domain: " + domain);
  }
}
