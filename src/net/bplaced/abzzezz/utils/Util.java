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

import ga.abzzezz.util.stringing.StringUtil;
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
    /**
     * Start time to calculate running time
     */
    public static long startTime;

    /**
     * Get max cores for threadpool. to operate resource friendly but fast at the same time
     */
    public static int maxProcessing = Runtime.getRuntime().availableProcessors() > 12 ?
            Runtime.getRuntime().availableProcessors() : Runtime.getRuntime().availableProcessors() / 2;

    /**
     * Rewritten to just count and dont actually add to list
     * Hardcoded because JSoup gives the HTML part as one string
     * @param in
     * @param keyword
     * @return
     */
    public static int getCrawledWords(String in, String keyword) {
        int count = 0;
        StringBuilder stringBuilder = new StringBuilder(in);
        for (int i = 0; i < in.length(); i++) {
            int lastIndex = stringBuilder.indexOf(keyword);
            if (lastIndex == -1) break;
            int end = lastIndex + keyword.length();
            count++;
            stringBuilder.delete(lastIndex, end);
        }
        return count;
    }

    /**
     * @return
     */
    public static Color getDefaultColor() {
        return new Color(0xFF5A99B5);
    }

    /**
     *
     *
     * @param in
     * @return
     * @throws MalformedURLException
     */
    public synchronized static boolean containsURLInCase(String in) throws MalformedURLException {
        List<String> copy = Main.getInstance().getCrawlerHandler().getUrlsChecked();
        for (String s : copy) {
            URL newURL = new URL(in);
            URL checkURL = new URL(s);
            if (!newURL.getPath().isEmpty() && !checkURL.getPath().isEmpty()) {
                if (newURL.getPath().equalsIgnoreCase(checkURL.getPath())) return true;
            }

            if (copy.contains(in)) return true;


        }
        return false;
    }

    /**
     * Throws malurlex when Host does not respond
     * @return
     */
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
