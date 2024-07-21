
package com.uni.thanosgym.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author juancamr
 */
public class StringUtils {

    private static SimpleDateFormat sqlFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat spanishFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static String usernameRegex = "^[a-z]{8,15}$";
    private static String passwordRegex = "^[a-z]{8,}$";
    private static DecimalFormat boletaFormat = new DecimalFormat("00000000000");

    public static String sha256(final String base) {
        try {
            final MessageDigest digest = MessageDigest.getInstance("SHA-256");
            final byte[] hash = digest.digest(base.getBytes("UTF-8"));
            final StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < hash.length; i++) {
                final String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String parseIdBoleta(int id) {
        return boletaFormat.format(id);
    }

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPhone(String phone) {
        return phone.matches("[0-9]{9}");
    }

    public static boolean isValidDni(String dni) {
        return dni.matches("\\d{8}");
    }

    public static boolean isValidUsername(String email) {
        return email.matches(usernameRegex);
    }

    public static boolean isValidPassword(String password) {
        return password.matches(passwordRegex);
    }

    public static String parseSpanishDate(Date date) {
        return spanishFormat.format(date);
    }

    public static String parseToDayAndMonth(Date date) {
        return new SimpleDateFormat("dd/MM").format(date);
    }

    public static Date spanishDateToDate(String date) {
        try {
            return spanishFormat.parse(date);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String parseDate(Date date) {
        return sqlFormat.format(date);
    }

    public static boolean isDecimal(String number) {
        return number.matches("^-?\\d+(\\.\\d+)?$");
    }

    public static boolean isInteger(String num) {
        return num.matches("[0-9]+");
    }

    public static boolean isValidRuc(String ruc) {
        return ruc.matches("\\d{11}");
    }

}
