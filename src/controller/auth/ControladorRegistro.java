package controller.auth;

import DAO.CRUDAdministrador;
import controller.ControladorMainWindow;
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
import view.MainWindow;

public class ControladorRegistro implements ActionListener {

    WindowSession view;
    PanelRegister panel;

    public ControladorRegistro(WindowSession v, PanelRegister pan) {
        view = v;
        panel = pan;
        panel.jbtnInicioSesion.addActionListener(this);
        panel.jbtnRegistro.addActionListener(this);
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombresCompletos.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnInicioSesion) {
            new ControladorLogin(view, new PanelLogin());
        }

        if (e.getSource() == panel.jbtnRegistro) {
            String usernameRegex = "^[a-z]{3,15}$";
            String passwordRegex = "^[a-z]{8,}$";
            String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            
            String nombres = panel.jtxtNombresCompletos.getText();
            String userName = panel.jtxtNombreUsuario.getText();
            String password = String.valueOf(panel.jPassword.getPassword());
            String email = panel.jtxtCorreo.getText();
            
            if (!userName.isEmpty() || !password.isEmpty() || !nombres.isEmpty() || !email.isEmpty()) {
                if (userName.matches(usernameRegex)) {
                   if (password.matches(passwordRegex)) {
                       if (email.matches(emailRegex)) {
                            password = StringUtils.sha256(password);
                            Response<Administrador> response = CRUDAdministrador.getInstance().create(new Administrador.Builder()
                                    .setEmail(email)
                                    .setFullName(nombres)
                                    .setUsername(userName)
                                    .setPassword(password)
                                    .build());

                            if (response.isSuccess()) {
                                view.dispose();
                                new ControladorMainWindow(new MainWindow()).screen();
                            }
                            Messages.show(response.getMessage());
                       }
                   } else Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
                } else Messages.show("Ingrese un nombre de usuario valido");
            } else Messages.show("Complete todos los campos");
        }
    }

}
