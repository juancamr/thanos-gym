package com.uni.thanosgym.controller;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.view.PanelLogin;
import com.uni.thanosgym.view.PanelRegister;
import com.uni.thanosgym.view.WindowSession;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.uni.thanosgym.model.Administrador;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.preferences.UserPreference;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.view.MainWindow;

public class ControladorLogin implements ActionListener {
    WindowSession view;
    PanelLogin panel;

    public ControladorLogin(WindowSession v, PanelLogin pan) {
        view = v;
        panel = pan;
        panel.jbtnRegistro.addActionListener(this);
        panel.jbtnIniciar.addActionListener(this);
        FrameUtils.submitOnEnter(panel.jtxtNombreUsuario, () -> iniciarSesion());
        FrameUtils.submitOnEnter(panel.jPassword, () -> iniciarSesion());
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombreUsuario.requestFocus();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == panel.jbtnRegistro) {
            new ControladorRegistro(view, new PanelRegister());
        }

        if (e.getSource() == panel.jbtnIniciar) {
            iniciarSesion();
        }
    }

    private void iniciarSesion() {
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());

        if (!userName.isEmpty() || !password.isEmpty()) {
            password = StringUtils.sha256(password);
            Response<Administrador> response = CRUDAdministrador.getInstance().verify(userName, password);
            if (response.isSuccess()) {
                view.dispose();
                UserPreference.setAdminData(response.getData());
                new ControladorMainWindow(new MainWindow()).screen();
            } else {
                Messages.show("Credenciales incorrectas");
            }
        } else
            Messages.show("Complete todos los campos");

    }
}
