package ru.thecop.disco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.thecop.disco.BlockFormatter.getPaddingFormat;

public final class LineFormatter {

    private LineFormatter() {
    }

    /**
     * Formats given line to a number of lines (rows) that fit given width
     *
     * @param line     a text to format
     * @param width    width the lines will be fit to
     * @param aligment
     * @return a list of strings (lines), each will have length equal or less than given width.
     */
    public static List<String> formatToWidth(String line, int width, Aligment aligment) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be greater than zero");
        }
        if (line == null || line.trim().isEmpty()) {
            return new ArrayList<>();
        }

        line = line.trim();
        //splitting by spaces to get separate words
        List<String> split = new ArrayList<>(Arrays.asList(line.split(" ")));
        return formatWords(split, width, aligment);
    }

    private static List<String> formatWords(List<String> words, int width, Aligment aligment) {
        LinkedList<String> rows = new LinkedList<>();
        if (words.size() == 0) {
            return rows;
        }

        Iterator<String> iterator = words.iterator();

        while (iterator.hasNext()) {
            String word = iterator.next();
            rows = formatAndAppendWord(word, width, rows);
            iterator.remove();
        }

        return rows.stream().map(row -> alignRow(row, width, aligment)).collect(Collectors.toList());
    }

    private static String alignRow(String row, int width, Aligment aligment) {
        if (aligment == null) {
            return row;
        }
        switch (aligment) {
            case LEFT:
                return row;
            case RIGHT: {
                int leftPadding = width - row.length();
                if (leftPadding == 0) {
                    return row;
                }
                String format = getPaddingFormat(width, leftPadding, 0);
                return String.format(format, row);
            }
            case CENTER: {
                int freeSpace = width - row.length();
                if (freeSpace == 0) {
                    return row;
                }
                int rightPadding = freeSpace / 2;
                int leftPadding = freeSpace - rightPadding;
                String format = getPaddingFormat(width, leftPadding, rightPadding);
                return String.format(format, row);
            }
            default:
                return row;
        }
    }

    private static LinkedList<String> formatAndAppendWord(String word, int width, LinkedList<String> rows) {
        word = word.trim();
        //empty word - do nothing.
        if (word.isEmpty()) {
            return rows;
        }
        //long word
        if (word.length() > width) {
            rows.addAll(formatLongWord(word, width));
            return rows;
        }
        //ordinary fitting-to-width word
        StringBuilder sb = new StringBuilder();

        //if rows exist and if word is short enough to fit the last row - append it to it.
        if (!rows.isEmpty() && rows.getLast().length() + word.length() + 1 <= width) {
            sb.append(rows.removeLast());
            sb.append(" ").append(word);
            rows.add(sb.toString());
            return rows;
        }
        //if this is first word
        //or
        //if word is too long - add it as new row
        rows.add(word);
        return rows;
    }

    private static Collection<? extends String> formatLongWord(String word, int width) {
        List<String> rows = new ArrayList<>();
        while (word.length() > width) {
            rows.add(word.substring(0, width));
            word = word.substring(width);
        }
        rows.add(word);
        return rows;
    }
}
