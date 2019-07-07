package com.karollotkowski.webcrawler.scraper;

import com.karollotkowski.webcrawler.domain.PageDetails;
import com.karollotkowski.webcrawler.exception.PageScrapingException;

public interface PageScraper {

  PageDetails getPageDetails(String url) throws PageScrapingException;
}
