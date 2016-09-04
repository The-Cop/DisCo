package ru.thecop.disco.element;

import java.util.List;

/**
 * A basic component - contains text (paragraph) which is properly
 * formatted according to it's type and enclosing @{@link Block}
 */
public interface Element {

    //TODO implement
    List<String> formatToWidth(int width);
}
