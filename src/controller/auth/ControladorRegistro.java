package controller.auth;

import DAO.CrudAdministrador;
import model.Administrador;
import model.Response;
import view.auth.PanelLogin;
import view.auth.PanelRegister;
import view.auth.WindowSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import utils.FrameUtils;
import utils.Messages;
import utils.StringUtils;

public class ControladorRegistro implements ActionListener {

    WindowSession view;
    PanelRegister panel;

    public ControladorRegistro(WindowSession v, PanelRegister pan) {
        view = v;
        panel = pan;
        panel.jbtnInicioSesion.addActionListener(this);
        panel.jbtnRegistro.addActionListener(this);
        FrameUtils.showPanel(view, panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnInicioSesion) {
            new ControladorLogin(view, new PanelLogin());
        }

        if (e.getSource() == panel.jbtnRegistro) {
            String usernameRegex = "^[a-z]{3,15}$";
            String passwordRegex = "^[a-z]{8,}$";
            
            String nombres = panel.jtxtNombresCompletos.getText();
            String userName = panel.jtxtNombreUsuario.getText();
            String password = String.valueOf(panel.jPassword.getPassword());
            
            if (!userName.isEmpty() || !password.isEmpty() || !nombres.isEmpty()) {
                if (userName.matches(usernameRegex)) {
                   if (password.matches(passwordRegex)) {
                       password = StringUtils.sha256(password);
                       Response<Administrador> response = CrudAdministrador.getInstance().register(new Administrador.Builder()
                               .setFullName(nombres)
                               .setUsername(userName)
                               .setPassword(password)
                               .build());
                       
                       if (response.isSuccess()) {
                           view.dispose();
//                           new ControladorPna(new VentanaPrincipal()).screen();
                            System.out.println("welcome, redirect to dashboard...");
                       }
                       Messages.show(response.getMessage());
                   } else Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
                } else Messages.show("Ingrese un nombre de usuario valido");
            } else Messages.show("Complete todos los campos");
        }
    }

}
