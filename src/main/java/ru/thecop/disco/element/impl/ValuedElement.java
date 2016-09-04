package ru.thecop.disco.element.impl;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.Formatter;
import ru.thecop.disco.element.Element;

import java.util.Arrays;
import java.util.List;

public class ValuedElement implements Element {

    private String text;
    private String value;

    //settings
    private int minValueSpacing;
    private char valueSpacingChar;

    public ValuedElement(String text, String value, int minValueSpacing, char valueSpacingChar) {
        this.text = text;
        this.value = value;
        this.minValueSpacing = minValueSpacing;
        this.valueSpacingChar = valueSpacingChar;
        validateSettings();
    }

    public ValuedElement(String text, String value, DisplaySettings displaySettings) {
        this.text = text;
        this.value = value;
        this.minValueSpacing = displaySettings.getMinValueSpacing();
        this.valueSpacingChar = displaySettings.getValueSpacingChar();
        validateSettings();
    }

    private void validateSettings() {
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (minValueSpacing < 1) {
            throw new IllegalStateException("minValueSpacing can not be less than 1");
        }
    }

    @Override
    public List<String> formatToWidth(int width) {
        validateForWidth(width);
        List<String> formattedText = Formatter.formatToWidth(text, width);

        //trivial - no text, just add the value line
        if (formattedText.isEmpty()) {
            formattedText.add(formatAsNewLine(width));
            return formattedText;
        }
        String lastRow = formattedText.get(formattedText.size() - 1);

        //if value fits the last row
        if (lastRow.length() + getValueLengthWithSpacing() <= width) {
            formattedText.add(formatWithExistingLine(formattedText.remove(formattedText.size() - 1), width));
            return formattedText;
        } else {
            //value does not fit last row
            formattedText.add(padLineWithSpacing(formattedText.remove(formattedText.size() - 1), width));
            formattedText.add(formatAsNewLine(width));
            return formattedText;
        }
    }

    private String padLineWithSpacing(String line, int width) {
        int padLength = width - line.length();
        String padString = getPadString(padLength);
        return line + padString;
    }

    private String formatWithExistingLine(String line, int width) {
        int padLength = width - value.length() - line.length();
        String padString = getPadString(padLength);
        return line + padString + value;
    }

    private String getPadString(int padLength) {
        char[] padArray = new char[padLength];
        Arrays.fill(padArray, valueSpacingChar);
        return new String(padArray);
    }

    private String formatAsNewLine(int width) {
        return formatWithExistingLine("", width);
    }

    private void validateForWidth(int width) {
        int valueLength = getValueLengthWithSpacing();
        if (valueLength > width) {
            throw new IllegalStateException(
                    String.format(
                            "Value length with specified minValueSpacing (%d) is larger than formatting width (%d)",
                            getValueLengthWithSpacing(),
                            width));
        }
    }

    private int getValueLengthWithSpacing() {
        return value.length() + minValueSpacing;
    }
}
