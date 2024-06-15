package com.uni.thanosgym.controller;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.view.MainWindow;

public class ControladorMainWindow {
    public static MainWindow vista;

    static Color base = new Color(250, 250, 250);
    static Color focus = new Color(245, 245, 245);
    static Color foregroundColorBase = new Color(20, 20, 20);
    static Color foregroundColorFocus = new Color(0, 0, 0);
    static Font fontFocus = new Font("Malgun Gothic", 1, 16);
    static Font fontBase = new Font("Malgun Gothic", 4, 16);

    public static boolean isCorrectPassword;
    public static int panelReporte; // 0 panelReporteDia 1 panelConsultarReporte

    public static void initMainWindow() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        vista.jbtnPrimero.setText("    Dashboard");
        vista.jbtnSegundo.setText("    Planes");
        vista.jbtnTercero.setText("    Clientes");
        vista.jbtnCuarto.setText("    Buscar");
        vista.jbtnQuinto.setVisible(false);
       
        // on click events
        FrameUtils.addOnClickEvent(vista.jbtnPrimero, () -> {
            ControladorDashboard.showPanel();
            setFocusButton(vista.jbtnPrimero);
        });
        FrameUtils.addOnClickEvent(vista.jbtnSegundo, () -> {
            ControladorPlan.showHomePanel();
            setFocusButton(vista.jbtnSegundo);
        });
        FrameUtils.addOnClickEvent(vista.jbtnTercero, () -> {
            ControladorClient.showPanel();
            setFocusButton(vista.jbtnTercero);
        });
        FrameUtils.addOnClickEvent(vista.jbtnCuarto, () -> {
            ControladorClientBuscar.showPanel();
            setFocusButton(vista.jbtnCuarto);
        });
        FrameUtils.addOnClickEvent(vista.jbtnQuinto, () -> {
            System.out.println("fifth section");
        });

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

    public static void quitarFondosBotones() {
        vista.jbtnPrimero.setBackground(base);
        vista.jbtnPrimero.setForeground(foregroundColorBase);
        vista.jbtnPrimero.setFont(fontBase);

        vista.jbtnSegundo.setBackground(base);
        vista.jbtnSegundo.setForeground(foregroundColorBase);
        vista.jbtnSegundo.setFont(fontBase);

        vista.jbtnTercero.setBackground(base);
        vista.jbtnTercero.setForeground(foregroundColorBase);
        vista.jbtnTercero.setFont(fontBase);

        vista.jbtnCuarto.setBackground(base);
        vista.jbtnCuarto.setForeground(foregroundColorBase);
        vista.jbtnCuarto.setFont(fontBase);

        vista.jbtnQuinto.setBackground(base);
        vista.jbtnQuinto.setForeground(foregroundColorBase);
        vista.jbtnQuinto.setFont(fontBase);
    }

    public static void setFocusButton(JButton boton) {
        quitarFondosBotones();
        boton.setBackground(focus);
        boton.setForeground(foregroundColorFocus);
        boton.setFont(fontFocus);
    }
}
