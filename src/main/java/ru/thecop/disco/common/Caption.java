package ru.thecop.disco.common;

import ru.thecop.disco.Alignment;

public class Caption {

    private final String text;
    private final CaptionType type;
    private final Alignment alignment;

    public Caption(String text) {
        this.text = text;
        alignment = Alignment.CENTER;
        type = CaptionType.LINE;
    }

    public Caption(String text, Alignment alignment) {
        this.text = text;
        this.alignment = alignment;
        type = CaptionType.LINE;
    }

    public Caption(String text, CaptionType type) {
        this.text = text;
        this.type = type;
        alignment = Alignment.CENTER;
    }

    public Caption(String text, CaptionType type, Alignment alignment) {
        this.text = text;
        this.type = type;
        this.alignment = alignment;
    }

    private Caption(Caption other) {
        this.text = other.text;
        this.type = other.type;
        this.alignment = other.alignment;
    }

    public static Caption copy(Caption other) {
        return other != null ? new Caption(other) : null;
    }

    public String getText() {
        return text;
    }


    public CaptionType getType() {
        return type;
    }


    public Alignment getAlignment() {
        return alignment;
    }

}
