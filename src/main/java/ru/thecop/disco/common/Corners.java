package ru.thecop.disco.common;

public class Corners {

    private final char topLeft;
    private final char topRight;
    private final char bottomLeft;
    private final char bottomRight;

    public Corners(char topLeft, char topRight, char bottomLeft, char bottomRight) {
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public Corners() {
        topLeft = '-';
        topRight = '-';
        bottomLeft = '-';
        bottomRight = '-';
    }

    public static Corners copy(Corners other) {
        return other != null ? new Corners(other) : null;
    }

    private Corners(Corners other) {
        this.topLeft = other.topLeft;
        this.topRight = other.topRight;
        this.bottomLeft = other.bottomLeft;
        this.bottomRight = other.bottomRight;
    }

    public char getTopLeft() {
        return topLeft;
    }

    public char getTopRight() {
        return topRight;
    }

    public char getBottomLeft() {
        return bottomLeft;
    }

    public char getBottomRight() {
        return bottomRight;
    }
}
