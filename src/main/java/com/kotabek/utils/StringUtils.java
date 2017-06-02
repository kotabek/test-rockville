package com.kotabek.utils;

/**
 * Created by kotabek on 6/1/17.
 */
public class StringUtils {
    public static boolean isEmpty(String val) {
        return val == null
               || val.isEmpty()
               || val.trim().isEmpty();
    }

    public static boolean isNotEmpty(String val) {
        return !isEmpty(val);
    }
}
