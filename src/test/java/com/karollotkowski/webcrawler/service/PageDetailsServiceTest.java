package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.karollotkowski.webcrawler.domain.Elements;
import com.karollotkowski.webcrawler.domain.Links;
import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.domain.PageDetails;
import com.karollotkowski.webcrawler.domain.StaticContent;
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
                  .images(fixtures.images)
                  .pdfs(fixtures.pdfs)
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
    Set<String> domainSubPages = Set.of(domainSubPage1, domainSubPage2);

    String externalDomainPage1 = externalDomain + "/page";
    Set<String> externalDomainPages = Set.of(externalDomainPage1);

    String image = domain + "/content/image.jpg";
    Set<String> images = Set.of(image);

    String pdf = domain + "/documents/info.pdf";
    Set<String> pdfs = Set.of(pdf);

    Page page =
        Page.builder()
            .url(domain)
            .elements(
                Elements.builder()
                    .links(
                        Links.builder()
                            .internal(domainSubPages)
                            .external(externalDomainPages)
                            .build())
                    .content(StaticContent.builder().images(images).pdfs(pdfs).build())
                    .build())
            .build();
  }
}
