/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 13:09
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.crawler;

import net.bplaced.abzzezz.Main;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Crawler extends Thread {

    private String url;
    private String keyword;

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
            System.out.println(url);

            if (Main.getInstance().getCrawlerHandler().getUrlsChecked().size() > 300) {
                for (Map.Entry<String, Integer> stringIntegerEntry : Main.getInstance().getCrawlerHandler().getUrlAndCount().entrySet()) {
                    System.out.println(stringIntegerEntry);
                }
                Runtime.getRuntime().exit(0);
            }

            Document doc = Jsoup.connect(url).get();
            Document textContents = Jsoup.parse(doc.outerHtml());
            String content = textContents.wholeText();

            if (getCrawledWords(content).size() != 0) {
                Main.getInstance().getCrawlerHandler().getUrlAndCount().put(url, getCrawledWords(content).size());
            }

            Elements urls = doc.select("a[href*=https]");
            for (Element element : urls) {
                String newUrl = element.attr("abs:href");
                if (!Main.getInstance().getCrawlerHandler().getUrlsChecked().contains(newUrl)) {
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

    @Override
    public void interrupt() {
        System.out.println("Thread: " + getName() + " interrupted");
        super.interrupt();
    }

    public ArrayList<Integer> getCrawledWords(String in) {
        ArrayList<Integer> crawled = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(in);
        for (int i = 0; i < in.length(); i++) {
            int lastIndex = stringBuilder.indexOf(keyword);
            if (lastIndex == -1) break;
            int end = lastIndex + keyword.length();
            crawled.add(lastIndex);
            stringBuilder.delete(lastIndex, end);
        }
        return crawled;
    }
}
