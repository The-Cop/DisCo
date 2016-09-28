package ru.thecop.disco.common;

public class Padding {

    private final int top;
    private final int bottom;
    private final int left;
    private final int right;

    public Padding(int top, int bottom, int left, int right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Padding() {
        top = 0;
        bottom = 0;
        left = 0;
        right = 0;
    }

    public Padding(Padding other) {
        if (other == null) {
            top = 0;
            bottom = 0;
            left = 0;
            right = 0;
        } else {
            this.top = other.top;
            this.bottom = other.bottom;
            this.left = other.left;
            this.right = other.right;
        }
    }

    public int getTop() {
        return top;
    }

    public int getBottom() {
        return bottom;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
