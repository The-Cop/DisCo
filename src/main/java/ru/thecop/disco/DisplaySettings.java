package ru.thecop.disco;

import lombok.Data;

// TODO: 08.09.2016 get rid of lombok
// TODO: 08.09.2016 use builder with validation on build()
@Data
public class DisplaySettings {

    // TODO: 07.09.2016 global: make only block interface, and resolve internal blocks recursively.

    //Display width
    private int width;
    //---Common display properties
    //Horizontal line  character
    private char horizontal;
    //Vertical line horizontal character
    private char vertical;
    //Crossing of vertical and horisontal lines (non-corner)
    private char cross;
    //Crossing of vertical and lowest horisontal line (non-corner)
    private char bottomCross;
    //Crossing of vertical and top horisontal line (non-corner)
    private char topCross;
    //Top-left corner
    private char topLeft;
    //Top-right corner
    private char topRight;
    //Bottom-left corner
    private char bottomLeft;
    //Bottom-right corner
    private char bottomRight;

    //---Block
    //Block padding
    private int blockPaddingTop;
    private int blockPaddingBottom;
    private int blockPaddingLeft;
    private int blockPaddingRight;
    //Number of separation lines between elements in block
    private int blockElementsSeparationLinesCount;
    //A char which will be used in separation lines between elements in block
    private char blockElementsSeparationChar;

    //---Container
    //Number of separation lines between blocks in container
    private int containerBlockSeparationLinesCount;
    //A char which will be used in separation lines between blocks in container
    private char containerBlockSeparationChar;

    //---Frame
    //Number of separation lines between containers in frame
    private int frameContainerSeparationLinesCount;
    //A char which will be used in separation lines between containers in frame
    private char frameContainerSeparationChar;

    //---Display
    //Number of lines between frames
    private int displayFrameSeparationLinesCount;

    //---Valued element
    //Minimum distance between text and value
    private int valuedElementMinValueSpacing;
    //Char used as spacing between text and value
    private char valuedElementSpacingChar;

    public static DisplaySettings defaultSettings() {
        return new DisplaySettings()
                .setWidth(180)
                .setHorizontal('-')
                .setVertical('|')
                .setCross('+')
                .setBottomCross('-')
                .setTopCross('-')
                .setTopLeft('-')
                .setTopRight('-')
                .setBottomLeft('-')
                .setBottomRight('-')

                .setBlockPaddingTop(0)
                .setBlockPaddingBottom(0)
                .setBlockPaddingLeft(0)
                .setBlockPaddingRight(0)
                .setBlockElementsSeparationLinesCount(0)
                .setBlockElementsSeparationChar(' ')

                .setContainerBlockSeparationLinesCount(0)
                .setContainerBlockSeparationChar(' ')

                .setFrameContainerSeparationLinesCount(0)
                .setFrameContainerSeparationChar(' ')

                .setDisplayFrameSeparationLinesCount(7)

                .setValuedElementMinValueSpacing(1)
                .setValuedElementSpacingChar('.');
    }
    //TODO implement dos-like frames settings
}
