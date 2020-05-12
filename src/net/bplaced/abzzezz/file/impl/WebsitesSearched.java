/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 12.05.20, 12:43
 * Uses:
 * Abzzezz Util (c) Roman P.
 * LWJGL Engine (c) Roman P.
 * JSoup https://jsoup.org/
 *
 */

package net.bplaced.abzzezz.file.impl;

import ga.abzzezz.util.data.FileUtil;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.file.CustomFile;

public class WebsitesSearched extends CustomFile {
    /**
     * Simple custom file. Use as parent to make own files and load them automatically with the engine
     *
     *
     */

    public WebsitesSearched() {
        super("Websites-searched.txt");
    }

    @Override
    public void write() {
        FileUtil.writeArrayListToFile(Main.getInstance().getCrawlerHandler().getUrlsChecked(), thisFile);
        super.write();
    }
}
