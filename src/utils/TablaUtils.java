package utils;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

public class TablaUtils {
    static Color white = new Color(255, 255, 254);
    static Color black = new Color(35,38,46);
    static Color grey = new Color(105,105,118);
    
    public static void establecerFormatoTabla (JTable tabla, int ncol, int width, boolean flag) {
        //metodo para dar formato a una tabla
        tabla.getColumnModel().getColumn(ncol).setPreferredWidth(width);
        //dar color a las tablas
        tabla.setOpaque(true);
        tabla.setFillsViewportHeight(true);
        tabla.setBackground(white);
        tabla.setForeground(grey);
        tabla.getTableHeader().setBackground(black);
        tabla.getTableHeader().setForeground(white);
        tabla.getTableHeader().setFont(new Font("Malgun Gothic", 4, 14));
        if (flag) {
            //centrar
            DefaultTableCellRenderer modelo = new DefaultTableCellRenderer();
            modelo.setHorizontalAlignment(SwingConstants.CENTER);
            tabla.getColumnModel().getColumn(ncol).setCellRenderer(modelo);
        }
    }
    public static void formatoTablaBoleta(JTable tabla) {
        //metodo que usa el metodo anterior para dar formato a la tabla boleta
        establecerFormatoTabla(tabla, 0, 200, true); //cantidad
        establecerFormatoTabla(tabla, 1, 500, false); //descripcion
        establecerFormatoTabla(tabla, 2, 200, true); //precio
        establecerFormatoTabla(tabla, 3, 200, true); //total
    }
    public static void formatoTablaReportesDia(JTable tabla) {
        //metodo que usa el metodo anterior para dar formato a la tabla reportesDia
        establecerFormatoTabla(tabla, 0, 250, true);
        establecerFormatoTabla(tabla, 1, 500, false);
        establecerFormatoTabla(tabla, 2, 250, true);
    }
    public static void formatoTablaConsultarReporte(JTable tabla) {
        establecerFormatoTabla(tabla, 0, 200, true); //idReporte
        establecerFormatoTabla(tabla, 1, 200, true); //fecha
        establecerFormatoTabla(tabla, 2, 200, true); //montoTotal
    }
    public static void formatoTablaConsultarBoleta(JTable tabla) {
        establecerFormatoTabla(tabla, 0, 200, true); //fecha
        establecerFormatoTabla(tabla, 1, 200, true); //idBoleta
        establecerFormatoTabla(tabla, 2, 200, true); //monto
    }
    public static void formatoTablaProductosSinStock(JTable tabla) {
        establecerFormatoTabla(tabla, 0, 200, false); //codigo
        establecerFormatoTabla(tabla, 1, 500, false); //descripcion
        establecerFormatoTabla(tabla, 2, 100, true); //stock
    }
    public static void formatoTablaTodosLosProductos(JTable tabla) {
        TableColumn ac = new TableColumn();
        //metodo que usa el metodo anterior para dar formato a la tabla boleta
        establecerFormatoTabla(tabla, 0, 200, true); //codigo
        establecerFormatoTabla(tabla, 1, 500, false); //nombre
        establecerFormatoTabla(tabla, 2, 200, true);//precio costo
        establecerFormatoTabla(tabla, 3, 200, true); //precio venta
        establecerFormatoTabla(tabla, 4, 500, false); //proveedor
        establecerFormatoTabla(tabla, 5, 200, true); //stock
    }
}
