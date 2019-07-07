package com.karollotkowski.webcrawler.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Elements {

  private final Links links;
  private final StaticContent content;
}
