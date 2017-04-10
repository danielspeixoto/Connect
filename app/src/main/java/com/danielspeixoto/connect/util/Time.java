package com.danielspeixoto.connect.util;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by danielspeixoto on 2/18/17.
 */

public class Time {

    public static final String DD_MM_YYYY = "dd/mm/yyyy";

    public static long getTodayInMillis() {
        return getMillis(getDate());
    }

    public static long getDayInMillis(long timestamp) {
        return getMillis(new Timestamp(timestamp).toString().substring(0, 10));
    }

    public static String getDate() {
        return new Timestamp(System.currentTimeMillis()).toString().substring(0, 10);
    }


    public static long getMillis(String date) {
        return Date.valueOf(date).getTime();
    }

    public static String getFormat(String date, String format) {
        if (format.equals(DD_MM_YYYY)) {
            return date.substring(8) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        }
        return date;
    }

    public static String convert(String date, String format) {
        if (format.equals(DD_MM_YYYY)) {
            return date.substring(6, 10) + "-" + date.substring(3, 5) + "-" + date.substring(0, 2);
        }
        return date;
    }
}
