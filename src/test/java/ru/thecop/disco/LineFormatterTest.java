package ru.thecop.disco;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

public class LineFormatterTest {

    @Test
    public void testSimple() {
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 4);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testBigWidth() {
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 666);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testSmallWidth() {
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 3);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes", ", I", "am", "tex", "t"));
    }

    @Test
    public void testLongWord() {
        String text = "Yes, Word Antidisestablishmentarianism is very long";
        List<String> rows = LineFormatter.formatToWidth(text, 11);

        assertThat(rows, IsIterableContainingInOrder.contains("Yes, Word",
                "Antidisesta",
                "blishmentar",
                "ianism is",
                "very long"));
    }

    @Test
    public void testSpaces() {
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 4);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testSpacesBigWidth() {
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 666);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testTabs() {
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 4);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testTabsBigWidth() {
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 666);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNewlines() {
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 4);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testNewlinesBigWidth() {
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 666);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNull() {
        List<String> rows = LineFormatter.formatToWidth(null, 666);
        assertEquals(0, rows.size());
    }

    @Test
    public void testEmptyText() {
        List<String> rows = LineFormatter.formatToWidth("", 666);
        assertEquals(0, rows.size());
    }

    @Test
    public void testWhitespaceText() {
        List<String> rows = LineFormatter.formatToWidth("  \t     \t    ", 666);
        assertEquals(0, rows.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroWidth() {
        String text = "Yes, I am text";
        LineFormatter.formatToWidth(text, 0);
    }
}
