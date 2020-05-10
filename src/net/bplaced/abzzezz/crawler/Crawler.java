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
import net.bplaced.abzzezz.util.Util;
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
             * Search and run URLS
             */
            Elements urls = doc.select("a[href*=https]");
            for (Element element : urls) {
                String newUrl = element.attr("abs:href");
                if (!containsURLInCase(newUrl)) {
                    Main.getInstance().getCrawlerHandler().getUrlsChecked().add(newUrl);
                    Main.getInstance().getCrawlerHandler().newCrawler(newUrl);
                }
            }
            interrupt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }

    private boolean containsURLInCase(String in) {
        String cont = in;
        if(cont.contains("=")) cont.substring(0, cont.indexOf("="));
        return Main.getInstance().getCrawlerHandler().getUrlsChecked().contains(cont);
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
