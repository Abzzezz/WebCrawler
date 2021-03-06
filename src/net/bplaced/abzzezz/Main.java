/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 12.05.20, 12:43
 * Uses:
 * Abzzezz Util (c) Roman P.
 * LWJGL Engine (c) Roman P.
 * JSoup https://jsoup.org/
 *
 */

package net.bplaced.abzzezz;

import net.bplaced.abzzezz.crawler.CrawlerHandler;

public class Main {

    private static final Main ins = new Main();
    /**
     *
     */
    private CrawlerHandler crawlerHandler;
    private String keyword, url;
    private int entriesFound;

    public static Main getInstance() {
        return ins;
    }

    /**
     *Start initial crawler
     */
    public void startThread() {
        this.crawlerHandler = new CrawlerHandler();
        crawlerHandler.newCrawler(url);
    }

    public CrawlerHandler getCrawlerHandler() {
        return crawlerHandler;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getEntriesFound() {
        return entriesFound;
    }

    public void setEntriesFound(int entriesFound) {
        this.entriesFound = entriesFound;
    }


}
