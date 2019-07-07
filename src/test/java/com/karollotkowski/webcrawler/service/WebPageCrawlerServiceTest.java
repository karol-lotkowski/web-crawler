package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.karollotkowski.webcrawler.domain.PageMap;
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

  @Mock private PageDetailsService pageDetailsService;

  private final Fixtures fixtures = new Fixtures();

  @Before
  public void setUp() {
    given(pageDetailsService.getLinks(any(String.class))).willReturn(fixtures.domainLinks);
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

    String subPage1 = domain + "/sub-page1";
    String subPage2 = domain + "/sub-page2";

    Set<String> domainLinks = Set.of(subPage1, subPage2);

    PageMap expectedPageMap = PageMap.builder().domain(domain).pages(domainLinks).build();
  }
}
