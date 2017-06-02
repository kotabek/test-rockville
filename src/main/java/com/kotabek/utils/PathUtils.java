package com.kotabek.utils;

/**
 * Created by kotabek on 6/1/17.
 */
public class PathUtils {
    public static String getRootPath() {
        return PathUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    }
}
