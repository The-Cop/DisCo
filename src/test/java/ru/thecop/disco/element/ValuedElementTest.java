package ru.thecop.disco.element;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;

public class ValuedElementTest {

    @Test
    public void simpleTest() {
        String text = "Some valued thing";
        String value = "value";
        List<String> rows = new ValuedElement(text, value).buildLines(30);
        assertThat(rows, IsIterableContainingInOrder.contains("Some valued thing........value"));
    }

    @Test
    public void simpleDashTest() {
        String text = "Some valued thing";
        String value = "value";
        List<String> rows = new ValuedElement(text, value, 1, '-').buildLines(30);
        assertThat(rows, IsIterableContainingInOrder.contains("Some valued thing--------value"));
    }

    @Test
    public void overLineTest() {
        String text = "Some valued longthingname";
        String value = "value";
        List<String> rows = new ValuedElement(text, value).buildLines(17);
        assertThat(rows, IsIterableContainingInOrder.contains(
                "Some valued",
                "longthingname....",
                "............value"));
    }

    @Test
    public void bigSpacingTest() {
        String text = "Some valued thing";
        String value = "value";
        List<String> rows = new ValuedElement(text, value, 10, '.').buildLines(30);
        assertThat(rows, IsIterableContainingInOrder.contains(
                "Some valued thing.............",
                ".........................value"));
    }

    @Test
    public void emptyTextTest() {
        String text = "";
        String value = "value";
        List<String> rows = new ValuedElement(text, value).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("...............value"));
    }

    @Test
    public void nullTextTest() {
        String value = "value";
        List<String> rows = new ValuedElement(null, value).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("...............value"));
    }

    @Test
    public void emptyValueTest() {
        String text = "Some valued thing";
        String value = "";
        List<String> rows = new ValuedElement(text, value).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("Some valued thing..."));
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullValueTest() {
        String text = "Some valued thing";
        List<String> rows = new ValuedElement(text, null).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("...............value"));
    }

    @Test
    public void emptyTextAndEmptyValueTest() {
        String text = "";
        String value = "";
        List<String> rows = new ValuedElement(text, value).buildLines(20);
        assertThat(rows, IsIterableContainingInOrder.contains("...................."));
    }

    @Test(expected = IllegalStateException.class)
    public void tooSmallWidthExceptionTest() {
        String text = "Some valued thing";
        String value = "value";
        new ValuedElement(text, value).buildLines(4);
    }

    @Test(expected = IllegalStateException.class)
    public void tooBigSpacingExceptionTest() {
        String text = "Some valued thing";
        String value = "value";
        new ValuedElement(text, value, 10, '.').buildLines(14);
    }

    @Test(expected = IllegalStateException.class)
    public void tooSmallSpacingExceptionTest() {
        new ValuedElement("", "", 0, '.').buildLines(100);
    }

    // TODO: 29.09.2016 test null arguments
}
