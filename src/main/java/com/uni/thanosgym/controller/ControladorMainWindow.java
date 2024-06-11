package com.uni.thanosgym.controller;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.UserPreferences;
import com.uni.thanosgym.view.MainWindow;

public class ControladorMainWindow {
    public static MainWindow vista;

    static Color base = new Color(20, 23, 31);
    static Color focus = new Color(55, 58, 64);
    static Color foregroundColorFocus = new Color(255, 255, 254);
    static Color foregroundColorBase = new Color(170, 170, 170);
    static Font fontFocus = new Font("Malgun Gothic", 1, 16);
    static Font fontBase = new Font("Malgun Gothic", 4, 16);

    public static boolean isCorrectPassword;
    public static int panelReporte; // 0 panelReporteDia 1 panelConsultarReporte

    public static void initMainWindow() {
        MainWindow vista = ControladorMainWindow.getMainWindow();
        // on click events
        FrameUtils.addOnClickEvent(vista.jbtnPrimero, () -> {
            ControladorPlan.showHomePanel();
            setFocusButton(vista.jbtnPrimero, vista.jlblNombreAdministrador);
        });
        FrameUtils.addOnClickEvent(vista.jbtnCliente, () -> {
            ControladorClient.showPanel();
            setFocusButton(vista.jbtnCliente, vista.jlblNombreAdministrador);
        });
        FrameUtils.addOnClickEvent(vista.jbtnTercero, () -> {
            System.out.println("fifth section");
        });
        FrameUtils.addOnClickEvent(vista.jbtnCuarto, () -> {
            System.out.println("fourth section");
        });
        FrameUtils.addOnClickEvent(vista.jbtnQuinto, () -> {
            System.out.println("third section");
        });

        vista.jlblNombreAdministrador.setText(UserPreferences.getData().getFullName());
        ControladorPlan.showHomePanel();

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

        vista.jbtnCliente.setBackground(base);
        vista.jbtnCliente.setForeground(foregroundColorBase);
        vista.jbtnCliente.setFont(fontBase);

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

    public static void setFocusButton(JButton boton, JLabel label) {
        quitarFondosBotones();
        boton.setBackground(focus);
        boton.setForeground(foregroundColorFocus);
        boton.setFont(fontFocus);
        label.setBackground(focus);
        label.setForeground(foregroundColorFocus);
    }
}
