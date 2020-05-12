/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 12.05.20, 14:17
 * Uses:
 * Abzzezz Util (c) Roman P.
 * LWJGL Engine (c) Roman P.
 * JSoup https://jsoup.org/
 *
 */

package net.bplaced.abzzezz.crawler;

import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.logging.Logger;
import net.bplaced.abzzezz.EngineCore;
import net.bplaced.abzzezz.Main;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerHandler {

    private final List<Crawler> crawlers = new LinkedList<>();
    private final List<String> urlsChecked = new LinkedList<>();

    private final String keyword;
    private final File fileIn;

    public CrawlerHandler() {
        this.keyword = Main.getInstance().getKeyword();
        this.fileIn = new File(EngineCore.getInstance().getMainDir(), "outfile.txt");
        if (!fileIn.exists()) {
            try {
                fileIn.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeToFile("[Searching for keyword:" + keyword + "]");
    }

    public void newCrawler(String url) {
        Crawler crawler = new Crawler(url, keyword);
        //Logger.log("New Crawler Thread running: " + crawler.getName(), Logger.LogType.INFO);
        crawlers.add(crawler);
        crawler.run();
    }

    public void writeToFile(String in) {
        FileUtil.appendToFile(in, fileIn, true);
    }

    public List<Crawler> getCrawlers() {
        return crawlers;
    }

    public List<String> getUrlsChecked() {
        return urlsChecked;
    }

}
