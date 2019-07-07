package com.karollotkowski.webcrawler.scraper;

import static java.util.stream.Collectors.toSet;

import com.karollotkowski.webcrawler.exception.PageScrapingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsoupPageScraper implements PageScraper {

  private static final String HTML_SELECTOR_LINK = "a";

  private static final String ATTRIBUTE_HREF = "href";

  private static final String SLASH = "/";
  private static final String HASH = "#";
  private static final String DOT = ".";

  private static final String EXTENSION_PDF = ".pdf";
  private static final String EXTENSION_DOCX = ".docx";
  private static final String EXTENSION_ZIP = ".zip";

  private static final String EXTENSION_PNG = ".png";
  private static final String EXTENSION_JPG = ".jpg";

  private static final String EXTENSION_JS = ".js";
  private static final String EXTENSION_JSON = ".json";
  private static final String EXTENSION_XML = ".xml";
  private static final String EXTENSION_SQL = ".sql";
  private static final String EXTENSION_YAML = ".yaml";
  private static final String EXTENSION_PY = ".py";

  private static final Set<String> FILES_EXTENTIONS =
      Set.of(EXTENSION_PDF, EXTENSION_DOCX, EXTENSION_ZIP);

  private static final Set<String> IMAGES_EXTENTIONS = Set.of(EXTENSION_PNG, EXTENSION_JPG);

  private static final Set<String> SCRIPTS_EXTENTIONS =
      Set.of(
          EXTENSION_PY, EXTENSION_JS, EXTENSION_JSON, EXTENSION_XML, EXTENSION_SQL, EXTENSION_YAML);

  @Override
  public Set<String> getLinks(@NonNull final String url) throws PageScrapingException {
    log.info("Scrap page: {}", url);

    try {
      final Document doc = Jsoup.connect(url).get();
      final String domain = getDomain(url);

      return getDomainLinks(domain, doc);

    } catch (final Exception e) {
      log.error("Error occurred during page scraping: {}", url);
      throw new PageScrapingException(url);
    }
  }

  Set<String> getLinks(@NonNull final String url, @NonNull final String html)
      throws PageScrapingException {
    log.info("Scrap HTML page: {}", html);

    try {
      final Document doc = Jsoup.parse(html);
      final String domain = getDomain(url);

      return getDomainLinks(domain, doc);

    } catch (final Exception e) {
      log.error("Error occurred during page scraping: {}", url);
      throw new PageScrapingException(url);
    }
  }

  private String getDomain(final String url) throws MalformedURLException {
    final URL pageUrl = new URL(url);
    return pageUrl.getProtocol() + "://" + pageUrl.getHost();
  }

  private Set<String> getDomainLinks(@NonNull final String domain, @NonNull final Document doc) {

    return doc.select(HTML_SELECTOR_LINK).stream()
        .map(element -> element.attributes().get(ATTRIBUTE_HREF))
        .filter(link -> link.startsWith(domain) || link.startsWith(SLASH))
        .filter(link -> !FILES_EXTENTIONS.contains(getExtension(link)))
        .filter(link -> !IMAGES_EXTENTIONS.contains(getExtension(link)))
        .filter(link -> !SCRIPTS_EXTENTIONS.contains(getExtension(link)))
        .filter(link -> !link.contains(HASH))
        .filter(link -> !link.equals(SLASH))
        .map(it -> it.startsWith(SLASH) ? domain + it : it)
        .collect(toSet());
  }

  private String getExtension(final String link) {
    if (link.contains(DOT)) {
      return link.substring(link.lastIndexOf(DOT));
    }
    return link;
  }
}
