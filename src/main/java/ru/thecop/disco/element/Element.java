package ru.thecop.disco.element;

import ru.thecop.disco.DisplaySettings;

import java.util.List;

/**
 * A basic component - contains text (paragraph) which is properly
 * formatted according to it's type and enclosing @{@link Block}
 */
public interface Element {

    List<String> formatToWidth(int width, DisplaySettings displaySettings);

    default void validateWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be positive");
        }
    }
}
