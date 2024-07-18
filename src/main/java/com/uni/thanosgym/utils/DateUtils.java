package com.uni.thanosgym.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static String[] meses = new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" };

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, days);
        return cal.getTime();
    }

    public static String[] obtenerUltimosCuatroMesesEnTexto() {
        return new String[] { "Enero", "Febrero", "Marzo", "Abril" };
    }
}
