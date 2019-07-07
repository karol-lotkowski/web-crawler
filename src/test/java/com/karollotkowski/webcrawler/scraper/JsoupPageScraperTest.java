package com.karollotkowski.webcrawler.scraper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import org.junit.Test;

public class JsoupPageScraperTest {

  private JsoupPageScraper jsoupPageScraper = new JsoupPageScraper();

  private final Fixtures fixtures = new Fixtures();

  @Test
  public void returnDomainLinksAndSkipExternalLinksAndFiles() {
    // when
    final Set<String> links = jsoupPageScraper.getLinks(fixtures.domain, fixtures.html);

    // then
    assertThat(links).hasSameElementsAs(fixtures.expectedLinks);
  }

  @Test
  public void wikipediaPageShouldContainsLinkToPolishVersion() {
    // given
    final String domain = "https://www.wikipedia.org/";

    // when
    final Set<String> links = jsoupPageScraper.getLinks(domain);

    // then
    assertThat(links).containsAnyOf("https://www.wikipedia.org//pl.wikipedia.org/");
  }

  private class Fixtures {

    String domain = "http://domain.com";

    String html =
        "<body>"
            + "<a href=\"/sub-page-1\">Link 1</a>"
            + "<a href=\"http://domain.com/sub-page-2\">Link 2</a>"
            + "<a href=\"http://externaldomain.com/sub-page-2\">Link 2</a>"
            + "<a href=\"http://domain.com/document.pdf\">PDF</a>"
            + "<a href=\"http://domain.com/document.docx\">DOCX</a>"
            + "<a href=\"http://domain.com/document.zip\">ZIP</a>"
            + "<a href=\"http://domain.com/image.png\">PNG</a>"
            + "<a href=\"http://domain.com/image.jpg\">JPG</a>"
            + "<a href=\"http://domain.com/file.js\">JS</a>"
            + "<a href=\"http://domain.com/file.json\">JSON</a>"
            + "<a href=\"http://domain.com/file.xml\">XML</a>"
            + "<a href=\"http://domain.com/file.sql\">SQL</a>"
            + "<a href=\"http://domain.com/file.yaml\">YAML</a>"
            + "<a href=\"http://domain.com/file.py\">PY</a>"
            + "</body>";

    String domainLink1 = domain + "/sub-page-1";
    String domainLink2 = domain + "/sub-page-2";

    Set<String> expectedLinks = Set.of(domainLink1, domainLink2);
  }
}
