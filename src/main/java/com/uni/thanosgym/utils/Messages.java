package com.uni.thanosgym.utils;

import javax.swing.JOptionPane;

public class Messages {
    /**
     * Show a message dialog with the provided message.
     *
     * @param mensaje Message to be shown
     */
    public static void show(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }

    /**
     * Show a message dialog with the provided message and title.
     *
     * @param mensaje Message to be shown
     * @return The answer in string
    */
    public static String input(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }

    /**
     * Show a confirm dialog with the provided message and title.
     *
     * @param mensaje Message to be shown
     * @param titulo Title of the message dialog
     *
     * @return An integer representing the user's choice
     */
    public static boolean confirm(String mensaje, String titulo) {
        int answer = JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION);
        return answer == JOptionPane.OK_OPTION;
    }
}
