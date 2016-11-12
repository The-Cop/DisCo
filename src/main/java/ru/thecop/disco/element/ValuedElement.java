package ru.thecop.disco.element;

import ru.thecop.disco.Alignment;
import ru.thecop.disco.DisplayBlock;
import ru.thecop.disco.LineFormatter;

import java.util.List;

import static ru.thecop.disco.BlockFormatter.charFilledString;

public class ValuedElement implements DisplayBlock {

    // TODO: 12.11.2016 implement maximum width?
    private String text;
    private String value;

    //settings
    private int minValueSpacing = 2;
    private char valueSpacingChar = '.';

    public ValuedElement(String text, String value, int minValueSpacing, char valueSpacingChar) {
        this.text = text;
        this.value = value;
        this.minValueSpacing = minValueSpacing;
        this.valueSpacingChar = valueSpacingChar;
    }

    public ValuedElement(String text, String value) {
        this.text = text;
        this.value = value;
    }

    private void validateState(int minValueSpacing) {
        if (value == null) {
            throw new IllegalArgumentException("value is null");
        }
        if (minValueSpacing < 1) {
            throw new IllegalStateException("minValueSpacing can not be less than 1");
        }
    }

    @Override
    public List<String> buildLines(int width) {
        validateState(minValueSpacing);
        validateForWidth(width, minValueSpacing);

        List<String> formattedText = LineFormatter.formatToWidth(text, width, Alignment.LEFT);

        //trivial - no text, just add the value line
        if (formattedText.isEmpty()) {
            formattedText.add(formatAsNewLine(width));
            return formattedText;
        }
        String lastRow = formattedText.get(formattedText.size() - 1);

        //if value fits the last row
        if (lastRow.length() + getValueLengthWithSpacing(minValueSpacing) <= width) {
            formattedText.add(formatWithExistingLine(formattedText.remove(formattedText.size() - 1),
                    width));
            return formattedText;
        } else {
            //value does not fit last row
            formattedText.add(padLineWithSpacing(formattedText.remove(formattedText.size() - 1),
                    width));
            formattedText.add(formatAsNewLine(width));
            return formattedText;
        }
    }

    private String padLineWithSpacing(String line, int width) {
        int padLength = width - line.length();
        String padString = charFilledString(padLength, valueSpacingChar);
        return line + padString;
    }

    private String formatWithExistingLine(String line, int width) {
        int padLength = width - value.length() - line.length();
        String padString = charFilledString(padLength, valueSpacingChar);
        return line + padString + value;
    }

    private String formatAsNewLine(int width) {
        return formatWithExistingLine("", width);
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
