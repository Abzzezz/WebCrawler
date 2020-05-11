/*
 * Copyright (c) 2020. Roman P.
 * All code belongs to its owners!
 * Last modified: 29.04.20, 14:52
 * APIS used:
 * LWJGL (https://www.lwjgl.org/)
 * Slick (http://slick.ninjacave.com/slick-util/)
 * Abzzezz Util (https://github.com/Abzzezz/AbzzezzUtil)
 */

package net.bplaced.abzzezz.screens;

import net.bplaced.abzzezz.utils.FontUtil;
import net.bplaced.abzzezz.utils.MouseUtil;
import net.bplaced.abzzezz.utils.RenderUtil;
import net.bplaced.abzzezz.utils.Util;

import java.awt.*;

public class Button {

    private String text;
    private final float xPos;
    private final float yPos;
    private final float id;

    private final FontUtil smallFont = new FontUtil("SIMPLIFICA", 20);

    /**
     * Simple button. Buttons can be added just use this as a parent
     *
     * @param id
     * @param text
     * @param xPos
     * @param yPos
     */
    public Button(float id, String text, float xPos, float yPos) {
        this.id = id;
        this.text = text;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void drawButton() {
        RenderUtil.drawQuad(xPos - 20, yPos, 100, getDimensions()[1], Util.getDefaultColor());
        smallFont.drawString(text, xPos, yPos, isButtonHovered() ? new Color(-1) : Color.GRAY);
    }

    public boolean isButtonHovered() {
        return MouseUtil.mouseHovered(xPos, yPos, getDimensions()[0], getDimensions()[1]);
    }

    public float getId() {
        return id;
    }

    public float[] getDimensions() {
        return new float[]{smallFont.getStringWidth(text), smallFont.getHeight()};
    }

    public void setText(String text) {
        this.text = text;
    }
}
