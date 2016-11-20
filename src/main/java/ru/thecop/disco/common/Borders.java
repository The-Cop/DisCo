package ru.thecop.disco.common;

public class Borders {

    private final Character vertical;
    private final Character horizontal;
    private final Corners corners;

    public Borders(Character vertical, Character horizontal, Corners corners) {
        this.vertical = vertical;
        this.horizontal = horizontal;
        this.corners = corners;
    }

    public Borders() {
        vertical = '|';
        horizontal = '-';
        corners = new Corners();
    }

    public static Borders copy(Borders other) {
        return other != null ? new Borders(other) : null;
    }

    private Borders(Borders other) {
        this.vertical = other.vertical;
        this.horizontal = other.horizontal;
        this.corners = Corners.copy(other.corners);
    }

    public Character getVertical() {
        return vertical;
    }

    public Character getHorizontal() {
        return horizontal;
    }

    public Corners getCorners() {
        return corners;
    }

    public boolean hasBorders() {
        return vertical != null || horizontal != null;
    }

    public boolean useCorners() {
        return corners != null && horizontal != null;
    }
}
