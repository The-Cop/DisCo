package ru.thecop.disco;

import java.util.Arrays;

public final class Util {

    private Util() {
    }

    public static String getPadString(int padLength, char valueSpacingChar) {
        char[] padArray = new char[padLength];
        Arrays.fill(padArray, valueSpacingChar);
        return new String(padArray);
    }
}
