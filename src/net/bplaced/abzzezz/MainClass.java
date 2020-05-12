/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 20:45
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz;

import net.bplaced.abzzezz.file.impl.DomainsSearched;
import net.bplaced.abzzezz.file.impl.InfoFile;
import net.bplaced.abzzezz.file.impl.WebsitesSearched;
import net.bplaced.abzzezz.ui.MainUi;

import java.io.File;

public class MainClass {

    /**
     * Main Args URL, Keyword, Fileout, maxWebsites:
     *
     * @param args
     */
    public static void main(String[] args) {
        EngineCore engineCore = new EngineCore(500, 700, new MainUi());
        engineCore.setGameName("Web Crawler");
        if (args.length > 0) engineCore.setMainDir(new File(args[0]));
        engineCore.getFileManager().getFiles().add(new DomainsSearched());
        engineCore.getFileManager().getFiles().add(new WebsitesSearched());
        engineCore.getFileManager().getFiles().add(new InfoFile());
        engineCore.start();
    }
}
