package ru.thecop.disco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class LineFormatter {

    private LineFormatter() {
    }

    /**
     * Formats given line to a number of lines (rows) that fit given width
     *
     * @param line a text to format
     * @param width     width the lines will be fit to
     * @return a list of strings (lines), each will have length equal or less than given width.
     */
    public static List<String> formatToWidth(String line, int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be greater than zero");
        }
        if (line == null || line.trim().isEmpty()) {
            return new ArrayList<>();
        }
        line = line.trim();
        //splitting by spaces to get separate words
        List<String> split = new ArrayList<>(Arrays.asList(line.split(" ")));
        return formatWords(split, width);
    }

    private static List<String> formatWords(List<String> words, int width) {
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
        return rows;
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
