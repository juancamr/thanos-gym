package com.uni.thanosgym.controller;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.view.MainWindow;

public class ControladorMainWindow {
    public static MainWindow vista;
    public static boolean vistaRendered = false;

    static Color base = new Color(250, 250, 250);
    static Color focus = new Color(245, 245, 245);
    static Color foregroundColorBase = new Color(20, 20, 20);
    static Color foregroundColorFocus = new Color(0, 0, 0);
    static Font fontFocus = new Font("Malgun Gothic", 1, 16);
    static Font fontBase = new Font("Malgun Gothic", 4, 16);

    public static void initMainWindow() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        setFocusButton(vista.jbtnPrimero);
        vista.jbtnPrimero.setText("    Dashboard");
        vista.jbtnSegundo.setText("    Planes");
        vista.jbtnTercero.setText("    Clientes");
        vista.jbtnCuarto.setText("    Cuarto");
        vista.jbtnQuinto.setText("    Producto");
        vista.jbtnSexto.setText("    Utilidad");

        if (!vistaRendered) {
            FrameUtils.addOnClickEvent(vista.jbtnPrimero, () -> {
                ControladorDashboard.showPanel();
                setFocusButton(vista.jbtnPrimero);
            });
            FrameUtils.addOnClickEvent(vista.jbtnSegundo, () -> {
                ControladorPlan.showPanel();
                setFocusButton(vista.jbtnSegundo);
            });
            FrameUtils.addOnClickEvent(vista.jbtnTercero, () -> {
                ControladorClientBuscar.showPanel();
                setFocusButton(vista.jbtnTercero);
            });
            FrameUtils.addOnClickEvent(vista.jbtnCuarto, () -> {
                System.out.println("Pronto mas :))");
                // ControladorClientBuscar.showPanel();
                // setFocusButton(vista.jbtnCuarto);
            });
            FrameUtils.addOnClickEvent(vista.jbtnQuinto, () -> {
                ControladorProducto.showPanel();
                setFocusButton(vista.jbtnQuinto);
            });
            FrameUtils.addOnClickEvent(vista.jbtnSexto, () -> {
                ControladorUtilidad.showPanel();
                setFocusButton(vista.jbtnSexto);
            });
            vistaRendered = true;
        }

        vista.jlblNombreAdministrador.setText(UserPreferences.getData().getFullName());
        ControladorDashboard.showPanel();

        // mostrar ventana
        vista.setSize(1060, 690);
        vista.setResizable(false);
        vista.setTitle("Thanos Gym");
        vista.setLocationRelativeTo(vista);
        vista.setVisible(true);
    }

    public static MainWindow getMainWindow() {
        if (vista == null)
            vista = new MainWindow();
        return vista;
    }

    public static void quitarFondosBotones(JButton[] botones) {
        for (JButton boton : botones) {
            boton.setBackground(base);
            boton.setForeground(foregroundColorBase);
            boton.setFont(fontBase);
        }
    }

    public static void setFocusButton(JButton boton) {
        quitarFondosBotones(new JButton[] { vista.jbtnPrimero, vista.jbtnSegundo, vista.jbtnTercero, vista.jbtnCuarto,
                vista.jbtnQuinto, vista.jbtnSexto });
        boton.setBackground(focus);
        boton.setForeground(foregroundColorFocus);
        boton.setFont(fontFocus);
    }
}
