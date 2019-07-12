package com.avenuecode.utils;

public class StringUtils {

    public static String replaceBracketsAndCommas(String line) {
        return line
                .replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "");
    }
}
