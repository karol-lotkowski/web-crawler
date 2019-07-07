package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.karollotkowski.webcrawler.domain.Links;
import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.domain.PageDetails;
import java.util.Set;
import org.junit.Test;

public class PageDetailsServiceTest {

  private final Fixtures fixtures = new Fixtures();

  private PageDetailsService pageDetailsService =
      new PageDetailsService(
          url ->
              PageDetails.builder()
                  .domainLinks(fixtures.domainSubPages)
                  .externalLinks(fixtures.externalDomainPages)
                  .build());

  @Test
  public void returnPagesForDomain() {
    // when
    final Set<Page> pages = pageDetailsService.getPages(fixtures.domain);

    // then
    assertThat(pages).hasSameElementsAs(Set.of(fixtures.page));
  }

  private class Fixtures {

    String domain = "http://domain.com";
    String externalDomain = "http://externaldomain.com";

    String domainSubPage1 = domain + "/sub-page-1";
    String domainSubPage2 = domain + "/sub-page-2";

    String externalDomainPage1 = externalDomain + "/page";

    Set<String> domainSubPages = Set.of(domainSubPage1, domainSubPage2);
    Set<String> externalDomainPages = Set.of(externalDomainPage1);

    Page page =
        Page.builder()
            .url(domain)
            .links(Links.builder().internal(domainSubPages).external(externalDomainPages).build())
            .build();
  }
}
