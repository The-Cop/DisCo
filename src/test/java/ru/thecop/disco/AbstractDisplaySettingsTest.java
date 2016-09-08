package ru.thecop.disco;

public abstract class AbstractDisplaySettingsTest {

    protected DisplaySettings getDisplaySettings() {
        return new DisplaySettings()
                .setWidth(20)
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
                .setBlockElementsSeparationChar('#')

                .setContainerBlockSeparationLinesCount(0)
                .setContainerBlockSeparationChar(' ')

                .setFrameContainerSeparationLinesCount(0)
                .setFrameContainerSeparationChar(' ')

                .setDisplayFrameSeparationLinesCount(7)

                .setValuedElementMinValueSpacing(1)
                .setValuedElementSpacingChar('.');
    }
}
