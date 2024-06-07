package com.uni.thanosgym.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }
}
