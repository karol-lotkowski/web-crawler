package com.karollotkowski.webcrawler.task;

import com.karollotkowski.webcrawler.domain.Page;
import java.util.Set;
import java.util.concurrent.Callable;

public interface ScrapPagesTask extends Callable<Set<Page>> {}
