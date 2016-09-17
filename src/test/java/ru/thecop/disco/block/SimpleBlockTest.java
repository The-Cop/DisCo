package ru.thecop.disco.block;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import ru.thecop.disco.AbstractDisplaySettingsTest;
import ru.thecop.disco.block.impl.SimpleBlock;
import ru.thecop.disco.element.Element;
import ru.thecop.disco.element.impl.SimpleElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

public class SimpleBlockTest extends AbstractDisplaySettingsTest {

    @Test(expected = IllegalArgumentException.class)
    public void wrongWidthTest() {
        Element e = new SimpleElement("I am simple element");
        new SimpleBlock(e).formatToWidth(0, getDisplaySettings());
    }

    @Test
    public void emptyListTest() {
        List<String> rows = new SimpleBlock(new ArrayList<>()).formatToWidth(20, getDisplaySettings());
        assertEquals(rows.size(), 0);
    }

    @Test(expected = NullPointerException.class)
    public void nullElementTest() {
        Element e = null;
        new SimpleBlock(e).formatToWidth(20, getDisplaySettings());
    }

    @Test(expected = NullPointerException.class)
    public void nullListTest() {
        List<Element> e = null;
        new SimpleBlock(e).formatToWidth(20, getDisplaySettings());
    }

    @Test
    public void simpleWideWidthTest() {
        Element e = new SimpleElement("I am simple element");
        List<String> rows = new SimpleBlock(e).formatToWidth(20, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am simple element"));
    }

    @Test
    public void simpleSmallWidthTest() {
        Element e = new SimpleElement("I am simple element");
        List<String> rows = new SimpleBlock(e).formatToWidth(10, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am", "simple", "element"));
    }

    @Test
    public void simpleVerySmallWidthTest() {
        Element e = new SimpleElement("I am simple element");
        List<String> rows = new SimpleBlock(e).formatToWidth(4, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am", "simp", "le", "elem", "ent"));
    }

    @Test
    public void multipleSimpleTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(20, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am simple element", "An another goes here"));
    }

    @Test
    public void multipleSimpleWideWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(200, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am simple element", "An another goes here"));
    }

    @Test
    public void multipleSimpleSmallWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(10, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains("I am", "simple", "element", "An another", "goes here"));
    }

    @Test
    public void multipleSimpleVerySmallWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(4, getDisplaySettings());
        assertThat(rows, IsIterableContainingInOrder.contains(
                "I am", "simp", "le", "elem", "ent",
                "An", "anot", "her", "goes", "here"
        ));
    }

    @Test
    public void multipleSeparatedSimpleWideWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(25,
                getDisplaySettings().setBlockElementsSeparationLinesCount(2));
        assertThat(rows, IsIterableContainingInOrder.contains(
                "I am simple element",
                "#########################",
                "#########################",
                "An another goes here"));
    }

    @Test
    public void multipleSeparatedSimpleSmallWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(10,
                getDisplaySettings().setBlockElementsSeparationLinesCount(2));
        assertThat(rows, IsIterableContainingInOrder.contains(
                "I am", "simple", "element",
                "##########",
                "##########",
                "An another", "goes here"));
    }

    @Test
    public void multipleSeparatedSimpleVerySmallWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(4,
                getDisplaySettings().setBlockElementsSeparationLinesCount(2));
        assertThat(rows, IsIterableContainingInOrder.contains(
                "I am", "simp", "le", "elem", "ent",
                "####",
                "####",
                "An", "anot", "her", "goes", "here"
        ));
    }

    @Test
    public void fourElementsSeparatedSimpleWideWidthTest() {
        List<Element> elements = Arrays.asList(
                new SimpleElement("I am simple element"),
                new SimpleElement("The second"),
                new SimpleElement("The third"),
                new SimpleElement("An another goes here"));
        List<String> rows = new SimpleBlock(elements).formatToWidth(25,
                getDisplaySettings().setBlockElementsSeparationLinesCount(2));
        assertThat(rows, IsIterableContainingInOrder.contains(
                "I am simple element",
                "#########################",
                "#########################",
                "The second",
                "#########################",
                "#########################",
                "The third",
                "#########################",
                "#########################",
                "An another goes here"));
    }
}
