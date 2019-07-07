package com.karollotkowski.webcrawler.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageMap {

  private final String domain;
  private final Set<String> pages;
}
