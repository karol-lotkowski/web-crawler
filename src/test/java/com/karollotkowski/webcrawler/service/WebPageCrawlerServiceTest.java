package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.karollotkowski.webcrawler.domain.Elements;
import com.karollotkowski.webcrawler.domain.Links;
import com.karollotkowski.webcrawler.domain.Page;
import com.karollotkowski.webcrawler.domain.PageMap;
import com.karollotkowski.webcrawler.domain.StaticContent;
import com.karollotkowski.webcrawler.task.ScrapPagesTask;
import com.karollotkowski.webcrawler.task.ScrapPagesTaskSupplier;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class WebPageCrawlerServiceTest {

  @InjectMocks private WebPageCrawlerService webPageCrawlerService;

  @Mock private ScrapPagesTaskSupplier scrapPagesTaskSupplier;

  private final Fixtures fixtures = new Fixtures();

  @Before
  public void setUp() {
    given(scrapPagesTaskSupplier.get(any(String.class))).willReturn(fixtures.scrapPagesTask);
  }

  @Test
  public void returnPageMap() {
    // given
    final String domain = fixtures.domain;

    // when
    final PageMap pageMap = webPageCrawlerService.pageMap(domain);

    // then
    assertThat(pageMap).isEqualTo(fixtures.expectedPageMap);
  }

  private class Fixtures {

    String domain = "http://domain.com";

    String domainLink1 = domain + "/sub-page1";
    String domainLink2 = domain + "/sub-page2";
    Set<String> domainLinks = Set.of(domainLink1, domainLink2);

    String externalLink = "http://external.com";
    Set<String> externalLinks = Set.of(externalLink);

    String image = domain + "/content/image.jpg";
    Set<String> images = Set.of(image);

    String pdf = domain + "/documents/info.pdf";
    Set<String> pdfs = Set.of(pdf);

    Page page =
        Page.builder()
            .url(domain)
            .elements(
                Elements.builder()
                    .links(Links.builder().internal(domainLinks).external(externalLinks).build())
                    .content(StaticContent.builder().images(images).pdfs(pdfs).build())
                    .build())
            .build();

    Set<Page> pages = Set.of(page);

    ScrapPagesTask scrapPagesTask = () -> pages;

    PageMap expectedPageMap = PageMap.builder().domain(domain).pages(pages).build();
  }
}
