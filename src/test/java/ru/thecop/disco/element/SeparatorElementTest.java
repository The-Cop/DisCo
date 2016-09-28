package ru.thecop.disco.element;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public class SeparatorElementTest {

    @Test
    public void simpleTest() {
        List<String> rows = new SeparatorElement(1).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("--------------------"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void linesCountExceptionTest() {
        new SeparatorElement(0).buildLines(20);
    }

    @Test
    public void customCharTest() {
        List<String> rows = new SeparatorElement(1, 'z').buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("zzzzzzzzzzzzzzzzzzzz"));
    }

    @Test
    public void customMultipleLinesCharTest() {
        List<String> rows = new SeparatorElement(3, 'z').buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains(
                "zzzzzzzzzzzzzzzzzzzz",
                "zzzzzzzzzzzzzzzzzzzz",
                "zzzzzzzzzzzzzzzzzzzz"));
    }
}
