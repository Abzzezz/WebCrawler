/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 20:38
 * Uses:
 *  Abzzezz Util (c) Roman P.
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
            if (amount != 0)
                Main.getInstance().getCrawlerHandler().writeToFile("URL:" + url + " Entries found: " + amount);

            /**
             * Search and run URLS (https)
             */
            Elements https = doc.select("a[href*=https]");

            if(https.isEmpty()) interrupt();

            for (Element element : https) {
                String newUrl = element.attr("abs:href");
                if (!Util.containsURLInCase(newUrl)) {
                    Main.getInstance().getCrawlerHandler().getUrlsChecked().add(newUrl);
                    Main.getInstance().getCrawlerHandler().newCrawler(newUrl);
                }
            }

            Logger.log("Finished website: " + url, Logger.LogType.INFO);
            interrupt();
        } catch (IOException e) {
            Logger.log("Url not found", Logger.LogType.ERROR);
            interrupt();
            e.printStackTrace();
        }
        super.run();
    }


    @Override
    public void interrupt() {
        Main.getInstance().getCrawlerHandler().getCrawlers().remove(this);
        Logger.log("Thread: " + getName() + " interrupted - no further websites found", Logger.LogType.INFO);
        super.interrupt();
    }

    private int getCrawledWords(String in) {
        return Util.getCrawledWords(in, keyword).size();
    }

}
