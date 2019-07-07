package com.karollotkowski.webcrawler.scraper;

import com.karollotkowski.webcrawler.exception.PageScrapingException;
import java.util.Set;

public interface PageScraper {

  Set<String> getLinks(String url) throws PageScrapingException;
}
