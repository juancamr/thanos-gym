package controller.auth;

import DAO.CRUDAdministrador;
import controller.ControladorMainWindow;
import view.auth.PanelLogin;
import view.auth.PanelRegister;
import view.auth.WindowSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.FrameUtils;
import utils.Messages;
import utils.StringUtils;
import view.MainWindow;

public class ControladorLogin implements ActionListener {
    WindowSession view;
    PanelLogin panel;
    
    public ControladorLogin(WindowSession v, PanelLogin pan) {
        view = v;
        panel = pan;
        panel.jbtnRegistro.addActionListener(this);
        panel.jbtnIniciar.addActionListener(this);
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombreUsuario.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnRegistro) {
            new ControladorRegistro(view, new PanelRegister());
        }
        
        if (e.getSource() == panel.jbtnIniciar) {
            String userName = panel.jtxtNombreUsuario.getText();
            String password = String.valueOf(panel.jPassword.getPassword());
            
            if (!userName.isEmpty() || !password.isEmpty()) {
                password = StringUtils.sha256(password);
                boolean adminExist = CRUDAdministrador.getInstance().verify(userName, password);
                if (adminExist) {
                    view.dispose();
                    new ControladorMainWindow(new MainWindow()).screen();
                } else {
                    Messages.show("Credenciales incorrectas");
                }
            } else Messages.show("Complete todos los campos");
        }
    }
    
}
