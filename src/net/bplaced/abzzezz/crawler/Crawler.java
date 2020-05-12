/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 12.05.20, 14:18
 * Uses:
 * Abzzezz Util (c) Roman P.
 * LWJGL Engine (c) Roman P.
 * JSoup https://jsoup.org/
 *
 */

package net.bplaced.abzzezz.crawler;

import ga.abzzezz.util.logging.Logger;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.utils.Util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Crawler extends Thread {

    private final String url;
    private final String keyword;

    public Crawler(String url, String keyword) {
        this.url = url;
        this.keyword = keyword;
    }

    /**
     * Get website text using Jsoup then filter for keywords
     */
    @Override
    public void run() {
        try {
            Logger.log("Got URL: " + url, Logger.LogType.INFO);
            Document doc = Jsoup.connect(url).get();
            Document textContents = Jsoup.parse(doc.outerHtml());
            int amount = getCrawledWords(textContents.wholeText());

            /**
             * Write to file if the amount of keywords found on the site != 0
             */
            if (amount != 0) {
                Main.getInstance().getCrawlerHandler().writeToFile("URL:" + url + " Entries found: " + amount);
                Main.getInstance().setEntriesFound(Main.getInstance().getEntriesFound() + amount);
            }

            /**
             * Search and process URLS
             */
            processLink(doc);

            Logger.log("Finished website: " + url, Logger.LogType.INFO);
            interrupt();
        } catch (IOException e) {
            Logger.log("Url not found", Logger.LogType.ERROR);
            interrupt();
            e.printStackTrace();
        }
        super.run();
    }


    public void processLink(Document document) throws IOException {
        Elements https = document.select("a[href]");
        if (https.isEmpty()) interrupt();
        for (Element element : https) {
            String newUrl = element.attr("abs:href");
            if (!Util.containsURLInCase(newUrl)) {
                Main.getInstance().getCrawlerHandler().getUrlsChecked().add(newUrl);
                Main.getInstance().getCrawlerHandler().newCrawler(newUrl);
            }
        }
    }


    @Override
    public void interrupt() {
        Main.getInstance().getCrawlerHandler().getCrawlers().remove(this);
        Logger.log("Thread: " + getName() + " interrupted - no further websites found", Logger.LogType.INFO);
        this.stop();
        super.interrupt();
    }

    private int getCrawledWords(String in) {
        return Util.getCrawledWords(in, keyword).size();
    }

}
