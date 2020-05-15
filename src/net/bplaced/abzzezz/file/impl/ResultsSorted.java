/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 15.05.20, 14:14
 * Uses:
 * Abzzezz Util (c) Roman P.
 * LWJGL Engine (c) Roman P.
 * JSoup https://jsoup.org/
 *
 */

package net.bplaced.abzzezz.file.impl;

import ga.abzzezz.util.array.ArrayUtil;
import ga.abzzezz.util.data.FileUtil;
import ga.abzzezz.util.stringing.StringUtil;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.file.CustomFile;

import java.io.File;
import java.util.ArrayList;

public class ResultsSorted extends CustomFile {

    /**
     * Simple custom file. Use as parent to make own files and load them automatically with the engine
     *
     *
     */
    public ResultsSorted() {
        super("Result-Sorted.txt");
    }


    @Override
    public void write() {
        ArrayList<String> copy = new ArrayList<>();
        for (String s : FileUtil.getFileContentAsList(Main.getInstance().getCrawlerHandler().getFileIn())) {
            if(s.split(StringUtil.splitter).length > 1) copy.add(s);
        }

        FileUtil.writeArrayListToFile(ArrayUtil.sortWithNumberInName(copy, StringUtil.splitter, 1), thisFile);
        super.write();
    }

}
