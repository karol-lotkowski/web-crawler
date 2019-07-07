package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.karollotkowski.webcrawler.domain.Elements;
import com.karollotkowski.webcrawler.domain.Links;
import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.domain.PageDetails;
import com.karollotkowski.webcrawler.domain.StaticContent;
import com.karollotkowski.webcrawler.scraper.PageScraper;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PageDetailsServiceTest {

  private final Fixtures fixtures = new Fixtures();

  @InjectMocks private PageDetailsService pageDetailsService;

  @Mock private PageScraper pageScraper;

  @Before
  public void setUp() {
    given(pageScraper.getPageDetails(fixtures.domain)).willReturn(fixtures.pageDetailsForDomain);
    given(pageScraper.getPageDetails(fixtures.domainSubPage))
        .willReturn(fixtures.pageDetailsForSubPage);
  }

  @Test
  public void returnTwoPagesForDomain() {
    // when
    final Set<Page> pages = pageDetailsService.getPages(fixtures.domain);

    // then
    assertThat(pages).hasSameElementsAs(fixtures.expectedPages);
  }

  private class Fixtures {

    String domain = "http://domain.com";
    String externalDomain = "http://externaldomain.com";

    String domainSubPage = domain + "/sub-page";
    Set<String> domainSubPages = Set.of(domainSubPage);

    String externalDomainPage1 = externalDomain + "/page";
    String externalDomainPage2 = externalDomain + "/page2";
    Set<String> externalDomainPages = Set.of(externalDomainPage1);

    String image = domain + "/content/image.jpg";
    Set<String> images = Set.of(image);

    String pdf = domain + "/documents/info.pdf";
    Set<String> pdfs = Set.of(pdf);

    PageDetails pageDetailsForDomain =
        PageDetails.builder()
            .domainLinks(domainSubPages)
            .externalLinks(externalDomainPages)
            .images(images)
            .pdfs(pdfs)
            .build();

    PageDetails pageDetailsForSubPage =
        PageDetails.builder()
            .domainLinks(Set.of())
            .externalLinks(Set.of(externalDomainPage2))
            .images(Set.of())
            .pdfs(Set.of())
            .build();;

    Page pageForDomain =
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

    Page pageForSubPage =
        Page.builder()
            .url(domainSubPage)
            .elements(
                Elements.builder()
                    .links(
                        Links.builder()
                            .internal(Set.of())
                            .external(Set.of(externalDomainPage2))
                            .build())
                    .content(StaticContent.builder().images(Set.of()).pdfs(Set.of()).build())
                    .build())
            .build();

    Set<Page> expectedPages = Set.of(pageForDomain, pageForSubPage);
  }
}
