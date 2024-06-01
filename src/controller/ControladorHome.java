package controller;
import config.Startup;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Administrador;
import thanosgym.Main;
import utils.FrameUtils;
import view.HomePanel;
import view.MainWindow;

public class ControladorHome implements ActionListener {
    HomePanel panel;
    MainWindow vista;
    Administrador admin = Main.admin;
    
    public ControladorHome(MainWindow v, HomePanel pan) {
        panel = pan;
        vista = v;
        
        panel.jbtnCerrarSesion.addActionListener(this);
        panel.jlblNombreAdministrador.setText(admin.getFullName());
        
        FrameUtils.showPanel(vista, panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnCerrarSesion) {
            vista.dispose();
            Main.admin = null;
            Startup.initWindow();
        }
    }
    
}
