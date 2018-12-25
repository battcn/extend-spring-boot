package com.battcn.boot.request.utils;

/**
 * @author Levin
 * @since 2018/12/20 0020
 */
public class StringUtils {

    /**
     * The empty String {@code ""}.
     *
     * @since 2.0
     */
    public static final String EMPTY = "";

    public static final String DEFAULT_CHARSET = "UTF-8";

    public static String defaultString(final String str, final String defaultStr) {
        return str == null ? defaultStr : str;
    }


    public static <T> String join(final T... elements) {
        return join(elements, null);
    }


    public static String join(final Object[] array, final char separator, final int startIndex, final int endIndex) {
        if (array == null) {
            return null;
        }
        final int noOfItems = endIndex - startIndex;
        if (noOfItems <= 0) {
            return EMPTY;
        }
        final StringBuilder buf = new StringBuilder(noOfItems * 16);
        for (int i = startIndex; i < endIndex; i++) {
            if (i > startIndex) {
                buf.append(separator);
            }
            if (array[i] != null) {
                buf.append(array[i]);
            }
        }
        return buf.toString();
    }

}
