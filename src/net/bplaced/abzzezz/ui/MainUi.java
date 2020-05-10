/*
 * Copyright (c) 2020. Roman P.
 * All code is owned by Roman P. APIs are mentioned.
 * Last modified: 10.05.20, 20:45
 * Uses:
 *  Abzzezz Util (c) Roman P.
 */

package net.bplaced.abzzezz.ui;

import ga.abzzezz.util.logging.Logger;
import net.bplaced.abzzezz.Main;
import net.bplaced.abzzezz.screens.Button;
import net.bplaced.abzzezz.screens.Screen;
import net.bplaced.abzzezz.util.Util;
import net.bplaced.abzzezz.utils.FontUtil;

public class MainUi extends Screen {

    private FontUtil fontUtil, smaller;

    @Override
    public void buttonPressed(float buttonID) {
        if (buttonID == 0) {
            Main.getInstance().setUrl(Logger.logInput("Input URL"));
        } else if (buttonID == 1) {
            Main.getInstance().setKeyword(Logger.logInput("Input Keyword"));
        } else if (buttonID == 2) {
            switch (Util.runState) {
                case WAITING:
                    Util.runState = Util.RunState.RUNNING;
                    getButtonByID(2).setText("Stop");
                    new Thread(() -> {
                        Main.getInstance().startThread();
                    }).start();
                    break;
                case RUNNING:
                    Util.runState = Util.RunState.INTERRUPTED;
                    getButtonByID(2).setText("Proceed");
                    Main.getInstance().interruptAll();
                    break;
                case INTERRUPTED:
                    Util.runState = Util.RunState.RUNNING;
                    getButtonByID(2).setText("Stop");
                    Main.getInstance().proceedAll();
                    break;
                default:
                    break;
            }
        }
        super.buttonPressed(buttonID);
    }

    @Override
    public void init() {
        this.fontUtil = new FontUtil("BigNoodleTitling", 40);
        this.smaller = new FontUtil("BigNoodleTitling", 25);

        String[] buttonText = {"Set URL", "Set Keyword", "Start"};
        for (int i = 0; i < buttonText.length; i++) {
            this.getButtons().add(new Button(i, buttonText[i], 0, getHeight() - 30 - i * 30));
        }
        super.init();
    }

    @Override
    public void drawScreen() {
        fontUtil.drawString("Web Crawler", 0, 0, Util.getDefaultColor());

        if (Main.getInstance().getCrawlerHandler() != null) {
            String s = "Crawlers active: " + Main.getInstance().getCrawlerHandler().getCrawlers().size();
            smaller.drawString(s, getWidth() - smaller.getStringWidth(s), 0, Util.getDefaultColor());

            String s1 = "Websites searched: " + Main.getInstance().getCrawlerHandler().getUrlsChecked().size();
            smaller.drawString(s1, getWidth() - smaller.getStringWidth(s1), smaller.getHeight() * 2, Util.getDefaultColor());


        }
        super.drawScreen();
    }

    @Override
    public void keyTyped(int keyCode, char keyTyped) {
        super.keyTyped(keyCode, keyTyped);
    }
}
