package com.karollotkowski.webcrawler.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Links {

  private final Set<String> internal;
  private final Set<String> external;
}
