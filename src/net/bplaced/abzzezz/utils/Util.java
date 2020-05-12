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

import net.bplaced.abzzezz.Main;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Util {

    /**
     * Default set to white
     */
    public static Color backgroundColor = Color.WHITE;
    public static long startTime;

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

    public static boolean containsURLInCase(String in) throws MalformedURLException {
        List<String> copy = Main.getInstance().getCrawlerHandler().getUrlsChecked();
        for (String s : copy) {
            if (s.contains("/") && in.contains("/")) {
                URL path = new URL(in);
                URL pathLast = new URL(s);
                if (path.getPath().equalsIgnoreCase(pathLast.getPath())) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getDomainList() {
        List<String> domains = new ArrayList<>();
        Main.getInstance().getCrawlerHandler().getUrlsChecked().forEach(s -> {
            if (s.split("/").length > 1) {
                String domain = s.split("/")[2];
                if (!domains.contains(domain)) domains.add(domain);
            }
        });
        return domains;
    }
}
