package com.vedantu.utils;

public class StringUtils {

    public static final String EMPTY = "";

    public static boolean isEmpty(String s) {

        return null == s || EMPTY.equals(s.trim());
    }

    public static boolean isNotEmpty(String s) {

        return !isEmpty(s);
    }
}