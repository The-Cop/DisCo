package ru.thecop.disco;

import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Test;
import ru.thecop.disco.common.Borders;
import ru.thecop.disco.common.Corners;
import ru.thecop.disco.common.Padding;
import ru.thecop.disco.common.Separations;
import ru.thecop.disco.element.SimpleElement;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BlockFormatterTest {

    @Test
    public void lineFilledListTest() {
        List<String> s = BlockFormatter.lineFilledList(3, " ");
        assertThat(s, IsIterableContainingInOrder.contains(" ", " ", " "));
        s = BlockFormatter.lineFilledList(3, "| some formatted thing     |");
        assertThat(s, IsIterableContainingInOrder.contains("| some formatted thing     |",
                "| some formatted thing     |",
                "| some formatted thing     |"));

        s = BlockFormatter.lineFilledList(0, "| some formatted thing     |");
        assertEquals(0, s.size());

        s = BlockFormatter.lineFilledList(0, null);
        assertEquals(0, s.size());
    }

    @Test
    public void charFilledStringTest() {
        String s = BlockFormatter.charFilledString(10, 'b');
        assertEquals("bbbbbbbbbb", s);
        s = BlockFormatter.charFilledString(-1, 'b');
        assertEquals("", s);
        s = BlockFormatter.charFilledString(0, 'b');
        assertEquals("", s);
    }

    @Test
    public void whitespaceLinesTest() {
        List<String> s = BlockFormatter.whitespaceLines(3);
        assertThat(s, IsIterableContainingInOrder.contains(" ", " ", " "));
        s = BlockFormatter.whitespaceLines(0);
        assertEquals(0, s.size());
        s = BlockFormatter.whitespaceLines(-1);
        assertEquals(0, s.size());
    }

    @Test
    public void padAndBorderTopAndBottomTest() {
        List<String> s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, new Padding(3, 2, 5, 5), "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, "PADLINE",
                new Borders(null, '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3", "PADLINE", "PADLINE",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, 0, 2, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "Line1", "Line2", "Line3", "PADLINE", "PADLINE",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, 0, 2, "",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "Line1", "Line2", "Line3", "", "",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, 0, 0, "",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "Line1", "Line2", "Line3",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, -6, -5, "",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "Line1", "Line2", "Line3",
                "C--------D"
        ));

        s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, null,
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "", "", "",
                "Line1", "Line2", "Line3",
                "", "",
                "C--------D"
        ));
        //null padding
        s = BlockFormatter.padAndBorderTopAndBottom(10, null, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A--------B",
                "Line1", "Line2", "Line3",
                "C--------D"
        ));
        //null borders
        s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, "PADLINE",
                null,
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE"
        ));
        //null borders and padding
        s = BlockFormatter.padAndBorderTopAndBottom(10, null, "PADLINE",
                null,
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "Line1", "Line2", "Line3"
        ));
        s = BlockFormatter.padAndBorderTopAndBottom(10, 0, 0, "PADLINE",
                null,
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "Line1", "Line2", "Line3"
        ));

        //null horiz border
        s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, "PADLINE",
                new Borders('|', null, new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE"
        ));
        s = BlockFormatter.padAndBorderTopAndBottom(10, 0, 0, "PADLINE",
                new Borders('|', null, new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "Line1", "Line2", "Line3"
        ));
        //null corners
        s = BlockFormatter.padAndBorderTopAndBottom(10, 3, 2, "PADLINE",
                new Borders('|', '-', null),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "----------",
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE",
                "----------"
        ));
        //null corners small width
        s = BlockFormatter.padAndBorderTopAndBottom(1, 3, 2, "PADLINE",
                new Borders('|', '-', null),
                Arrays.asList("Line1", "Line2", "Line3"));
        assertThat(s, IsIterableContainingInOrder.contains(
                "-",
                "PADLINE", "PADLINE", "PADLINE",
                "Line1", "Line2", "Line3",
                "PADLINE", "PADLINE",
                "-"
        ));
    }

    @Test(expected = IllegalArgumentException.class)
    public void padAndBorderTopAndBottomSmallBaseWidthTest() {
        BlockFormatter.padAndBorderTopAndBottom(1, 3, 2, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void padAndBorderTopAndBottomZeroBaseWidthTest() {
        BlockFormatter.padAndBorderTopAndBottom(0, 3, 2, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void padAndBorderTopAndBottomNegativeBaseWidthTest() {
        BlockFormatter.padAndBorderTopAndBottom(-10, 3, 2, "PADLINE",
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')),
                Arrays.asList("Line1", "Line2", "Line3"));
    }

    @Test
    public void horizontalBorderTest() {
        String s = BlockFormatter.horizontalBorder(10, new Borders('|', '-', new Corners('A', 'B', 'C', 'D')), true);
        assertEquals("A--------B", s);

        s = BlockFormatter.horizontalBorder(10, new Borders('|', '-', new Corners('A', 'B', 'C', 'D')), false);
        assertEquals("C--------D", s);

        s = BlockFormatter.horizontalBorder(10, new Borders('|', '-', null), false);
        assertEquals("----------", s);

        s = BlockFormatter.horizontalBorder(10, new Borders('|', null, new Corners('A', 'B', 'C', 'D')), false);
        assertNull(s);

        s = BlockFormatter.horizontalBorder(10, null, false);
        assertNull(s);
    }

    @Test
    public void createSeparationTest() {
        List<String> s = BlockFormatter.createSeparation(10, 2, 'z');
        assertThat(s, IsIterableContainingInOrder.contains("zzzzzzzzzz", "zzzzzzzzzz"));

        s = BlockFormatter.createSeparation(10, -4, 'z');
        assertEquals(0, s.size());

        s = BlockFormatter.createSeparation(-1, 2, 'z');
        assertThat(s, IsIterableContainingInOrder.contains("", ""));

        s = BlockFormatter.createSeparation(-11, -4, 'z');
        assertEquals(0, s.size());

        s = BlockFormatter.createSeparation(-11, null);
        assertEquals(0, s.size());

        s = BlockFormatter.createSeparation(10, new Separations(2, 'd'));
        assertThat(s, IsIterableContainingInOrder.contains("dddddddddd", "dddddddddd"));
    }

    @Test
    public void createBorderedSeparationTest() {
        List<String> s = BlockFormatter.createBorderedSeparation(10, 2, 'z', '|');
        assertThat(s, IsIterableContainingInOrder.contains("|zzzzzzzz|", "|zzzzzzzz|"));

        s = BlockFormatter.createBorderedSeparation(2, 2, 'z', '|');
        assertThat(s, IsIterableContainingInOrder.contains("||", "||"));

        s = BlockFormatter.createBorderedSeparation(10, -4, 'z', '|');
        assertEquals(0, s.size());

        s = BlockFormatter.createBorderedSeparation(10, new Separations(2, 'd'), '\'');
        assertThat(s, IsIterableContainingInOrder.contains("'dddddddd'", "'dddddddd'"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createBorderedSeparationExceptionTest() {
        BlockFormatter.createBorderedSeparation(1, 2, 'z', '|');
    }

    @Test
    public void getPaddingFormatTest() {
        String s = BlockFormatter.getPaddingFormat(13, 3, 4);
        assertEquals("   %-6s    ", s);

        s = BlockFormatter.getPaddingFormat(10, 2, 1);
        assertEquals("  %-7s ", s);

        s = BlockFormatter.getPaddingFormat(10, -4, -4);
        assertEquals("%-10s", s);

        s = BlockFormatter.getPaddingFormat(10, new Padding(1, 2, 3, 4));
        assertEquals("   %-3s    ", s);

        s = BlockFormatter.getPaddingFormat(10, null);
        assertEquals("%-10s", s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaddingFormatNegativeWidthTest() {
        BlockFormatter.getPaddingFormat(-10, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaddingFormatTooSmallWidthTest() {
        BlockFormatter.getPaddingFormat(10, 5, 5);
    }

    @Test
    public void getPaddingFormatWithBordersTest() {
        String s = BlockFormatter.getPaddingFormatWithBorders(13, 3, 4, '|');
        assertEquals("|   %-4s    |", s);

        s = BlockFormatter.getPaddingFormatWithBorders(10, 2, 1, '|');
        assertEquals("|  %-5s |", s);

        s = BlockFormatter.getPaddingFormatWithBorders(10, -4, -4, '|');
        assertEquals("|%-8s|", s);

        s = BlockFormatter.getPaddingFormatWithBorders(10, null, '|');
        assertEquals("|%-8s|", s);

        s = BlockFormatter.getPaddingFormatWithBorders(10, new Padding(1, 2, 3, 4), '|');
        assertEquals("|   %-1s    |", s);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaddingFormatWithBordersNegativeWidthTest() {
        BlockFormatter.getPaddingFormatWithBorders(-10, 2, 3, '|');
    }

    @Test(expected = IllegalArgumentException.class)
    public void getPaddingFormatWithBordersTooSmallWidthTest() {
        BlockFormatter.getPaddingFormatWithBorders(10, 4, 4, '|');
    }

    // TODO: 25.09.2016 check expected/actual order
    @Test
    public void getWidthForChildrenTest() {
        int s = BlockFormatter.getWidthForChildren(10, 3, 4, false);
        assertEquals(s, 3);

        s = BlockFormatter.getWidthForChildren(10, 3, 4, true);
        assertEquals(s, 1);

        s = BlockFormatter.getWidthForChildren(10, new Padding(1, 2, 3, 4), false);
        assertEquals(s, 3);

        s = BlockFormatter.getWidthForChildren(10, new Padding(1, 2, 3, 4), true);
        assertEquals(s, 1);

        s = BlockFormatter.getWidthForChildren(10, null, false);
        assertEquals(s, 10);

        s = BlockFormatter.getWidthForChildren(10, null, true);
        assertEquals(s, 8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWidthForChildrenTestNegativeWidthTest() {
        BlockFormatter.getWidthForChildren(-10, 3, 4, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWidthForChildrenSmallWidthTest() {
        BlockFormatter.getWidthForChildren(0, 0, 0, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWidthForChildrenSmallResultingWidthTest() {
        BlockFormatter.getWidthForChildren(7, 3, 4, false);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getWidthForChildrenSmallWidthBorderedTest() {
        BlockFormatter.getWidthForChildren(9, 3, 4, true);
    }

    @Test
    public void formatBlocksTest() {
        List<DisplayBlock> blocks = Arrays.asList(
                new SimpleElement("First SimpleElement block here"),
                new SimpleElement("Second SimpleElement block here"),
                new SimpleElement("Third SimpleElement block here")
        );
        //everything, fit to "SimpleElement" word
        List<String> s = BlockFormatter.formatBlocks(blocks, 23,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A---------------------B",
                "|                     |",
                "|   First             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|zzzzzzzzzzzzzzzzzzzzz|",
                "|   Second            |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|zzzzzzzzzzzzzzzzzzzzz|",
                "|   Third             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|                     |",
                "|                     |",
                "C---------------------D"
        ));

        //everything, wider
        s = BlockFormatter.formatBlocks(blocks, 30,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A----------------------------B",
                "|                            |",
                "|   First SimpleElement      |",
                "|   block here               |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|   Second SimpleElement     |",
                "|   block here               |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|   Third SimpleElement      |",
                "|   block here               |",
                "|                            |",
                "|                            |",
                "C----------------------------D"
        ));

        //everything, narrow
        s = BlockFormatter.formatBlocks(blocks, 15,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A-------------B",
                "|             |",
                "|   First     |",
                "|   Simpl     |",
                "|   eElem     |",
                "|   ent       |",
                "|   block     |",
                "|   here      |",
                "|zzzzzzzzzzzzz|",
                "|   Secon     |",
                "|   d         |",
                "|   Simpl     |",
                "|   eElem     |",
                "|   ent       |",
                "|   block     |",
                "|   here      |",
                "|zzzzzzzzzzzzz|",
                "|   Third     |",
                "|   Simpl     |",
                "|   eElem     |",
                "|   ent       |",
                "|   block     |",
                "|   here      |",
                "|             |",
                "|             |",
                "C-------------D"
        ));

        //no separations
        s = BlockFormatter.formatBlocks(blocks, 23,
                new Separations(0, 'f'),
                new Padding(1, 2, 3, 5),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A---------------------B",
                "|                     |",
                "|   First             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|   Second            |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|   Third             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|                     |",
                "|                     |",
                "C---------------------D"
        ));

        s = BlockFormatter.formatBlocks(blocks, 23,
                null,
                new Padding(1, 2, 3, 5),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A---------------------B",
                "|                     |",
                "|   First             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|   Second            |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|   Third             |",
                "|   SimpleElement     |",
                "|   block here        |",
                "|                     |",
                "|                     |",
                "C---------------------D"
        ));

        //no padding
        s = BlockFormatter.formatBlocks(blocks, 30,
                new Separations(1, 'z'),
                new Padding(0, 0, 0, 0),
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A----------------------------B",
                "|First SimpleElement block   |",
                "|here                        |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|Second SimpleElement block  |",
                "|here                        |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|Third SimpleElement block   |",
                "|here                        |",
                "C----------------------------D"
        ));
        s = BlockFormatter.formatBlocks(blocks, 30,
                new Separations(1, 'z'),
                null,
                new Borders('|', '-', new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "A----------------------------B",
                "|First SimpleElement block   |",
                "|here                        |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|Second SimpleElement block  |",
                "|here                        |",
                "|zzzzzzzzzzzzzzzzzzzzzzzzzzzz|",
                "|Third SimpleElement block   |",
                "|here                        |",
                "C----------------------------D"
        ));
        //no borders
        s = BlockFormatter.formatBlocks(blocks, 23,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                null);
        assertThat(s, IsIterableContainingInOrder.contains(
                "                       ",
                "   First               ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Second              ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Third               ",
                "   SimpleElement       ",
                "   block here          ",
                "                       ",
                "                       "
        ));
        s = BlockFormatter.formatBlocks(blocks, 23,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                new Borders(null, null, null));
        assertThat(s, IsIterableContainingInOrder.contains(
                "                       ",
                "   First               ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Second              ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Third               ",
                "   SimpleElement       ",
                "   block here          ",
                "                       ",
                "                       "
        ));
        s = BlockFormatter.formatBlocks(blocks, 23,
                new Separations(1, 'z'),
                new Padding(1, 2, 3, 5),
                new Borders(null, null, new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "                       ",
                "   First               ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Second              ",
                "   SimpleElement       ",
                "   block here          ",
                "zzzzzzzzzzzzzzzzzzzzzzz",
                "   Third               ",
                "   SimpleElement       ",
                "   block here          ",
                "                       ",
                "                       "
        ));
        // TODO: 25.09.2016 no vertical;no horizontal
        //no anything
        s = BlockFormatter.formatBlocks(blocks, 15,
                new Separations(0, 'z'),
                new Padding(0, 0, 0, 0),
                new Borders(null, null, new Corners('A', 'B', 'C', 'D')));
        assertThat(s, IsIterableContainingInOrder.contains(
                "First          ",
                "SimpleElement  ",
                "block here     ",
                "Second         ",
                "SimpleElement  ",
                "block here     ",
                "Third          ",
                "SimpleElement  ",
                "block here     "
        ));
        s = BlockFormatter.formatBlocks(blocks, 15,
                null,
                null,
                null);
        assertThat(s, IsIterableContainingInOrder.contains(
                "First          ",
                "SimpleElement  ",
                "block here     ",
                "Second         ",
                "SimpleElement  ",
                "block here     ",
                "Third          ",
                "SimpleElement  ",
                "block here     "
        ));
    }
}
