package com.kotabek.utils;

/**
 * Created by kotabek on 6/1/17.
 */
public class DG {
    public static String getString(Object obj) {
        return getString(obj, "");
    }

    public static String getString(Object obj, String def) {
        if (obj == null) {
            return def;

        } else if (obj instanceof String) {
            return (String) obj;

        }
        return obj.toString();
    }

    public static long getLong(Object obj) {
        return getLong(obj, 0);
    }

    public static long getLong(Object obj, long def) {
        if (obj == null) {
            return def;
        } else if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return getLong(Integer.valueOf(getString(obj)), def);
        } catch (Exception ex) {
            return def;
        }
    }

    public static int getInt(Object obj) {
        return getInt(obj, 0);
    }

    public static int getInt(Object obj, int def) {
        if (obj == null) {
            return def;
        } else if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return getInt(Integer.valueOf(getString(obj)), def);
        } catch (Exception ex) {
            return def;
        }
    }
}
