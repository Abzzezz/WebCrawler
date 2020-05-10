/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 19:47
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.util;

import java.awt.*;
import java.util.ArrayList;

public class Util {

    public static RunState runState = RunState.WAITING;

    public static ArrayList<Integer> getCrawledWords(String in, String keyword) {
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

    public static Color getDefaultColor() {
        return new Color(0xFF5A99B5);
    }

    public enum RunState {

        RUNNING, INTERRUPTED, STARTED, WAITING
    }
}
