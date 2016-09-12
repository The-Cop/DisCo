package ru.thecop.disco.block.impl;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.block.Block;
import ru.thecop.disco.element.Element;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlock implements Block {

    List<Element> elements = new ArrayList<>();

    public SimpleBlock(List<Element> elements) {
        this.elements = elements;
    }

    public SimpleBlock(Element element) {
        this.elements.add(element);
    }

    @Override
    public List<Element> getElements() {
        return elements;
    }

    @Override
    public List<String> formatToWidth(int width, DisplaySettings displaySettings) {
        validateWidth(width);

        return null;
    }
}
