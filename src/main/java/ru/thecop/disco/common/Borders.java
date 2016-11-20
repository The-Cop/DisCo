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

    //// TODO: 20.11.2016 refactor to static copy method
    public Borders(Borders other) {
        if (other == null) {
            this.vertical = null;
            this.horizontal = null;
            this.corners = null;
        } else {
            this.vertical = other.vertical;
            this.horizontal = other.horizontal;
            //noinspection IncompleteCopyConstructor
            this.corners = other.getCorners() != null ? new Corners(other.getCorners()) : null;
        }
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
