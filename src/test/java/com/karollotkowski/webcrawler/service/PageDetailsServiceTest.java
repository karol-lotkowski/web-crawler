package com.karollotkowski.webcrawler.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.Test;

public class PageDetailsServiceTest {

  private final Fixtures fixtures = new Fixtures();

  private PageDetailsService pageDetailsService =
      new PageDetailsService(url -> Set.of(fixtures.subPage1, fixtures.subPage2));

  @Test
  public void returnDomainLinks() {
    // given

    // when
    final Set<String> links = pageDetailsService.getLinks(fixtures.domain);

    // then
    assertThat(links).hasSameElementsAs(Set.of(fixtures.subPage1, fixtures.subPage2));
  }

  private class Fixtures {

    String domain = "http://domain.com";

    String subPage1 = domain + "/sub-page-1";
    String subPage2 = domain + "/sub-page-2";
  }
}
