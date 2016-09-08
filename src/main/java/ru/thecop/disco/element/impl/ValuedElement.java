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
    private int customMinValueSpacing;
    private char customValueSpacingChar;

    private boolean customized;

    public ValuedElement(String text, String value, int minValueSpacing, char valueSpacingChar) {
        this.text = text;
        this.value = value;
        this.customMinValueSpacing = minValueSpacing;
        this.customValueSpacingChar = valueSpacingChar;
        customized = true;
    }

    public ValuedElement(String text, String value) {
        this.text = text;
        this.value = value;
    }

    private void validateSettings(int minValueSpacing) {
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (minValueSpacing < 1) {
            throw new IllegalStateException("minValueSpacing can not be less than 1");
        }
    }

    @Override
    public List<String> formatToWidth(int width, DisplaySettings displaySettings) {
        char valueSpacingChar = customized ? customValueSpacingChar : displaySettings.getValuedElementSpacingChar();
        int minValueSpacing = customized ? customMinValueSpacing : displaySettings.getValuedElementMinValueSpacing();
        validateSettings(minValueSpacing);
        validateForWidth(width, minValueSpacing);

        List<String> formattedText = Formatter.formatToWidth(text, width);

        //trivial - no text, just add the value line
        if (formattedText.isEmpty()) {
            formattedText.add(formatAsNewLine(width, valueSpacingChar));
            return formattedText;
        }
        String lastRow = formattedText.get(formattedText.size() - 1);

        //if value fits the last row
        if (lastRow.length() + getValueLengthWithSpacing(minValueSpacing) <= width) {
            formattedText.add(formatWithExistingLine(formattedText.remove(formattedText.size() - 1),
                    width, valueSpacingChar));
            return formattedText;
        } else {
            //value does not fit last row
            formattedText.add(padLineWithSpacing(formattedText.remove(formattedText.size() - 1),
                    width, valueSpacingChar));
            formattedText.add(formatAsNewLine(width, valueSpacingChar));
            return formattedText;
        }
    }

    private String padLineWithSpacing(String line, int width, char valueSpacingChar) {
        int padLength = width - line.length();
        String padString = getPadString(padLength, valueSpacingChar);
        return line + padString;
    }

    private String formatWithExistingLine(String line, int width, char valueSpacingChar) {
        int padLength = width - value.length() - line.length();
        String padString = getPadString(padLength, valueSpacingChar);
        return line + padString + value;
    }

    private String getPadString(int padLength, char valueSpacingChar) {
        char[] padArray = new char[padLength];
        Arrays.fill(padArray, valueSpacingChar);
        return new String(padArray);
    }

    private String formatAsNewLine(int width, char valueSpacingChar) {
        return formatWithExistingLine("", width, valueSpacingChar);
    }

    private void validateForWidth(int width, int minValueSpacing) {
        int valueLength = getValueLengthWithSpacing(minValueSpacing);
        if (valueLength > width) {
            throw new IllegalStateException(
                    String.format(
                            "Value length with specified minValueSpacing (%d) is larger than formatting width (%d)",
                            valueLength,
                            width));
        }
    }

    private int getValueLengthWithSpacing(int minValueSpacing) {
        return value.length() + minValueSpacing;
    }
}
