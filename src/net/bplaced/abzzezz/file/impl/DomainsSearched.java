/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 11.05.20, 16:53
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.file.impl;

import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.misc.TimeUtil;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.file.CustomFile;
import net.bplaced.abzzezz.utils.Util;

import java.util.concurrent.TimeUnit;

public class DomainsSearched extends CustomFile {

    public DomainsSearched() {
        super("DomainsSearched.txt");
    }


    @Override
    public void write() {
        FileUtil.writeArrayListToFile(Main.getInstance().getCrawlerHandler().getUrlsChecked(), thisFile);

        FileUtil.appendToFile("Time Elapsed: " + TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis() - Util.startTime), thisFile, true);
        FileUtil.appendToFile("Last Crawlers active: " + Main.getInstance().getCrawlerHandler().getCrawlers().size(), thisFile, true);

        super.write();
    }
}
