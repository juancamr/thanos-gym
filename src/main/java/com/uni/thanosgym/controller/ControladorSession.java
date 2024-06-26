package com.uni.thanosgym.controller;

import javax.swing.*;

import java.io.File;

import com.uni.thanosgym.dao.CRUDAdministrador;
import com.uni.thanosgym.view.PanelLogin;
import com.uni.thanosgym.view.PanelRegister;
import com.uni.thanosgym.view.VerifyAdminMaster;
import com.uni.thanosgym.view.WindowSession;
import com.uni.thanosgym.model.Admin;
import com.uni.thanosgym.model.Response;
import com.uni.thanosgym.utils.Auth;
import com.uni.thanosgym.utils.FrameUtils;
import com.uni.thanosgym.utils.Messages;
import com.uni.thanosgym.utils.StringUtils;
import com.uni.thanosgym.utils.Uploader;

public class ControladorSession {
    public static WindowSession vista;
    public static PanelLogin panelLogin;
    public static PanelRegister panelRegister;
    public static boolean panelLoginRendered = false;
    public static boolean panelRegisterRendered = false;
    public static File imageSelected = null;

    public static void initWindow() {
        String imageUrl = "https://i.pinimg.com/736x/1d/bc/ef/1dbcef30ca20a90b82c60490804de803.jpg";
        WindowSession view = ControladorSession.getWindow();
        view.jpanelImage.removeAll();
        FrameUtils.renderImageFromWeb(imageUrl, view.jpanelImage);
        view.jpanelImage.revalidate();
        view.jpanelImage.repaint();
        showLoginPanel();
        FrameUtils.showWindow(view, "Inicia sesion");
    }

    public static void showLoginPanel() {
        WindowSession view = ControladorSession.getWindow();
        PanelLogin panel = ControladorSession.getPanelLogin();
        if (!panelLoginRendered) {
            FrameUtils.addEnterEvent(panel.jPassword, ControladorSession::iniciarSesion);
            FrameUtils.addOnClickEvent(panel.jbtnIniciar, ControladorSession::iniciarSesion);
            FrameUtils.addOnClickEvent(panel.jbtnRegistro, () -> {
                ControladorSession.showRegisterPanel();
            });
            panelLoginRendered = true;
        }
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombreUsuario.requestFocus();
    }

    public static void showRegisterPanel() {
        WindowSession view = ControladorSession.getWindow();
        PanelRegister panel = ControladorSession.getPanelRegister();
        if (!panelRegisterRendered) {
            FrameUtils.addEnterEvent(panel.jtxtRepeatPassword, ControladorSession::registrarAction);
            FrameUtils.addOnClickEvent(panel.jbtnInicioSesion, ControladorSession::showLoginPanel);
            FrameUtils.addOnClickEvent(panel.jbtnRegistro, ControladorSession::registrarAction);
            FrameUtils.addOnClickEvent(panel.jbtnUploadImagen, ControladorSession::chooseImageAction);
        }
        FrameUtils.showPanel(view, panel);
        panel.jtxtNombresCompletos.requestFocus();
    }

    private static void chooseImageAction() {
        PanelRegister panel = getPanelRegister();
        Response<File> response = FrameUtils.chooseImage(getPanelRegister());
        if (response.isSuccess()) {
            imageSelected = response.getData();
            panel.jlblFileName.setText(imageSelected.getName());
        } else {
            Messages.show(response.getMessage());
            imageSelected = null;
            panel.jlblFileName.setText("");
        }
    }

    private static void registrarAction() {
        PanelRegister panel = ControladorSession.getPanelRegister();
        String nombres = panel.jtxtNombresCompletos.getText();
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());
        String email = panel.jtxtCorreo.getText();
        String phone = panel.jtxtPhone.getText();
        String repeatedPassword = String.valueOf(panel.jtxtRepeatPassword.getPassword());

        if (userName.isEmpty() || password.isEmpty() || nombres.isEmpty() || email.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        if (!userName.matches(StringUtils.usernameRegex)) {
            Messages.show("Ingrese un nombre de usuario valido");
            return;
        }
        if (!password.matches(StringUtils.passwordRegex)) {
            Messages.show("Contraseña invalida, recuerda que debe ser almenos de 8 caracteres");
            return;
        }
        if (!phone.isEmpty() && !StringUtils.isValidPhone(phone)) {
            Messages.show("El telefono debe ser un numero de 9 digitos.");
            return;
        }
        if (!password.equals(repeatedPassword)) {
            Messages.show("Las contraseñas no coinciden");
            return;
        }
        if (!StringUtils.isValidEmail(email)) {
            Messages.show("Ingrese un correo valido");
            return;
        }

        password = StringUtils.sha256(password);
        boolean isForMaster = panel.jCheckIsForMaster.isSelected();

        Admin administrador = new Admin.Builder()
                .setFullName(nombres)
                .setEmail(email)
                .setPhone(phone)
                .setUsername(userName)
                .setPassword(password)
                .setPhotoUrl("")
                .setRol(isForMaster ? Admin.Rol.MASTER : Admin.Rol.EMPLEADO)
                .build();
        if (!phone.isEmpty()) {
            administrador.setPhone(phone);
        }
        if (CRUDAdministrador.getInstance().getQuantity() == 0) {
            registrar(administrador, new Admin.Builder().build());
            return;
        }
        showVerifyAdminMaster(administrador);
    }

    private static void showVerifyAdminMaster(Admin administrador) {
        VerifyAdminMaster verifyMasterWindow = new VerifyAdminMaster();
        verifyMasterWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        FrameUtils.addEnterEvent(verifyMasterWindow.jPassword, () -> {
            ControladorSession.verifyAdminMaster(verifyMasterWindow, administrador);
        });
        FrameUtils.addOnClickEvent(verifyMasterWindow.jbtnIniciar, () -> {
            ControladorSession.verifyAdminMaster(verifyMasterWindow, administrador);
        });
        FrameUtils.showWindow(verifyMasterWindow, "Verificar administrador superior");
    }

    private static void verifyAdminMaster(VerifyAdminMaster ventana, Admin administrador) {
        String username = ventana.jtxtNombreUsuario.getText();
        String password = String.valueOf(ventana.jPassword.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        Admin adminMaster = new Admin.Builder()
                .setUsername(username)
                .setPassword(StringUtils.sha256(password))
                .build();
        ventana.dispose();
        registrar(administrador, adminMaster);
    }

    private static void registrar(Admin admin, Admin adminMaster) {
        if (imageSelected != null) {
            Uploader.UploaderResponse resUploader = Uploader.uploadImage(imageSelected);
            if (!resUploader.isSuccess()) {
                Messages.show(resUploader.getMessage());
                imageSelected = null;
                return;
            }
            admin.setPhotoUrl(resUploader.getUrl());
            imageSelected = null;
        }
        Response<Admin> response = CRUDAdministrador.getInstance()
                .create(admin, adminMaster);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        PanelRegister panel = ControladorSession.getPanelRegister();
        FrameUtils.clearInputs(new JTextField[] { panel.jtxtCorreo, panel.jtxtNombresCompletos, panel.jtxtNombreUsuario,
                panel.jtxtPhone });
        panel.jPassword.setText("");
        panel.jtxtRepeatPassword.setText("");
        vista.dispose();
        Admin adminForSignin = response.getData();
        adminForSignin.setId(response.getId());
        Auth.signIn(adminForSignin);
    }

    public static void iniciarSesion() {
        WindowSession view = ControladorSession.getWindow();
        PanelLogin panel = ControladorSession.getPanelLogin();
        String userName = panel.jtxtNombreUsuario.getText();
        String password = String.valueOf(panel.jPassword.getPassword());

        if (userName.isEmpty() || password.isEmpty()) {
            Messages.show("Complete todos los campos");
            return;
        }
        password = StringUtils.sha256(password);
        Response<Admin> response = CRUDAdministrador.getInstance().verify(userName, password, false);
        if (!response.isSuccess()) {
            Messages.show(response.getMessage());
            return;
        }
        panel.jtxtNombreUsuario.setText("");
        panel.jPassword.setText("");
        panel.jtxtNombreUsuario.requestFocus();
        Admin administrador = response.getData();
        administrador.setPersistencia(panel.jCheckSesion.isSelected());
        view.dispose();
        Auth.signIn(administrador);

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
