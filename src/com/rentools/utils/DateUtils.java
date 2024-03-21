package com.rentools.utils;

public class DateUtils {

    public static boolean isIndependenceDay(Calendar calendar) {
        return calendar.get(Calendar.MONTH) == Calendar.JULY &&
                calendar.get(Calendar.DAY_OF_MONTH) == 4;
    }

    public static boolean isLaborDay(Calendar calendar) {
        return calendar.get(Calendar.MONTH) == Calendar.SEPTEMBER &&
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY &&
                calendar.get(Calendar.DAY_OF_MONTH) <= 7; // First Monday in September
    }
}