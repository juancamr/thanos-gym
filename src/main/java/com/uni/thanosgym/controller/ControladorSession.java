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
    public static WindowSession vista;
    public static PanelLogin panelLogin;
    public static PanelRegister panelRegister;

    public static void initWindow() {
        WindowSession view = ControladorSession.getWindow();
        FrameUtils.showWindow(view, "Inicia sesion");
        showLoginPanel();
    }

    public static void showLoginPanel() {
        WindowSession view = ControladorSession.getWindow();
        PanelLogin panel = ControladorSession.getPanelLogin();
        FrameUtils.addEnterEvent(panel.jPassword, ControladorSession::iniciarSesion);
        FrameUtils.addOnClickEvent(panel.jbtnIniciar, ControladorSession::iniciarSesion);
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> {
            ControladorSession.showRegisterPanel();
        });
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombreUsuario.requestFocus();
    }

    public static void showRegisterPanel() {
        WindowSession view = ControladorSession.getWindow();
        PanelRegister panel = ControladorSession.getPanelRegister();
        FrameUtils.addOnClickEvent(panel.jbtnInicioSesion, ControladorSession::showLoginPanel);
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, ControladorSession::registrar);
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombresCompletos.requestFocus();
    }

    public static void registrar() {
        WindowSession view = ControladorSession.getWindow();
        PanelRegister panel = ControladorSession.getPanelRegister();
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

    public static void iniciarSesion() {
        WindowSession view = ControladorSession.getWindow();
        PanelLogin panel = ControladorSession.getPanelLogin();
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

    public static WindowSession getWindow() {
        if (vista == null)
            vista = new WindowSession();
        return vista;
    }

    public static PanelLogin getPanelLogin() {
        if (panelLogin == null)
            panelLogin = new PanelLogin();
        return panelLogin;
    }

    public static PanelRegister getPanelRegister() {
        if (panelRegister == null)
            panelRegister = new PanelRegister();
        return panelRegister;
    }

}
