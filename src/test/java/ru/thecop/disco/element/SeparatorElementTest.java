package ru.thecop.disco.element;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import ru.thecop.disco.AbstractDisplaySettingsTest;
import ru.thecop.disco.element.impl.SeparatorElement;

import java.util.List;

import static org.junit.Assert.assertThat;

public class SeparatorElementTest extends AbstractDisplaySettingsTest {

    @Test
    public void simpleTest() {
        List<String> rows = new SeparatorElement(1).formatToWidth(20, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("####################"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void linesCountExceptionTest() {
        new SeparatorElement(0).formatToWidth(20, getDisplaySettings());
    }

    @Test
    public void customCharTest() {
        List<String> rows = new SeparatorElement(1, 'z').formatToWidth(20, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("zzzzzzzzzzzzzzzzzzzz"));
    }

    @Test
    public void customMultipleLinesCharTest() {
        List<String> rows = new SeparatorElement(3, 'z').formatToWidth(20, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains(
                "zzzzzzzzzzzzzzzzzzzz",
                "zzzzzzzzzzzzzzzzzzzz",
                "zzzzzzzzzzzzzzzzzzzz"));
    }
}
