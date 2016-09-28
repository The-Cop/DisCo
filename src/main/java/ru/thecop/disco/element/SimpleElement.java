package ru.thecop.disco.element;

import ru.thecop.disco.DisplayBlock;
import ru.thecop.disco.Formatter;

import java.util.List;

public class SimpleElement implements DisplayBlock {

    private String text;

    public SimpleElement(String text) {
        this.text = text;
    }

    @Override
    public List<String> buildLines(int width) {
        return Formatter.formatToWidth(text, width);
    }
}
