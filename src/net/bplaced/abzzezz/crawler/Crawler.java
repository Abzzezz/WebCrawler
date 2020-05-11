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
import java.util.List;

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
            for (Element element : https) {
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
        List<String> copy = Main.getInstance().getCrawlerHandler().getUrlsChecked();
        String last = (copy.size() > 5) ? copy.get(copy.size() - 1) : "";
        if (last.contains("=") && in.contains("=")) {
            if (last.split("=")[0].equalsIgnoreCase(in.split("=")[0])) {
                Logger.log("Similar seeming URL found:" + in + "  To:" + last, Logger.LogType.INFO);
                return true;
            }
        }
        return Main.getInstance().getCrawlerHandler().getUrlsChecked().contains(in);
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
