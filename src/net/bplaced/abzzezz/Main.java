/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 13:19
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz;

import net.bplaced.abzzezz.crawler.CrawlerHandler;

import java.util.Map;

public class Main {

    private CrawlerHandler crawlerHandler = new CrawlerHandler("Test");
    private static Main ins = new Main();

    public void startThread() {
        crawlerHandler.newCrawler("https://stackoverflow.com/questions/40769548/how-to-retrieve-a-url-from-a-link-on-a-website-using-jsoup");
    }

    public CrawlerHandler getCrawlerHandler() {
        return crawlerHandler;
    }

    public static Main getInstance() {
        return ins;
    }
}
