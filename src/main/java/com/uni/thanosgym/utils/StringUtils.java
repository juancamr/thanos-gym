
package com.uni.thanosgym.utils;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author juancamr
 */
public class StringUtils {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String usernameRegex = "^[a-z]{8,15}$";
    public static String passwordRegex = "^[a-z]{8,}$";


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

    public static String parseDate(Date date) {
        return sdf.format(date);
    }

    public static boolean isDecimal(String number) {
        return number.matches("^-?\\d+(\\.\\d+)?$");
    } 

    public static boolean isInteger(String num) {
        return num.matches("[0-9]+");
    }

}
