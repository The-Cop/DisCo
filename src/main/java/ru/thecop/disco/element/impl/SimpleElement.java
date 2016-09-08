package ru.thecop.disco.element.impl;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.Formatter;
import ru.thecop.disco.element.Element;

import java.util.List;

public class SimpleElement implements Element {

    private String text;

    public SimpleElement(String text) {
        this.text = text;
    }

    @Override
    public List<String> formatToWidth(int width, DisplaySettings displaySettings) {
        return Formatter.formatToWidth(text, width);
    }
}
