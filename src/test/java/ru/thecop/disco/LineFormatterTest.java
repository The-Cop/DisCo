package ru.thecop.disco;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

public class LineFormatterTest {

    @Test
    public void testSimple() {
        Aligment aligment = Aligment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 4, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testBigWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 666, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testSmallWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 3, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes", ", I", "am", "tex", "t"));
    }

    @Test
    public void testLongWord() {
        Aligment aligment = Aligment.LEFT;
        String text = "Yes, Word Antidisestablishmentarianism is very long";
        List<String> rows = LineFormatter.formatToWidth(text, 11, aligment);

        assertThat(rows, IsIterableContainingInOrder.contains("Yes, Word",
                "Antidisesta",
                "blishmentar",
                "ianism is",
                "very long"));
    }

    @Test
    public void testSpaces() {
        Aligment aligment = Aligment.LEFT;
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 4, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testSpacesBigWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 666, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testTabs() {
        Aligment aligment = Aligment.LEFT;
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 4, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testTabsBigWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 666, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNewlines() {
        Aligment aligment = Aligment.LEFT;
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 4, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testNewlinesBigWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 666, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNull() {
        Aligment aligment = Aligment.LEFT;
        List<String> rows = LineFormatter.formatToWidth(null, 666, aligment);
        assertEquals(0, rows.size());
    }

    @Test
    public void testEmptyText() {
        Aligment aligment = Aligment.LEFT;
        List<String> rows = LineFormatter.formatToWidth("", 666, aligment);
        assertEquals(0, rows.size());
    }

    @Test
    public void testWhitespaceText() {
        Aligment aligment = Aligment.LEFT;
        List<String> rows = LineFormatter.formatToWidth("  \t     \t    ", 666, aligment);
        assertEquals(0, rows.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroWidth() {
        Aligment aligment = Aligment.LEFT;
        String text = "Yes, I am text";
        LineFormatter.formatToWidth(text, 0, aligment);
    }

    //aligment tests
    @Test
    public void testAligmentRight() {
        Aligment aligment = Aligment.RIGHT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("      Yes, I am text"));
    }

    @Test
    public void testAligmentRight2() {
        Aligment aligment = Aligment.RIGHT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 5, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains(" Yes,", " I am", " text"));
    }

    @Test
    public void testAligmentCenter() {
        Aligment aligment = Aligment.CENTER;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("   Yes, I am text   "));
    }

    @Test
    public void testAligmentCenter2() {
        Aligment aligment = Aligment.CENTER;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 21, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("    Yes, I am text   "));
    }

    @Test
    public void testAligmentNull() {
        Aligment aligment = null;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, aligment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }
}
