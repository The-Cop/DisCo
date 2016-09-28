package ru.thecop.disco.common;

public class Separations {

    private final int separationLinesCount;
    private final char separationChar;

    public Separations(int separationLinesCount, char separationChar) {
        this.separationLinesCount = separationLinesCount;
        this.separationChar = separationChar;
    }

    public Separations() {
        separationLinesCount = 0;
        separationChar = ' ';
    }

    public Separations(Separations other) {
        if (other != null) {
            this.separationLinesCount = other.separationLinesCount;
            this.separationChar = other.separationChar;
        } else {
            separationLinesCount = 0;
            separationChar = ' ';
        }
    }

    public int getSeparationLinesCount() {
        return separationLinesCount;
    }

    public char getSeparationChar() {
        return separationChar;
    }
}
