package com.uni.thanosgym.utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class TablaUtils {
    static Color white = new Color(255, 255, 254);
    static Color black = new Color(35, 38, 46);
    static Color grey = new Color(105, 105, 118);

    public static void establecerFormatoTabla(JTable tabla, int ncol, int width, boolean flag) {
        // metodo para dar formato a una tabla
        tabla.getColumnModel().getColumn(ncol).setPreferredWidth(width);
        // dar color a las tablas
        tabla.setOpaque(true);
        tabla.setFillsViewportHeight(true);
        tabla.setBackground(white);
        tabla.setForeground(grey);
        tabla.getTableHeader().setBackground(black);
        tabla.getTableHeader().setForeground(white);
        tabla.getTableHeader().setFont(new Font("Malgun Gothic", 4, 14));
        if (flag) {
            // centrar
            DefaultTableCellRenderer modelo = new DefaultTableCellRenderer();
            modelo.setHorizontalAlignment(SwingConstants.CENTER);
            tabla.getColumnModel().getColumn(ncol).setCellRenderer(modelo);
        }
    }

    public static void formatoTablaProductoOUtilildad(JTable tabla) {
        establecerFormatoTabla(tabla, 0, 100, true); // id
        establecerFormatoTabla(tabla, 1, 300, false); // nombre
        establecerFormatoTabla(tabla, 2, 100, true); // cantidad
        establecerFormatoTabla(tabla, 3, 100, true); // precio
    }

}
