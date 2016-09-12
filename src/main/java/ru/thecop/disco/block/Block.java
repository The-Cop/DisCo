package ru.thecop.disco.block;

import ru.thecop.disco.DisplaySettings;
import ru.thecop.disco.container.Container;
import ru.thecop.disco.element.Element;

import java.util.List;

/**
 * A component with it's own width, must be within a @{@link Container}. Contains one or more @{@link Element}s.
 * Does not have own borders.
 */
public interface Block {

    List<Element> getElements();

    List<String> formatToWidth(int width, DisplaySettings displaySettings);

    default void validateWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("width must be positive");
        }
    }
}
