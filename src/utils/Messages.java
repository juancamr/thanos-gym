package utils;

import javax.swing.JOptionPane;

public class Messages {
    public static void show(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje);
    }
    public static String input(String mensaje) {
        return JOptionPane.showInputDialog(mensaje);
    }
    public static int confirm(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION);
    }
}
