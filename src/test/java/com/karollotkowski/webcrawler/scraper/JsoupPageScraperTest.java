package com.karollotkowski.webcrawler.scraper;

import static org.assertj.core.api.Assertions.assertThat;

import com.karollotkowski.webcrawler.domain.PageDetails;
import com.karollotkowski.webcrawler.exception.PageScrapingException;
import java.util.Set;
import org.junit.Test;

public class JsoupPageScraperTest {

  private JsoupPageScraper jsoupPageScraper = new JsoupPageScraper();

  private final Fixtures fixtures = new Fixtures();

  @Test
  public void returnPageDetails() {
    // when
    final PageDetails pageDetails = jsoupPageScraper.getPageDetails(fixtures.domain, fixtures.html);

    // then
    assertThat(pageDetails).isEqualTo(fixtures.expectedPageDetails);
  }

  @Test(expected = PageScrapingException.class)
  public void throwErrorForEmptyUrl() {
    // given
    final String emptyUrl = "";

    // when
    jsoupPageScraper.getPageDetails(emptyUrl);
  }

  @Test
  public void wikipediaPageShouldContainsLinkToPolishVersion() {
    // given
    final String domain = "https://www.wikipedia.org/";

    // when
    final PageDetails pageDetails = jsoupPageScraper.getPageDetails(domain);

    // then
    assertThat(pageDetails.domainLinks)
        .containsAnyOf("https://www.wikipedia.org//pl.wikipedia.org/");
    assertThat(pageDetails.externalLinks)
        .containsAnyOf("https://creativecommons.org/licenses/by-sa/3.0/");
  }

  private class Fixtures {

    String domain = "http://domain.com";

    String html =
        "<body>"
            + "<a href=\"/sub-page-1\">Link 1</a>"
            + "<a href=\"http://domain.com/sub-page-2\">Link 2</a>"
            + "<a href=\"http://externaldomain.com/sub-page-X\">External link X</a>"
            + "<a href=\"http://externaldomain2.com/sub-page-Y\">External link Y</a>"
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
            + "<img src=\"http://domain.com/image.png\"></img>"
            + "<img src=\"http://domain.com/image.jpg\"></img>"
            + "</body>";

    String domainLink1 = domain + "/sub-page-1";
    String domainLink2 = domain + "/sub-page-2";

    Set<String> expectedDomainLinks = Set.of(domainLink1, domainLink2);

    String externalLink1 = "http://externaldomain.com/sub-page-X";
    String externalLink2 = "http://externaldomain2.com/sub-page-Y";

    Set<String> expectedExternalLinks = Set.of(externalLink1, externalLink2);

    String image1 = "http://domain.com/image.png";
    String image2 = "http://domain.com/image.jpg";

    Set<String> expectedImages = Set.of(image1, image2);

    String pdf = "http://domain.com/document.pdf";
    Set<String> expectedPdfs = Set.of(pdf);

    PageDetails expectedPageDetails =
        PageDetails.builder()
            .domainLinks(expectedDomainLinks)
            .externalLinks(expectedExternalLinks)
            .images(expectedImages)
            .pdfs(expectedPdfs)
            .build();
  }
}
