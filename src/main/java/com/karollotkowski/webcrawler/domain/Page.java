package com.karollotkowski.webcrawler.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Page {

  private final String url;
  private final Links links;

  public static Page from(@NonNull final String url, @NonNull final PageDetails pageDetails) {
    return Page.builder()
        .url(url)
        .links(
            Links.builder()
                .internal(pageDetails.domainLinks)
                .external(pageDetails.getExternalLinks())
                .build())
        .build();
  }
}
