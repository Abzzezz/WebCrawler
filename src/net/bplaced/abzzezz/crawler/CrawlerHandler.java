/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 13:13
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.crawler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CrawlerHandler {

    private List<Crawler> crawlers = new LinkedList<>();
    private List<String> urlsChecked = new LinkedList<>();
    private HashMap<String, Integer> urlAndCount  = new HashMap<>();

    private String keyword;

    public CrawlerHandler(String keyword) {
        this.keyword = keyword;
    }

    public void newCrawler(String url) {
        Crawler crawler = new Crawler(url, keyword);
        crawlers.add(crawler);
        crawler.run();
    }

    public List<Crawler> getCrawlers() {
        return crawlers;
    }

    public List<String> getUrlsChecked() {
        return urlsChecked;
    }
}
