package ru.thecop.disco;

import lombok.Data;

@Data
public class DisplaySettings {

    private char horizontal;
    private char vertical;
    private char cross;
    private char bottomCross;
    private char topCross;
    private char topLeft;
    private char topRight;
    private char bottomLeft;
    private char bottomRight;

    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    private int width;

    private int separationLinesCount;

    public static DisplaySettings defaultSettings() {
        return new DisplaySettings()
                .setHorizontal('|')
                .setVertical('-')
                .setCross('-')
                .setBottomCross('-')
                .setTopCross('-')
                .setTopLeft('-')
                .setTopRight('-')
                .setBottomLeft('-')
                .setBottomRight('-')
                .setPaddingBottom(1)
                .setPaddingTop(1)
                .setPaddingLeft(1)
                .setPaddingRight(1)
                .setWidth(180)
                .setSeparationLinesCount(7);
    }
    //TODO implement dos-like frames settings
}
