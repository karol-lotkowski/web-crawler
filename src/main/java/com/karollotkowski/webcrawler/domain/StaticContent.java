package com.karollotkowski.webcrawler.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StaticContent {

  private final Set<String> images;
  private final Set<String> pdfs;
}
