/*
 * Copyright (c) 2020. Roman P.
 * All code belongs to its owners!
 * Last modified: 29.04.20, 15:53
 * APIS used:
 * LWJGL (https://www.lwjgl.org/)
 * Slick (http://slick.ninjacave.com/slick-util/)
 * Abzzezz Util (https://github.com/Abzzezz/AbzzezzUtil)
 */

package net.bplaced.abzzezz.utils;

import java.awt.*;
import java.util.ArrayList;

public class Util {

    /**
     * Default set to white
     */
    public static Color backgroundColor = Color.WHITE;

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

    public static long startTime;
}
