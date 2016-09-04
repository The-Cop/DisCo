package ru.thecop.disco.element.impl;

import ru.thecop.disco.element.Element;

import java.util.List;

public class SeparatorElement implements Element {

    @Override
    public List<String> formatToWidth(int width) {
        return null;
    }

    //TODO implement - returns a line (or several lines) filled with 'elementSeparator' chars
    // (given or from settings), or just empty lines
}
