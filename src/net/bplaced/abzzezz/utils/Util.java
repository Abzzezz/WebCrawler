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

import ga.abzzezz.util.logging.Logger;
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

        if(in.contains("facebook")) return true;

        String last = (copy.size() > 5) ? copy.get(copy.size() - 1) : "";
        if (last.contains("=") && in.contains("=")) {
            if (last.split("=")[0].equalsIgnoreCase(in.split("=")[0])) {
                Logger.log("Similar seeming URL found:" + in + "  To:" + last, Logger.LogType.INFO);
                return true;
            }
        } else if(last.contains("/") && in.contains("/")) {
            URL path = new URL(in);
            URL pathLast = new URL(last);
            if(path.getPath().equalsIgnoreCase(pathLast.getPath())) {
                Logger.log("Similar seeming URL found:" + path.getPath() + "  To:" + pathLast.getPath(), Logger.LogType.INFO);
                return true;
            }
        }
        return Main.getInstance().getCrawlerHandler().getUrlsChecked().contains(in);
    }

    public static List<String> getDomainList() {
        List<String> domains = new ArrayList<>();
        int https = "https://".length();
        Main.getInstance().getCrawlerHandler().getUrlsChecked().forEach(s -> {
            if (s.indexOf("/", https) >= 0) {
                String domain = s.substring(https, s.indexOf("/", https));
                if (domains.contains(domain)) domains.add(domain);
            } else {
                domains.add(s);
            }
        });

        return domains;
    }
}
