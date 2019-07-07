package com.karollotkowski.webcrawler.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Value
@Builder
public class Page {

  private final String url;
  private final Elements elements;

  public static Page from(@NonNull final String url, @NonNull final PageDetails pageDetails) {
    return Page.builder()
        .url(url)
        .elements(
            Elements.builder()
                .links(
                    Links.builder()
                        .internal(pageDetails.domainLinks)
                        .external(pageDetails.getExternalLinks())
                        .build())
                .content(
                    StaticContent.builder()
                        .images(pageDetails.getImages())
                        .pdfs(pageDetails.getPdfs())
                        .build())
                .build())
        .build();
  }
}
