package ru.thecop.disco.element.impl;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.element.Element;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeparatorElement implements Element {

    private boolean customized;
    private int linesCount;
    private char customSeparatorElementChar;

    public SeparatorElement(int linesCount, char separatorElementChar) {
        this.linesCount = linesCount;
        this.customSeparatorElementChar = separatorElementChar;
        customized = true;
        validateSettings();
    }

    public SeparatorElement(int linesCount) {
        this.linesCount = linesCount;
        validateSettings();
    }

    @Override
    public List<String> formatToWidth(int width, DisplaySettings displaySettings) {
        validateWidth(width);
        char[] array = new char[width];
        Arrays.fill(array, customized ? customSeparatorElementChar : displaySettings.getBlockElementsSeparationChar());
        List<String> result = new ArrayList<>();
        for (int i = 0; i < linesCount; i++) {
            result.add(new String(array));
        }
        return result;
    }

    private void validateSettings() {
        if (linesCount <= 0) {
            throw new IllegalArgumentException("linesCount must be positive");
        }
    }
}
