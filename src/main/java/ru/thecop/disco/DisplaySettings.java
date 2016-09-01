package ru.thecop.disco;

import lombok.Data;

@Data
public class DisplaySettings {

    private int width;

    private char horizontal;
    private char vertical;
    private char cross;
    private char bottomCross;
    private char topCross;
    private char topLeft;
    private char topRight;
    private char bottomLeft;
    private char bottomRight;
    private char blockSeparator;
    private char elementSeparator;

    private int blockElementsSpacing;

    private int paddingTop;
    private int paddingBottom;
    private int paddingLeft;
    private int paddingRight;

    private int frameSeparationLinesCount;
    private int blockSeparationLinesCount;

    public static DisplaySettings defaultSettings() {
        return new DisplaySettings()
                .setWidth(180)
                .setHorizontal('|')
                .setVertical('-')
                .setCross('-')
                .setBottomCross('-')
                .setTopCross('-')
                .setTopLeft('-')
                .setTopRight('-')
                .setBottomLeft('-')
                .setBottomRight('-')
                .setBlockSeparator('-')
                .setElementSeparator('-')
                .setBlockElementsSpacing(0)
                .setPaddingBottom(1)
                .setPaddingTop(1)
                .setPaddingLeft(1)
                .setPaddingRight(1)
                .setFrameSeparationLinesCount(7)
                .setBlockSeparationLinesCount(0);
    }
    //TODO implement dos-like frames settings
}
