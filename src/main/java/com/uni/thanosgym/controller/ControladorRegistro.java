package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.view.PanelLogin;
import com.uni.thanosgym.view.PanelRegister;
import com.uni.thanosgym.view.WindowSession;
import com.uni.thanosgym.preferences.UserPreference;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.MainWindow;

public class ControladorRegistro {

    WindowSession view;
    PanelRegister panel;
    String usernameRegex = "^[a-z]{8,15}$";
    String passwordRegex = "^[a-z]{8,}$";

    public ControladorRegistro(WindowSession v, PanelRegister pan) {
        view = v;
        panel = pan;
        FrameUtils.addOnClickEvent(panel.jbtnInicioSesion, () -> {
            new ControladorLogin(view, new PanelLogin());
        });
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> registrar());
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombresCompletos.requestFocus();
    }

    private void registrar() {
        String nombres = panel.jtxtNombresCompletos.getText();
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());
        String email = panel.jtxtCorreo.getText();
        String phone = panel.jtxtPhone.getText();
        String repeatedPassword = String.valueOf(panel.jtxtRepeatPassword.getPassword());

        if (!userName.isEmpty() || !password.isEmpty() || !nombres.isEmpty() || !email.isEmpty()) {
            if (userName.matches(usernameRegex)) {
                if (password.matches(passwordRegex)) {
                    if (phone.isEmpty() || StringUtils.isValidPhone(phone)) {
                        if (password.equals(repeatedPassword)) {
                            if (StringUtils.isValidEmail(email)) {
                                password = StringUtils.sha256(password);
                                Administrador administrador = new Administrador.Builder()
                                        .setEmail(email)
                                        .setFullName(nombres)
                                        .setUsername(userName)
                                        .setPassword(password)
                                        .build();
                                if (!phone.isEmpty()) {
                                    administrador.setPhone(Integer.parseInt(phone));
                                }

                                Response<Administrador> response = CRUDAdministrador.getInstance()
                                        .create(administrador);

                                if (response.isSuccess()) {
                                    view.dispose();
                                    UserPreference.setAdminData(response.getData());
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
