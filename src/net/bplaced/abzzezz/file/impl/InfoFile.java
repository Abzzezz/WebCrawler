/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 12.05.20, 12:39
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.file.impl;

import ga.abzzezz.util.data.FileUtil;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.file.CustomFile;
import net.bplaced.abzzezz.utils.Util;

import java.util.concurrent.TimeUnit;

public class InfoFile extends CustomFile {
    /**
     * Simple custom file. Use as parent to make own files and load them automatically with the engine
     */
    public InfoFile() {
        super("Info.txt");
    }

    @Override
    public void write() {

        String[] strings = {"Time Elapsed: " + TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - Util.startTime) + " Minutes",
                "Last Crawlers active: " + Main.getInstance().getCrawlerHandler().getCrawlers().size(),
                "Total Websites searched:" + Main.getInstance().getCrawlerHandler().getUrlsChecked().size(),
                "Total Entries of keyword: " + Main.getInstance().getKeyword() + " :" + Main.getInstance().getEntriesFound()
        };

        for (String string : strings) {
            FileUtil.appendToFile(string, thisFile, true);
        }
        super.write();
    }
}
