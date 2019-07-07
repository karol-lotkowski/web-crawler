package com.karollotkowski.webcrawler.domain;

import java.util.Set;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PageDetails {

  public Set<String> domainLinks;
  public Set<String> externalLinks;
  public Set<String> images;
  public Set<String> pdfs;
}
