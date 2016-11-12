package ru.thecop.disco;

import ru.thecop.disco.common.Borders;
import ru.thecop.disco.common.Padding;
import ru.thecop.disco.common.Separations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public final class BlockFormatter {

    //TODo javadoc!

    private BlockFormatter() {
    }

    public static List<String> lineFilledList(int linesCount, String fillingLine) {
        if (linesCount <= 0) {
            return new ArrayList<>();
        }
        String[] array = new String[linesCount];
        Arrays.fill(array, fillingLine != null ? fillingLine : "");
        return Arrays.stream(array).collect(Collectors.toList());
    }

    public static String charFilledString(int length, char fillingChar) {
        if (length <= 0) {
            return "";
        }
        char[] padArray = new char[length];
        Arrays.fill(padArray, fillingChar);
        return new String(padArray);
    }

    public static List<String> whitespaceLines(int linesCount) {
        return lineFilledList(linesCount, " ");
    }

    public static List<String> padAndBorderTopAndBottom(int baseWidth, Padding padding, String padLine,
                                                        Borders borders, List<String> formattedLines) {
        if (padding == null && (borders == null || borders.getHorizontal() == null)) {
            return new ArrayList<>(formattedLines);
        }
        return padAndBorderTopAndBottom(baseWidth,
                padding != null ? padding.getTop() : 0,
                padding != null ? padding.getBottom() : 0,
                padLine, borders, formattedLines);
    }

    public static List<String> padAndBorderTopAndBottom(int baseWidth, int paddingTop, int paddingBottom,
                                                        String padLine, Borders borders, List<String> formattedLines) {
        paddingTop = paddingTop > 0 ? paddingTop : 0;
        paddingBottom = paddingBottom > 0 ? paddingBottom : 0;
        if (paddingTop == 0 && paddingBottom == 0 && (borders == null || borders.getHorizontal() == null)) {
            return new ArrayList<>(formattedLines);
        }
        List<String> result = new ArrayList<>(paddingTop + paddingBottom + formattedLines.size() +
                (borders != null && borders.getHorizontal() != null ? 2 : 0));
        String topBorder = horizontalBorder(baseWidth, borders, true);
        String bottomBorder = horizontalBorder(baseWidth, borders, false);
        if (topBorder != null) {
            result.add(topBorder);
        }
        result.addAll(lineFilledList(paddingTop, padLine));
        result.addAll(formattedLines);
        result.addAll(lineFilledList(paddingBottom, padLine));
        if (bottomBorder != null) {
            result.add(bottomBorder);
        }
        return result;
    }

    public static String horizontalBorder(int baseWidth, Borders borders, boolean top) {
        if (borders != null && borders.getHorizontal() != null) {
            String border = charFilledString(baseWidth, borders.getHorizontal());
            if (borders.useCorners()) {
                if (baseWidth < 2) {
                    throw new IllegalArgumentException("baseWidth can not be less than 2 to use corners");
                }
                StringBuilder sb = new StringBuilder(border);
                sb.setCharAt(0, top ? borders.getCorners().getTopLeft() : borders.getCorners().getBottomLeft());
                sb.setCharAt(sb.length() - 1, top ? borders.getCorners().getTopRight()
                        : borders.getCorners().getBottomRight());
                border = sb.toString();
            }
            return border;
        }
        return null;
    }

    public static List<String> createSeparation(int baseWidth, Separations separations) {
        if (separations == null) {
            return Collections.emptyList();
        }
        return createSeparation(baseWidth, separations.getSeparationLinesCount(), separations.getSeparationChar());
    }

    public static List<String> createSeparation(int baseWidth, int linesCount, char separationChar) {
        if (linesCount <= 0) {
            return Collections.emptyList();
        }
        List<String> separation = new ArrayList<>(linesCount);

        String separationLine = charFilledString(baseWidth, separationChar);
        for (int i = 0; i < linesCount; i++) {
            separation.add(separationLine);
        }
        return separation;
    }

    public static List<String> createBorderedSeparation(int baseWidth, Separations separations, char verticalChar) {
        return createBorderedSeparation(baseWidth, separations.getSeparationLinesCount(),
                separations.getSeparationChar(), verticalChar);
    }

    public static List<String> createBorderedSeparation(int baseWidth, int linesCount,
                                                        char separationChar, char verticalChar) {
        if (baseWidth < 2) {
            throw new IllegalArgumentException("Width can not be 2 or less for bordered separation");
        }
        return createSeparation(baseWidth - 2, linesCount, separationChar)
                .stream()
                .map(s -> new StringBuilder().append(verticalChar).append(s).append(verticalChar).toString())
                .collect(Collectors.toList());
    }

    public static String getPaddingFormat(int baseWidth, Padding padding) {
        return getPaddingFormat(baseWidth, padding != null ? padding.getLeft() : 0,
                padding != null ? padding.getRight() : 0);
    }

    public static String getPaddingFormat(int baseWidth, int paddingLeft, int paddingRight) {
        return BlockFormatter.charFilledString(paddingLeft, ' ')
                + "%-" + getWidthForChildren(baseWidth, paddingLeft, paddingRight, false) + "s"
                + BlockFormatter.charFilledString(paddingRight, ' ');
    }

    public static String getPaddingFormatWithBorders(int baseWidth, Padding padding, char verticalBorder) {
        return getPaddingFormatWithBorders(baseWidth,
                padding != null ? padding.getLeft() : 0,
                padding != null ? padding.getRight() : 0,
                verticalBorder);
    }

    public static String getPaddingFormatWithBorders(int baseWidth, int paddingLeft,
                                                     int paddingRight, char verticalBorder) {
        return verticalBorder
                + BlockFormatter.charFilledString(paddingLeft, ' ')
                + "%-" + getWidthForChildren(baseWidth, paddingLeft, paddingRight, true) + "s"
                + BlockFormatter.charFilledString(paddingRight, ' ')
                + verticalBorder;
    }

    public static int getWidthForChildren(int width, Padding padding, boolean hasBorders) {
        return getWidthForChildren(width,
                padding != null ? padding.getLeft() : 0,
                padding != null ? padding.getRight() : 0,
                hasBorders);
    }

    public static int getWidthForChildren(int width, int paddingLeft, int paddingRight, boolean hasBorders) {
        if (width < 1) {
            throw new IllegalArgumentException("Width can not be less than 1");
        }
        paddingLeft = paddingLeft > 0 ? paddingLeft : 0;
        paddingRight = paddingRight > 0 ? paddingRight : 0;
        width = width - paddingLeft;
        width = width - paddingRight;
        if (hasBorders) {
            width = width - 2;
        }
        if (width < 1) {
            throw new IllegalArgumentException("Resulting width can not be less than 1. " +
                    "Check padding and borders settings.");
        }
        return width;
    }

    // TODO: 25.09.2016 allow null blocks?
    public static List<String> formatBlocks(List<DisplayBlock> blocks, int baseWidth,
                                            Separations separations, Padding padding, Borders borders) {
        String format;
        if (borders != null && borders.getVertical() != null) {
            format = getPaddingFormatWithBorders(baseWidth, padding, borders.getVertical());
        } else {
            format = getPaddingFormat(baseWidth, padding);
        }
        int widthForChildren = getWidthForChildren(baseWidth, padding, borders != null);
        String padLine = String.format(format, "");
        List<String> result = new ArrayList<>();
        if (separations != null && separations.getSeparationLinesCount() > 0) {

            //prepare separation lines
            List<String> separation;
            if (borders != null && borders.getVertical() != null) {
                separation = BlockFormatter.createBorderedSeparation(baseWidth,
                        separations,
                        borders.getVertical());
            } else {
                separation = BlockFormatter.createSeparation(baseWidth, separations);
            }

            //append to result each block and a separator after
            Iterator<DisplayBlock> iterator = blocks.listIterator();

            while (iterator.hasNext()) {
                //formatting and appending lines of child block to result
                result.addAll(iterator.next()
                        .buildLines(widthForChildren)
                        .stream()
                        .map(s -> String.format(format, s))
                        .collect(Collectors.toList()));
                //append separator if there is a next block
                if (iterator.hasNext()) {
                    result.addAll(separation);
                }
            }
//            return result;
        } else {
            //just append formatted blocks to each other
            result = blocks.stream()
                    .flatMap(b -> b.buildLines(widthForChildren).stream())
                    .map(s -> String.format(format, s))
                    .collect(Collectors.toList());
        }
        return padAndBorderTopAndBottom(baseWidth, padding, padLine, borders, result);
    }

    public static void validateWidth(int width) {
        if (width < 1) {
            throw new IllegalArgumentException("Width can not be less than 1");
        }
    }
}
