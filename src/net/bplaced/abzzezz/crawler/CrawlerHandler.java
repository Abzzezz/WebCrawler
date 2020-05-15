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
import net.bplaced.abzzezz.EngineCore;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.utils.Util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CrawlerHandler {

    /**
     * Header
     */
    private final List<String> urlsChecked = new CopyOnWriteArrayList<>();
    private final ExecutorService executor;
    private final File fileIn;

    public CrawlerHandler() {
        this.fileIn = new File(EngineCore.getInstance().getMainDir(), "outfile.txt");
        if (!fileIn.exists()) {
            try {
                fileIn.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        writeToFile("[Searching for keyword:" + Main.getInstance().getKeyword() + "]");
        this.executor = Executors.newFixedThreadPool(Util.maxProcessing);;
    }

    public void newCrawler(String url) {
        Crawler crawler = new Crawler(url, Main.getInstance().getKeyword());
        executor.submit(crawler);
    }

    public void writeToFile(String in) {
        FileUtil.appendToFile(in, fileIn, true);
    }

    public List<String> getUrlsChecked() {
        return urlsChecked;
    }

    public File getFileIn() {
        return fileIn;
    }
}
