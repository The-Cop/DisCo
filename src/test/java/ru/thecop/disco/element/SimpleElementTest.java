package ru.thecop.disco.element;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class SimpleElementTest {

    // TODO: 12.11.2016 write tests
    @Test
    public void nullText() {
        SimpleElement e = new SimpleElement(null);
        assertEquals(0, e.buildLines(10).size());
    }
}
