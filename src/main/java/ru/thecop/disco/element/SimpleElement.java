package ru.thecop.disco.element;

import ru.thecop.disco.Alignment;
import ru.thecop.disco.DisplayBlock;
import ru.thecop.disco.LineFormatter;

import java.util.List;

public class SimpleElement implements DisplayBlock {

    // TODO: 12.11.2016 implement maximum width?
    private String text;
    private Alignment alignment = Alignment.LEFT;

    public SimpleElement(String text) {
        this.text = text;
    }

    public SimpleElement(String text, Alignment alignment) {
        this.text = text;
        this.alignment = alignment;
    }

    @Override
    public List<String> buildLines(int width) {
        return LineFormatter.formatToWidth(text, width, alignment);
    }
}
