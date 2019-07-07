package com.karollotkowski.webcrawler.task;

import static org.junit.Assert.assertSame;

import com.karollotkowski.webcrawler.domain.PageDetails;
import org.junit.Test;

public class ScrapPagesTaskSupplierTest {

  private ScrapPagesTaskSupplier scrapPagesTaskSupplier =
      new ScrapPagesTaskSupplier(url -> PageDetails.builder().build());

  @Test
  public void returnParallelScrapPagesTask() {
    // given
    final String url = "http://domain.com";

    // when
    final ScrapPagesTask scrapPagesTask = scrapPagesTaskSupplier.get(url);

    // then
    assertSame(scrapPagesTask.getClass(), ParallelScrapPagesTask.class);
  }
}
