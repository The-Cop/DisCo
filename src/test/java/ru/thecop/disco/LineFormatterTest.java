package ru.thecop.disco;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertThat;

public class LineFormatterTest {

    @Test
    public void testSimple() {
        Alignment alignment = Alignment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 4, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testBigWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 666, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testSmallWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 3, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes", ", I", "am", "tex", "t"));
    }

    @Test
    public void testLongWord() {
        Alignment alignment = Alignment.LEFT;
        String text = "Yes, Word Antidisestablishmentarianism is very long";
        List<String> rows = LineFormatter.formatToWidth(text, 11, alignment);

        assertThat(rows, IsIterableContainingInOrder.contains("Yes, Word",
                "Antidisesta",
                "blishmentar",
                "ianism is",
                "very long"));
    }

    @Test
    public void testSpaces() {
        Alignment alignment = Alignment.LEFT;
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 4, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testSpacesBigWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = "  Yes,    I     am             text       ";
        List<String> rows = LineFormatter.formatToWidth(text, 666, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testTabs() {
        Alignment alignment = Alignment.LEFT;
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 4, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testTabsBigWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = " \t\tYes,    \t     I     \t         \t        \tam\t          \ttext            \t";
        List<String> rows = LineFormatter.formatToWidth(text, 666, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNewlines() {
        Alignment alignment = Alignment.LEFT;
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 4, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes,", "I am", "text"));
    }

    @Test
    public void testNewlinesBigWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = "\n  \n\nYes,       I\n      \n      \n     \n     \nam\n          \ntext   \n         \n";
        List<String> rows = LineFormatter.formatToWidth(text, 666, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }

    @Test
    public void testNull() {
        Alignment alignment = Alignment.LEFT;
        List<String> rows = LineFormatter.formatToWidth(null, 666, alignment);
        assertEquals(0, rows.size());
    }

    @Test
    public void testEmptyText() {
        Alignment alignment = Alignment.LEFT;
        List<String> rows = LineFormatter.formatToWidth("", 666, alignment);
        assertEquals(0, rows.size());
    }

    @Test
    public void testWhitespaceText() {
        Alignment alignment = Alignment.LEFT;
        List<String> rows = LineFormatter.formatToWidth("  \t     \t    ", 666, alignment);
        assertEquals(0, rows.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testZeroWidth() {
        Alignment alignment = Alignment.LEFT;
        String text = "Yes, I am text";
        LineFormatter.formatToWidth(text, 0, alignment);
    }

    //aligment tests
    @Test
    public void testAligmentRight() {
        Alignment alignment = Alignment.RIGHT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("      Yes, I am text"));
    }

    @Test
    public void testAligmentRight2() {
        Alignment alignment = Alignment.RIGHT;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 5, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains(" Yes,", " I am", " text"));
    }

    @Test
    public void testAligmentCenter() {
        Alignment alignment = Alignment.CENTER;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("   Yes, I am text   "));
    }

    @Test
    public void testAligmentCenter2() {
        Alignment alignment = Alignment.CENTER;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 21, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("    Yes, I am text   "));
    }

    @Test
    public void testAligmentNull() {
        Alignment alignment = null;
        String text = "Yes, I am text";
        List<String> rows = LineFormatter.formatToWidth(text, 20, alignment);
        assertThat(rows, IsIterableContainingInOrder.contains("Yes, I am text"));
    }
}
