package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.karollotkowski.webcrawler.domain.PageMap;
import java.util.Set;
import org.junit.Test;

public class WebPageCrawlerServiceTest {

  private WebPageCrawlerService webPageCrawlerService = new WebPageCrawlerService();

  private final Fixtures fixtures = new Fixtures();

  @Test
  public void returnMockedPageMap() {
    // given
    final String domain = fixtures.domain;

    // when
    final PageMap pageMap = webPageCrawlerService.pageMap(domain);

    // then
    assertThat(pageMap).isEqualTo(fixtures.expectedPageMap);
  }

  private class Fixtures {

    String domain = "http://domain.com";

    String subPage1 = domain + "/sub-page1";
    String subPage2 = domain + "/sub-page2";

    PageMap expectedPageMap =
        PageMap.builder().domain(domain).pages(Set.of(subPage1, subPage2)).build();
  }
}
