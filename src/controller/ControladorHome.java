package controller;
import config.Startup;
import controller.plan.ControladorAgregarPlan;
import controller.client.ControladorClient;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Administrador;
import thanosgym.Main;
import utils.FrameUtils;
import view.HomePanel;
import view.MainWindow;
import view.plan.AddPlan;
import view.client.AddCliente;

public class ControladorHome implements ActionListener {
    HomePanel panel;
    MainWindow vista;
    Administrador admin = Main.admin;
    
    public ControladorHome(MainWindow v, HomePanel pan) {
        panel = pan;
        vista = v;
        
        panel.jbtnCerrarSesion.addActionListener(this);
        panel.jlblNombreAdministrador.setText(admin.getFullName());
        panel.jbtnAgregarPlan.addActionListener(this);
        panel.jbtnAgregarClient.addActionListener(this);
        FrameUtils.showPanel(vista, panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnCerrarSesion) {
            vista.dispose();
            Main.admin = null;
            Startup.initWindow();
        }
        
        if (e.getSource() == panel.jbtnAgregarPlan) {
            new ControladorAgregarPlan(new AddPlan());
        } 
        
        if (e.getSource() == panel.jbtnAgregarClient) {
            new ControladorClient(new AddCliente());
        }
    }
    
}
