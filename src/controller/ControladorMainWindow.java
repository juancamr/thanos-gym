package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import view.HomePanel;
import view.MainWindow;
import thanosgym.Main;
import utils.FrameUtils;

public class ControladorMainWindow implements ActionListener {
    public static MainWindow vista;
    
    static Color base = new Color(20,23,31);
    static Color focus = new Color(55, 58, 64);
    static Color foregroundColorFocus = new Color(255, 255, 254);
    static Color foregroundColorBase = new Color(170,170,170);
    static Font fontFocus = new Font("Malgun Gothic", 1, 16);
    static Font fontBase = new Font("Malgun Gothic", 4, 16);
    
    public static boolean isCorrectPassword;
    public static int panelReporte; //0 panelReporteDia 1 panelConsultarReporte

    public ControladorMainWindow(MainWindow v){ 
        vista = v;
        vista.jbtnSegundo.addActionListener(this);
        vista.jbtnQuinto.addActionListener(this);
        vista.jbtnCuarto.addActionListener(this);
        vista.jbtnTercero.addActionListener(this);
        vista.jbtnPrimero.addActionListener(this);
        vista.jlblNombreAdministrador.setText(Main.admin.getFullName());
        new ControladorHome(vista, new HomePanel());
    }
    
    public void screen() {
        vista.setSize(1060, 790);
        vista.setTitle("Thanos Gym");
        vista.setLocationRelativeTo(vista);
        vista.setVisible(true);
    }
    
    public static void quitarFondosBotones() {
        vista.jbtnPrimero.setBackground(base);
        vista.jbtnPrimero.setForeground(foregroundColorBase);
        vista.jbtnPrimero.setFont(fontBase);
        vista.jlblHomeIcon.setBackground(base);
        vista.jlblHomeIcon.setForeground(foregroundColorBase);
        
        vista.jbtnSegundo.setBackground(base);
        vista.jbtnSegundo.setForeground(foregroundColorBase);
        vista.jbtnSegundo.setFont(fontBase);
        vista.jlblVentaIcon.setBackground(base);
        vista.jlblVentaIcon.setForeground(foregroundColorBase);
        
        vista.jbtnTercero.setBackground(base);
        vista.jbtnTercero.setForeground(foregroundColorBase);
        vista.jbtnTercero.setFont(fontBase);
        vista.jlblRepuestosIcon.setBackground(base);
        vista.jlblRepuestosIcon.setForeground(foregroundColorBase);
        
        vista.jbtnCuarto.setBackground(base);
        vista.jbtnCuarto.setForeground(foregroundColorBase);
        vista.jbtnCuarto.setFont(fontBase);
        vista.jlblReporteIcon.setBackground(base);
        vista.jlblReporteIcon.setForeground(foregroundColorBase);
        
        vista.jbtnQuinto.setBackground(base);
        vista.jbtnQuinto.setForeground(foregroundColorBase);
        vista.jbtnQuinto.setFont(fontBase);
        vista.jlblClientesIcon.setBackground(base);
        vista.jlblClientesIcon.setForeground(foregroundColorBase);
    }
    
    public static void setFocusButton(JButton boton, JLabel label) {
        quitarFondosBotones();
        boton.setBackground(focus);
        boton.setForeground(foregroundColorFocus);
        boton.setFont(fontFocus);
        label.setBackground(focus);
        label.setForeground(foregroundColorFocus);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.jbtnPrimero) {
            System.out.println("first section");
        }
        if (e.getSource() == vista.jbtnSegundo) {
            System.out.println("second section");
        }
        if (e.getSource() == vista.jbtnQuinto) {
            System.out.println("third section");
        }
        if (e.getSource() == vista.jbtnCuarto) {
            System.out.println("fourth section");
        }      
        if (e.getSource() == vista.jbtnTercero) {
            System.out.println("fifth section");
        }        
    }
}