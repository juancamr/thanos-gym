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
import thanosgym.Main;
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
            String phone = panel.jtxtPhone.getText();
            String repeatedPassword = String.valueOf(panel.jtxtRepeatPassword.getPassword());

            if (!userName.isEmpty() || !password.isEmpty() || !nombres.isEmpty() || !email.isEmpty()) {
                if (userName.matches(usernameRegex)) {
                    if (password.matches(passwordRegex)) {
                        if (!phone.isEmpty() && phone.matches("[0-9]{9}") || phone.isEmpty()) {
                            if (password.equals(repeatedPassword)) {
                                if (email.matches(emailRegex)) {
                                    password = StringUtils.sha256(password);
                                    Administrador administrador = new Administrador.Builder()
                                            .setEmail(email)
                                            .setFullName(nombres)
                                            .setUsername(userName)
                                            .setPassword(password)
                                            .build();
                                    if (!phone.isEmpty()) {
                                        administrador.setPhone(Long.valueOf(phone));
                                    }

                                    Response<Administrador> response = CRUDAdministrador.getInstance().create(administrador);

                                    if (response.isSuccess()) {
                                        view.dispose();
                                        Main.admin = response.getData();
                                        new ControladorMainWindow(new MainWindow()).screen();
                                    }
                                }
                            } else {
                                Messages.show("Las contraseñas no coinciden");
                            }
                        } else {
                            Messages.show("El telefono debe ser un numero de 9 digitos.");
                        }
                    } else {
                        Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
                    }
                } else {
                    Messages.show("Ingrese un nombre de usuario valido");
                }
            } else {
                Messages.show("Complete todos los campos");
            }
        }
    }

}
