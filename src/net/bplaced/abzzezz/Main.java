/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 20:06
 * Uses:
 *  Abzzezz Util (c) Roman P.
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

    public static Main getInstance() {
        return ins;
    }

    /**
     *
     */
    public void startThread() {
        this.crawlerHandler = new CrawlerHandler();
        crawlerHandler.newCrawler(url);
    }

    public void interruptAll() {
        crawlerHandler.getCrawlers().forEach(crawler -> {
            crawler.interrupt();
        });
    }

    public void proceedAll() {
        crawlerHandler.getCrawlers().forEach(crawler -> {
            crawler.start();
        });
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
}
