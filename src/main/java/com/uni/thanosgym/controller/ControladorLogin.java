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

public class ControladorLogin {
    WindowSession view;
    PanelLogin panel;

    public ControladorLogin(WindowSession v, PanelLogin pan) {
        view = v;
        panel = pan;
        FrameUtils.addEnterEvent(panel.jPassword, this::iniciarSesion);
        FrameUtils.addOnClickEvent(panel.jbtnIniciar, this::iniciarSesion);
        FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> {
            new ControladorRegistro(view, new PanelRegister());
        });
        FrameUtils.showPanel(view, panel);
        FrameUtils.showWindow(view, "Inicia sesion");
        panel.jtxtNombreUsuario.requestFocus();
    }

    private void iniciarSesion() {
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
