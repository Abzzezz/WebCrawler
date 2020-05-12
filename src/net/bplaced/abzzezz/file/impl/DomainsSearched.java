/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 11.05.20, 16:53
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.file.impl;

import ga.abzzezz.util.data.FileUtil;
import net.bplaced.abzzezz.file.CustomFile;
import net.bplaced.abzzezz.utils.Util;

public class DomainsSearched extends CustomFile {

    public DomainsSearched() {
        super("Domains-searched.txt");
    }


    @Override
    public void write() {
        FileUtil.writeArrayListToFile(Util.getDomainList(), thisFile);
        super.write();
    }
}
