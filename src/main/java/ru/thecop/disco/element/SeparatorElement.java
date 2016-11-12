package ru.thecop.disco.element;

import ru.thecop.disco.DisplayBlock;
import ru.thecop.disco.BlockFormatter;

import java.util.ArrayList;
import java.util.List;

import static ru.thecop.disco.BlockFormatter.validateWidth;

public class SeparatorElement implements DisplayBlock {

    private int linesCount;
    private char separatorChar = '-';

    public SeparatorElement(int linesCount, char separatorElementChar) {
        this.linesCount = linesCount;
        this.separatorChar = separatorElementChar;
        validateSettings();
    }

    public SeparatorElement(int linesCount) {
        this.linesCount = linesCount;
        validateSettings();
    }

    @Override
    public List<String> buildLines(int width) {
        validateWidth(width);
        List<String> result = new ArrayList<>();
        String line = BlockFormatter.charFilledString(width, separatorChar);
        for (int i = 0; i < linesCount; i++) {
            result.add(line);
        }
        return result;
    }

    private void validateSettings() {
        if (linesCount <= 0) {
            throw new IllegalArgumentException("linesCount must be positive");
        }
    }
}
