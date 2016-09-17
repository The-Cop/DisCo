package ru.thecop.disco.block.impl;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.block.Block;
import ru.thecop.disco.element.Element;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import static ru.thecop.disco.Util.getPadString;

public class SimpleBlock implements Block {

    private List<Element> elements = new ArrayList<>();

    public SimpleBlock(List<Element> elements) {
        if (elements == null) {
            throw new NullPointerException("elements can not be null");
        }
        this.elements = elements;
    }

    public SimpleBlock(Element element) {
        // TODO: 17.09.2016 allow nulls?
        if (element == null) {
            throw new NullPointerException("element can not be null");
        }
        this.elements.add(element);
    }

    @Override
    public List<Element> getElements() {
        return elements;
    }

    @Override
    public List<String> formatToWidth(int width, DisplaySettings displaySettings) {
        validateWidth(width);
        if (elements.isEmpty()) {
            return new ArrayList();
        }
        if (elements.size() == 1) {
            return elements.get(0).formatToWidth(width, displaySettings);
        }

        List<String> result = new ArrayList<>();
        List<String> separation = createSeparation(width, displaySettings);
        ListIterator<Element> iterator = elements.listIterator();
        while (iterator.hasNext()) {
            if (iterator.hasPrevious()) {
                result.addAll(separation);
            }
            Element element = iterator.next();
            result.addAll(element.formatToWidth(width, displaySettings));
        }
        return result;
    }

    private List<String> createSeparation(int width, DisplaySettings displaySettings) {
        if (displaySettings.getBlockElementsSeparationLinesCount() == 0) {
            return Collections.emptyList();
        }
        List<String> separation = new ArrayList<>();

        String separationLine = getPadString(width, displaySettings.getBlockElementsSeparationChar());
        for (int i = 0; i < displaySettings.getBlockElementsSeparationLinesCount(); i++) {
            separation.add(separationLine);
        }
        return separation;
    }
}
