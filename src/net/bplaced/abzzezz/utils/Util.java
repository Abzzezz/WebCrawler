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
            URL path = new URL(in);
            URL pathLast = new URL(s);
            if (!path.getPath().isEmpty() && !pathLast.getPath().isEmpty()) {
                if (path.getPath().equalsIgnoreCase(pathLast.getPath())) {
                    return true;
                }
            }

            if (copy.contains(in)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getDomainList() {
        List<String> domains = new ArrayList<>();
        Main.getInstance().getCrawlerHandler().getUrlsChecked().forEach(s -> {
            String domain = null;
            try {
                domain = new URL(s).getHost();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            if (!domains.contains(domain)) domains.add(domain);
        });
        return domains;
    }
}
