package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.view.PanelLogin;
import com.uni.thanosgym.view.PanelRegister;
import com.uni.thanosgym.view.WindowSession;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;

public class ControladorSession {

    public static void showLoginPanel(WindowSession view, PanelLogin panel) {
        FrameUtils.addEnterEvent(panel.jPassword, () -> {iniciarSesion(panel, view);});
        FrameUtils.addOnClickEvent(panel.jbtnIniciar, () -> {iniciarSesion(panel, view);});
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> {
            ControladorSession.showRegisterPanel(view, new PanelRegister());
        });
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombreUsuario.requestFocus();
    }

    public static void showRegisterPanel(WindowSession view, PanelRegister panel) {
        FrameUtils.addOnClickEvent(panel.jbtnInicioSesion, () -> {
            ControladorSession.showLoginPanel(view, new PanelLogin());
        });
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> {ControladorSession.registrar(view, panel);});
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombresCompletos.requestFocus();
    }

    public static void registrar(WindowSession view, PanelRegister panel) {
        String nombres = panel.jtxtNombresCompletos.getText();
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());
        String email = panel.jtxtCorreo.getText();
        String phone = panel.jtxtPhone.getText();
        String repeatedPassword = String.valueOf(panel.jtxtRepeatPassword.getPassword());

        if (!userName.isEmpty() || !password.isEmpty() || !nombres.isEmpty() || !email.isEmpty()) {
            if (userName.matches(StringUtils.usernameRegex)) {
                if (password.matches(StringUtils.passwordRegex)) {
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
                                    Auth.signIn(response.getData());
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

    public static void iniciarSesion(PanelLogin panel, WindowSession view) {
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());

        if (!userName.isEmpty() || !password.isEmpty()) {
            password = StringUtils.sha256(password);
            Response<Administrador> response = CRUDAdministrador.getInstance().verify(userName, password);
            if (response.isSuccess()) {
                view.dispose();
                Auth.signIn(response.getData());
            } else {
                Messages.show("Credenciales incorrectas");
            }
        } else
            Messages.show("Complete todos los campos");

    }
}
